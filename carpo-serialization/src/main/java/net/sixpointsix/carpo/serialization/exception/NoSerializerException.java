package net.sixpointsix.carpo.serialization.exception;

import net.sixpointsix.carpo.common.exception.CarpoException;

/**
 * Exception when a value cannot be serialized
 *
 * @author Andrew Tarry
 * @since 0.0.1
 */
public class NoSerializerException extends CarpoException {

    /**
     * Constructs a new runtime exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public NoSerializerException(String message) {
        super(message);
    }
}
