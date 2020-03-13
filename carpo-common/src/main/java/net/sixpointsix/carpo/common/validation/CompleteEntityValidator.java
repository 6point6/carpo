package net.sixpointsix.carpo.common.validation;

import net.sixpointsix.carpo.common.model.CarpoPropertyEntity;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Validate an entity has all the fields and none of them are null
 *
 * @author Andrew Tarry
 * @since 0.7.0
 */
public class CompleteEntityValidator implements ConstraintValidator<CompleteEntity, CarpoPropertyEntity> {
    /**
     * Initializes the validator in preparation for calls.
     * The constraint annotation for a given constraint declaration
     * is passed.
     * <p>
     * This method is guaranteed to be called before any use of this instance for
     * validation.
     * <p>
     * The default implementation is a no-op.
     *
     * @param constraintAnnotation annotation instance for a given constraint declaration
     */
    @Override
    public void initialize(CompleteEntity constraintAnnotation) {

    }

    /**
     * Implements the validation logic.
     * The state of {@code value} must not be altered.
     * <p>
     * This method can be accessed concurrently, thread-safety must be ensured
     * by the implementation.
     *
     * @param value   object to validate
     * @param context context in which the constraint is evaluated
     * @return {@code false} if {@code value} does not pass the constraint
     */
    @Override
    public boolean isValid(CarpoPropertyEntity value, ConstraintValidatorContext context) {
        return value.getCarpoId() != null
                && !value.getCarpoId().isBlank()
                && value.getTimestamp() != null
                && value.getTimestamp().getLastUpdated() != null
                && value.getTimestamp().getCreatedAt() != null
                && value.getProperties() != null;
    }
}
