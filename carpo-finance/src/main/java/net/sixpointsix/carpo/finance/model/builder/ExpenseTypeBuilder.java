package net.sixpointsix.carpo.finance.model.builder;

import net.sixpointsix.carpo.common.model.AbstractPropertyEntityBuilder;
import net.sixpointsix.carpo.common.model.CarpoPropertyEntity;
import net.sixpointsix.carpo.finance.model.ExpenseType;
import net.sixpointsix.carpo.finance.model.ImmutableExpenseType;

/**
 * Build a new expenses type
 */
public class ExpenseTypeBuilder extends AbstractPropertyEntityBuilder<ExpenseType> {

    public ExpenseTypeBuilder() {
    }

    public ExpenseTypeBuilder(CarpoPropertyEntity entity) {
        super(entity);
    }

    /**
     * Build the entity
     *
     * @return entity
     */
    @Override
    public ExpenseType build() {
        return new ImmutableExpenseType(carpoId, timestamp, properties);
    }
}
