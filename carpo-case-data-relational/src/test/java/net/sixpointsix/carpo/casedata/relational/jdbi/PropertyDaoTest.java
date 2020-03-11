package net.sixpointsix.carpo.casedata.relational.jdbi;

import net.sixpointsix.carpo.casedata.model.CarpoCase;
import net.sixpointsix.carpo.casedata.model.builder.CarpoCaseBuilder;
import net.sixpointsix.carpo.casedata.relational.jdbi.model.PropertyWrapper;
import net.sixpointsix.carpo.common.model.Property;
import net.sixpointsix.carpo.common.model.immutable.ImmutableProperty;
import net.sixpointsix.carpo.test.extension.DatabaseManagementExtension;
import net.sixpointsix.carpo.test.extension.JdbiParameterResolver;
import org.jdbi.v3.core.Jdbi;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@Tag("IT")
@ExtendWith({DatabaseManagementExtension.class, JdbiParameterResolver.class})
class PropertyDaoTest {

    @Test
    void insert(Jdbi jdbi) {
        String id = UUID.randomUUID().toString();
        Property property = ImmutableProperty.build("a", "b", id);

        CarpoCase carpoCase = getCase();

        jdbi.useExtension(CaseDataDao.class, h -> h.insertCaseData(carpoCase.getCarpoId(), carpoCase.getTimestamp().getCreatedAt(), carpoCase.getTimestamp().getLastUpdated()));

        jdbi.useExtension(PropertyDao.class, h -> h.insert(new PropertyWrapper(property), carpoCase.getCarpoId()));

        Optional<Property> saved = jdbi.withExtension(PropertyDao.class, h -> h.selectById(id));

        assertTrue(saved.isPresent());
    }

    @Test
    void insertAndGetByKey(Jdbi jdbi) {
        Property property = ImmutableProperty.build("a", "b");

        CarpoCase carpoCase = getCase();

        jdbi.useExtension(CaseDataDao.class, h -> h.insertCaseData(carpoCase.getCarpoId(), carpoCase.getTimestamp().getCreatedAt(), carpoCase.getTimestamp().getLastUpdated()));

        jdbi.useExtension(PropertyDao.class, h -> h.insert(new PropertyWrapper(property), carpoCase.getCarpoId()));

        Optional<Property> saved = jdbi.withExtension(PropertyDao.class, h -> h.selectByKey(carpoCase.getCarpoId(), "a"));

        assertTrue(saved.isPresent());
    }

    @Test
    void insertAndGetByWrongKey(Jdbi jdbi) {
        Property property = ImmutableProperty.build("a", "b");

        CarpoCase carpoCase = getCase();

        jdbi.useExtension(CaseDataDao.class, h -> h.insertCaseData(carpoCase.getCarpoId(), carpoCase.getTimestamp().getCreatedAt(), carpoCase.getTimestamp().getLastUpdated()));

        jdbi.useExtension(PropertyDao.class, h -> h.insert(new PropertyWrapper(property), carpoCase.getCarpoId()));

        Optional<Property> saved = jdbi.withExtension(PropertyDao.class, h -> h.selectByKey(carpoCase.getCarpoId(), "z"));

        assertFalse(saved.isPresent());
    }

    private CarpoCase getCase() {
        UUID id = UUID.randomUUID();
        CarpoCaseBuilder builder = CarpoCase.builder();
        builder.setTimestampToNow();
        builder.setCarpoId(id);

        return builder.build();
    }
}