package net.sixpointsix.carpo.common.jackson;

import java.net.PortUnreachableException;

/**
 * Common keys for json objects
 *
 * @since 0.5.0
 * @author Andrew Tarry
 */
abstract public class JsonKey {

    /**
     * Key value to be set in the json body
     */
    public static final String KEY = "key";

    /**
     * Type of the property
     */
    public static final String TYPE_KEY = "type";

    /**
     * Property value
     */
    public static final String VALUE_KEY = "value";

    /**
     * Creation date
     */
    public static final String CREATED_AT = "createdAt";

    /**
     * Last update date
     */
    public static final String LAST_UPDATED = "lastUpdated";
}
