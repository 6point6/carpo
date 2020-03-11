package net.sixpointsix.carpo.casedata.relational;

import net.sixpointsix.carpo.casedata.model.CarpoCase;
import net.sixpointsix.carpo.casedata.model.builder.CarpoCaseBuilder;
import net.sixpointsix.carpo.common.model.immutable.ImmutableProperty;
import net.sixpointsix.carpo.test.extension.DatabaseManagementExtension;
import net.sixpointsix.carpo.test.extension.JdbiParameterResolver;
import org.jdbi.v3.core.Jdbi;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@Tag("IT")
@ExtendWith({DatabaseManagementExtension.class, JdbiParameterResolver.class})
class RelationalCaseDataEntityRepositoryTest {

    private RelationalCaseDataEntityRepository relationalCaseDataEntityRepository;

    @BeforeEach
    void setUp(Jdbi jdbi) {
        relationalCaseDataEntityRepository = RelationalCaseDataEntityRepository.build(jdbi);
    }

    @Test
    void insert() {
        CarpoCase carpoCase = getCase();
        relationalCaseDataEntityRepository.create(carpoCase);

        Optional<CarpoCase> saved = relationalCaseDataEntityRepository.getById(carpoCase.getCarpoId());

        assertTrue(saved.isPresent());

        CarpoCase savedCase = saved.get();
        assertEquals(carpoCase.getCarpoId(), savedCase.getCarpoId());
        assertEquals(4, savedCase.getProperties().size());
        assertEquals("b", savedCase.getProperties().getStringByKey("a").get());
        assertEquals(1, savedCase.getProperties().getLongByKey("b").get());
        assertEquals(2.2, savedCase.getProperties().getDoubleByKey("c").get());
        assertTrue(savedCase.getProperties().getBooleanByKey("d").get());
    }

    @Test
    void updateWithoutPropertyChange() {
        CarpoCase carpoCase = getCase();
        relationalCaseDataEntityRepository.create(carpoCase);

        relationalCaseDataEntityRepository.update(carpoCase);

        Optional<CarpoCase> saved = relationalCaseDataEntityRepository.getById(carpoCase.getCarpoId());

        assertNotEquals(carpoCase.getTimestamp().getLastUpdated(), saved.get().getTimestamp().getLastUpdated());
    }

    @Test
    void updateWithNewProperty() {
        CarpoCase carpoCase = getCase();
        relationalCaseDataEntityRepository.create(carpoCase);

        carpoCase.getProperties().add(ImmutableProperty.build("x", "y"));

        relationalCaseDataEntityRepository.update(carpoCase);

        Optional<CarpoCase> saved = relationalCaseDataEntityRepository.getById(carpoCase.getCarpoId());

        assertNotEquals(carpoCase.getTimestamp().getLastUpdated(), saved.get().getTimestamp().getLastUpdated());
        assertEquals(5, saved.get().getProperties().size());
    }

    @Test
    void updateWithPropertyChange() {
        CarpoCase carpoCase = getCase();
        relationalCaseDataEntityRepository.create(carpoCase);

        carpoCase.getProperties().add(ImmutableProperty.build("b", "y"));

        relationalCaseDataEntityRepository.update(carpoCase);

        Optional<CarpoCase> saved = relationalCaseDataEntityRepository.getById(carpoCase.getCarpoId());

        assertNotEquals(carpoCase.getTimestamp().getLastUpdated(), saved.get().getTimestamp().getLastUpdated());
        assertEquals(4, saved.get().getProperties().size());
    }

    private CarpoCase getCase() {
        UUID id = UUID.randomUUID();
        CarpoCaseBuilder builder = CarpoCase.builder();
        builder.setTimestampToNow();
        builder.setCarpoId(id);
        builder.addProperty(ImmutableProperty.build("a", "b"));
        builder.addProperty(ImmutableProperty.build("b", 1));
        builder.addProperty(ImmutableProperty.build("c", 2.2));
        builder.addProperty(ImmutableProperty.build("d", true));

        return builder.build();
    }
}