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

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@Tag("IT")
@ExtendWith({DatabaseManagementExtension.class, JdbiParameterResolver.class})
class PropertyDaoTest {

    @Test
    void insert(Jdbi jdbi) {
        Property property = ImmutableProperty.build("a", "b");

        UUID id = UUID.randomUUID();
        CarpoCaseBuilder builder = CarpoCase.builder();
        builder.setTimestampToNow();
        builder.setCarpoId(id);

        CarpoCase carpoCase = builder.build();

        jdbi.useExtension(CaseDataDao.class, h -> h.insertCaseData(carpoCase.getCarpoId(), carpoCase.getTimestamp().getCreatedAt(), carpoCase.getTimestamp().getLastUpdated()));

        jdbi.useExtension(PropertyDao.class, h -> h.insert(new PropertyWrapper(property), id.toString()));
    }
}