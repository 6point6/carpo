package net.sixpointsix.carpo.finance.relational;

import net.sixpointsix.carpo.finance.model.ExpenseType;
import net.sixpointsix.carpo.finance.model.builder.ExpenseTypeBuilder;
import net.sixpointsix.carpo.finance.repository.ExpenseTypeRepository;
import net.sixpointsix.carpo.relational.JdbiRelationalManager;

import java.util.Optional;

/**
 * Manage data for expense types
 *
 * @author Andrew Tarry
 * @since 0.4.0
 */
public class RelationalExpenseTypeRepository implements ExpenseTypeRepository {

    private final JdbiRelationalManager jdbiRelationalManager;

    public RelationalExpenseTypeRepository(JdbiRelationalManager jdbiRelationalManager) {
        this.jdbiRelationalManager = jdbiRelationalManager;
    }

    /**
     * Create a new record of the entity
     *
     * @param entity to be saved
     */
    @Override
    public void create(ExpenseType entity) {
        jdbiRelationalManager.create(entity);
    }

    /**
     * Save the changes to entity
     * @param entity to be saved
     */
    @Override
    public void update(ExpenseType entity) {
        jdbiRelationalManager.update(entity);
    }

    /**
     * Delete an entity
     *
     * @param entity to be deleted
     */
    @Override
    public void delete(ExpenseType entity) {

    }

    /**
     * Get an entity by an id
     *
     * @param id id to search for
     * @return entity data
     */
    @Override
    public Optional<ExpenseType> getById(String id) {
        return jdbiRelationalManager
                .getById(id)
                .map(c -> new ExpenseTypeBuilder(c).build());
    }
}
