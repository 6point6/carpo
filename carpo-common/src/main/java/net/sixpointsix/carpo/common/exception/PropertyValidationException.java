package net.sixpointsix.carpo.common.exception;

import java.util.Set;

/**
 * Exception thrown when constraints are violated
 *
 * @author Andrew Tarry
 * @since 0.7.0
 */
public class PropertyValidationException extends CarpoException {

    private final Set<String> violationSet;

    public PropertyValidationException(String message, Set<String> violationSet) {
        super(message);
        this.violationSet = violationSet;
    }

    public Set<String> getViolationSet() {
        return violationSet;
    }
}
