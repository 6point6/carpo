package net.sixpointsix.carpo.finance.repository;

import net.sixpointsix.carpo.common.repository.CrudEntityRepository;
import net.sixpointsix.carpo.finance.model.ExpenseType;

/**
 * Expense type repository to load the expenses
 *
 * @author Andrew Tarry
 * @since 0.4.0
 */
public interface ExpenseTypeRepository extends CrudEntityRepository<ExpenseType> {
}
