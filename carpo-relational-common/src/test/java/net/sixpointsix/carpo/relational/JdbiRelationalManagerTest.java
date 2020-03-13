package net.sixpointsix.carpo.relational;

import net.sixpointsix.carpo.casedata.model.CarpoCase;
import net.sixpointsix.carpo.casedata.model.builder.CarpoCaseBuilder;
import net.sixpointsix.carpo.common.model.CarpoPropertyEntity;
import net.sixpointsix.carpo.common.model.immutable.ImmutableProperty;
import net.sixpointsix.carpo.common.repository.MutableSelectProperties;
import net.sixpointsix.carpo.common.repository.SelectProperties;
import net.sixpointsix.carpo.test.extension.DatabaseManagementExtension;
import net.sixpointsix.carpo.test.extension.JdbiParameterResolver;
import org.jdbi.v3.core.Jdbi;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@Tag("IT")
@ExtendWith({DatabaseManagementExtension.class, JdbiParameterResolver.class})
class JdbiRelationalManagerTest {

    private static JdbiRelationalManager jdbiRelationalManager;

    @BeforeAll
    static void setUp(Jdbi jdbi) {
        MutableRelationalConfiguration relationalConfiguration = new MutableRelationalConfiguration();
        relationalConfiguration.setPropertyTable("carpo_case_property");
        relationalConfiguration.setEntityDataTable("carpo_case");

        jdbiRelationalManager = new JdbiRelationalManager(jdbi, relationalConfiguration);
    }

    @BeforeEach
    void clearDatabase(Jdbi jdbi) {
        jdbi.useHandle(h -> {
            h.createUpdate("DELETE FROM carpo_case_property;").execute();
            h.createUpdate("DELETE FROM carpo_case;").execute();
        });
    }

    @Test
    void insert() throws InterruptedException {
        CarpoCase carpoCase = getCase();
        jdbiRelationalManager.create(carpoCase);

        Thread.sleep(500);

        Optional<CarpoPropertyEntity> saved = jdbiRelationalManager.getById(carpoCase.getCarpoId());

        assertTrue(saved.isPresent());

        CarpoPropertyEntity savedCase = saved.get();
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
        jdbiRelationalManager.create(carpoCase);

        jdbiRelationalManager.update(carpoCase);

        Optional<CarpoPropertyEntity> saved = jdbiRelationalManager.getById(carpoCase.getCarpoId());

        assertNotEquals(carpoCase.getTimestamp().getLastUpdated(), saved.get().getTimestamp().getLastUpdated());
    }

    @Test
    void updateWithNewProperty() throws InterruptedException {
        CarpoCase carpoCase = getCase();
        jdbiRelationalManager.create(carpoCase);

        Thread.sleep(500);

        carpoCase.getProperties().add(ImmutableProperty.build("x", "y"));

        jdbiRelationalManager.update(carpoCase);

        Optional<CarpoPropertyEntity> saved = jdbiRelationalManager.getById(carpoCase.getCarpoId());

        assertNotEquals(carpoCase.getTimestamp().getLastUpdated(), saved.get().getTimestamp().getLastUpdated());
        assertEquals(5, saved.get().getProperties().size());
    }

    @Test
    void updateWithPropertyChange() throws InterruptedException {
        CarpoCase carpoCase = getCase();
        jdbiRelationalManager.create(carpoCase);

        Thread.sleep(500);
        carpoCase.getProperties().add(ImmutableProperty.build("b", "y"));

        jdbiRelationalManager.update(carpoCase);

        Optional<CarpoPropertyEntity> saved = jdbiRelationalManager.getById(carpoCase.getCarpoId());

        assertNotEquals(carpoCase.getTimestamp().getLastUpdated(), saved.get().getTimestamp().getLastUpdated());
        assertEquals(4, saved.get().getProperties().size());
    }

    @Test
    void selectAll() {
        IntStream.range(0, 5).forEach(i -> jdbiRelationalManager.create(getCase()));
        List<CarpoPropertyEntity> entities = jdbiRelationalManager.getBySelectProperties(SelectProperties.OPEN_SELECT);

        assertEquals(5, entities.size());
    }

    @Test
    void selectLimit() {
        IntStream.range(0, 5).forEach(i -> jdbiRelationalManager.create(getCase()));
        SelectProperties selectProperties = new MutableSelectProperties(0, 2);
        List<CarpoPropertyEntity> entities = jdbiRelationalManager.getBySelectProperties(selectProperties);

        assertEquals(2, entities.size());
    }

    @Test
    void selectWithProperty() {
        IntStream.range(0, 5).forEach(i -> {
            if(i == 0) {
                jdbiRelationalManager.create(getCase());
            } else {
                jdbiRelationalManager.create(getCaseWithString(ImmutableProperty.build("a", "x")));
            }
        });
        SelectProperties selectProperties = new MutableSelectProperties(0, 2, List.of(ImmutableProperty.build("a", "b")));
        List<CarpoPropertyEntity> entities = jdbiRelationalManager.getBySelectProperties(selectProperties);

        assertEquals(1, entities.size());
    }

    @Test
    void selectWithIntProperty() {
        IntStream.range(0, 5).forEach(i -> {
            if(i == 0) {
                jdbiRelationalManager.create(getCase());
            } else {
                jdbiRelationalManager.create(getCaseWithInt(ImmutableProperty.build("b", 99)));
            }
        });
        SelectProperties selectProperties = new MutableSelectProperties(0, 2, List.of(ImmutableProperty.build("b", 1)));
        List<CarpoPropertyEntity> entities = jdbiRelationalManager.getBySelectProperties(selectProperties);

        assertEquals(1, entities.size());
    }

    @Test
    void selectWithFloatProperty() {
        IntStream.range(0, 5).forEach(i -> {
            if(i == 0) {
                jdbiRelationalManager.create(getCase());
            } else {
                jdbiRelationalManager.create(getCaseWithFloat(ImmutableProperty.build("b", 99.9)));
            }
        });
        SelectProperties selectProperties = new MutableSelectProperties(0, 2, List.of(ImmutableProperty.build("c", 2.2)));
        List<CarpoPropertyEntity> entities = jdbiRelationalManager.getBySelectProperties(selectProperties);

        assertEquals(1, entities.size());
    }

    @Test
    void selectWithBooleanProperty() {
        IntStream.range(0, 5).forEach(i -> {
            if(i == 0) {
                jdbiRelationalManager.create(getCase());
            } else {
                jdbiRelationalManager.create(getCaseWithBoolean(ImmutableProperty.build("d", false)));
            }
        });
        SelectProperties selectProperties = new MutableSelectProperties(0, 2, List.of(ImmutableProperty.build("d", true)));
        List<CarpoPropertyEntity> entities = jdbiRelationalManager.getBySelectProperties(selectProperties);

        assertEquals(1, entities.size());
    }

    private CarpoCase getCase() {
        return getCase(ImmutableProperty.build("a", "b"), ImmutableProperty.build("b", 1), ImmutableProperty.build("c", 2.2), ImmutableProperty.build("d", true));
    }

    private CarpoCase getCaseWithInt(ImmutableProperty intProperty) {
        return getCase(ImmutableProperty.build("a", "b"), intProperty, ImmutableProperty.build("c", 2.2), ImmutableProperty.build("d", true));
    }

    private CarpoCase getCaseWithString(ImmutableProperty stringProperty) {
        return getCase(stringProperty, ImmutableProperty.build("b", 1), ImmutableProperty.build("c", 2.2), ImmutableProperty.build("d", true));
    }

    private CarpoCase getCaseWithFloat(ImmutableProperty floatProperty) {
        return getCase(ImmutableProperty.build("a", "b"), ImmutableProperty.build("b", 1), floatProperty, ImmutableProperty.build("d", true));
    }

    private CarpoCase getCaseWithBoolean(ImmutableProperty property) {
        return getCase(ImmutableProperty.build("a", "b"), ImmutableProperty.build("b", 1), ImmutableProperty.build("c", 2.2), property);
    }

    private CarpoCase getCase(ImmutableProperty stringProperty,
                              ImmutableProperty intProperty,
                              ImmutableProperty floatProperty,
                              ImmutableProperty booleanProperty) {
        UUID id = UUID.randomUUID();
        CarpoCaseBuilder builder = CarpoCase.builder();
        builder.setTimestampToNow();
        builder.setCarpoId(id);
        builder.addProperty(stringProperty);
        builder.addProperty(intProperty);
        builder.addProperty(floatProperty);
        builder.addProperty(booleanProperty);

        return builder.build();
    }

}