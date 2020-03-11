package net.sixpointsix.carpo.finance.relational;

import net.sixpointsix.carpo.common.model.immutable.ImmutableProperty;
import net.sixpointsix.carpo.finance.model.ExpenseType;
import net.sixpointsix.carpo.finance.model.builder.ExpenseTypeBuilder;
import net.sixpointsix.carpo.relational.JdbiRelationalManager;
import net.sixpointsix.carpo.relational.MutableRelationalConfiguration;
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
class RelationalExpenseTypeRepositoryTest {

    private RelationalExpenseTypeRepository relationalExpenseTypeRepository;

    @BeforeEach
    void setUp(Jdbi jdbi) {
        MutableRelationalConfiguration relationalConfiguration = new MutableRelationalConfiguration();
        relationalConfiguration.setEntityDataTable("carpo_expense_type");
        relationalConfiguration.setPropertyTable("carpo_expense_type_property");

        relationalExpenseTypeRepository = new RelationalExpenseTypeRepository(new JdbiRelationalManager(jdbi, relationalConfiguration));
    }

    @Test
    void insert() {
        ExpenseType expenseType = getType();
        relationalExpenseTypeRepository.create(expenseType);

        Optional<ExpenseType> saved = relationalExpenseTypeRepository.getById(expenseType.getCarpoId());

        assertTrue(saved.isPresent());

        ExpenseType savedType = saved.get();
        assertEquals(expenseType.getCarpoId(), savedType.getCarpoId());
        assertEquals(4, savedType.getProperties().size());
        assertEquals("b", savedType.getProperties().getStringByKey("a").get());
        assertEquals(1, savedType.getProperties().getLongByKey("b").get());
        assertEquals(2.2, savedType.getProperties().getDoubleByKey("c").get());
        assertTrue(savedType.getProperties().getBooleanByKey("d").get());
    }

    @Test
    void updateWithoutPropertyChange() {
        ExpenseType expenseType = getType();
        relationalExpenseTypeRepository.create(expenseType);

        relationalExpenseTypeRepository.update(expenseType);

        Optional<ExpenseType> saved = relationalExpenseTypeRepository.getById(expenseType.getCarpoId());

        assertNotEquals(expenseType.getTimestamp().getLastUpdated(), saved.get().getTimestamp().getLastUpdated());
    }

    @Test
    void updateWithNewProperty() {
        ExpenseType expenseType = getType();
        relationalExpenseTypeRepository.create(expenseType);

        expenseType.getProperties().add(ImmutableProperty.build("x", "y"));

        relationalExpenseTypeRepository.update(expenseType);

        Optional<ExpenseType> saved = relationalExpenseTypeRepository.getById(expenseType.getCarpoId());

        assertNotEquals(expenseType.getTimestamp().getLastUpdated(), saved.get().getTimestamp().getLastUpdated());
        assertEquals(5, saved.get().getProperties().size());
    }

    @Test
    void updateWithPropertyChange() {
        ExpenseType expenseType = getType();
        relationalExpenseTypeRepository.create(expenseType);

        expenseType.getProperties().add(ImmutableProperty.build("b", "y"));

        relationalExpenseTypeRepository.update(expenseType);

        Optional<ExpenseType> saved = relationalExpenseTypeRepository.getById(expenseType.getCarpoId());

        assertNotEquals(expenseType.getTimestamp().getLastUpdated(), saved.get().getTimestamp().getLastUpdated());
        assertEquals(4, saved.get().getProperties().size());
    }

    private ExpenseType getType() {
        UUID id = UUID.randomUUID();
        ExpenseTypeBuilder builder = new ExpenseTypeBuilder();
        builder.setTimestampToNow();
        builder.setCarpoId(id);
        builder.addProperty(ImmutableProperty.build("a", "b"));
        builder.addProperty(ImmutableProperty.build("b", 1));
        builder.addProperty(ImmutableProperty.build("c", 2.2));
        builder.addProperty(ImmutableProperty.build("d", true));

        return builder.build();
    }

}