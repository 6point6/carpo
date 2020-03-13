package net.sixpointsix.carpo.common.exception;

import javax.validation.ConstraintViolation;
import java.util.Set;

/**
 * Exception thrown when constraints are violated
 *
 * @author Andrew Tarry
 * @since 0.7.0
 */
public class PropertyValidationException extends CarpoException {

    private final Set<ConstraintViolation<?>> violationSet;

    public PropertyValidationException(String message, Set<ConstraintViolation<?>> violationSet) {
        super(message);
        this.violationSet = violationSet;
    }

    public Set<ConstraintViolation<?>> getViolationSet() {
        return violationSet;
    }
}
