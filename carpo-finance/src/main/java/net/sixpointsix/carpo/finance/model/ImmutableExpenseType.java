package net.sixpointsix.carpo.finance.model;

import net.sixpointsix.carpo.common.model.PropertyCollection;
import net.sixpointsix.carpo.common.model.Timestamp;
import net.sixpointsix.carpo.common.model.immutable.AbstractImmutableCarpoPropertyEntity;

/**
 * Immutable type of expense
 */
public class ImmutableExpenseType extends AbstractImmutableCarpoPropertyEntity implements ExpenseType {

    public ImmutableExpenseType(String carpoId, Timestamp timestamp, PropertyCollection properties) {
        super(carpoId, timestamp, properties);
    }
}
