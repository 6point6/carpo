package net.sixpointsix.carpo.mi.exception;

import net.sixpointsix.carpo.common.exception.CarpoException;

/**
 * Exception to be thrown when no method set can be found
 *
 * @author Andrew Tarry
 * @since 0.3.0
 */
public class MethodListNotFound extends CarpoException {

    /**
     * Constructs a new runtime exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public MethodListNotFound(String message) {
        super(message);
    }
}
