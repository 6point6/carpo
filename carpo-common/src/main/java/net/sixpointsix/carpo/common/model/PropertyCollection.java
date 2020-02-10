package net.sixpointsix.carpo.common.model;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * Collection of properties within an entity
 *
 * @author Andrew Tarry
 * @since 0.0.1
 */
public interface PropertyCollection extends Collection<Property> {

    /**
     * Get a property by its key
     *
     * @param key property key
     * @return Property optional, empty if the key is not set
     */
    Optional<Property> getByKey(String key);

    /**
     * Test if a property exists with a key
     *
     * @param key property key
     * @return True if the property exists
     */
    Boolean hasPropertyByKey(String key);

    /**
     * Get the string value if it exists
     *
     * <p>
     *     This method will get the string value and will return an empty optional if the property with key does not
     *     exist or the value is not a string
     * </p>
     *
     * @param key property key
     * @return optional string
     */
    Optional<String> getStringByKey(String key);

    /**
     * Get the long value if it exists
     *
     * <p>
     *     This method will get the long value and will return an empty optional if the property with key does not
     *     exist or the value is not a long
     * </p>
     *
     * @param key property key
     * @return optional long
     */
    Optional<Long> getLongByKey(String key);

    /**
     * Get the double value if it exists
     *
     * <p>
     *     This method will get the double value and will return an empty optional if the property with key does not
     *     exist or the value is not a double
     * </p>
     *
     * @param key property key
     * @return optional double
     */
    Optional<Double> getDoubleByKey(String key);

    /**
     * Get the boolean value if it exists
     *
     * <p>
     *     This method will get the boolean value and will return an empty optional if the property with key does not
     *     exist or the value is not a boolean
     * </p>
     *
     * @param key property key
     * @return optional boolean
     */
    Optional<Boolean> getBooleanByKey(String key);

    /**
     * Get the object value if it exists
     *
     * <p>
     *     This method will get the object value and will return an empty optional if the property with key does not
     *     exist or the value is not a object or it cannot be cast to T
     * </p>
     *
     * @param key property key
     * @param type type to cast to
     * @param <T> type to cast to
     * @return optional object
     */
    <T> Optional<T> getObjectByKey(String key, Class<T> type);

    /**
     * Get the object values as a list
     *
     * <p>
     *     This method will get the list of object values and will return an empty list if the property with key does
     *     not exist. Each element will be cast a T and only the successful ones will be returned
     * </p>
     *
     * @param key property key
     * @param type type to cast to
     * @param <T> type to cast to
     * @return list of object
     */
    <T> List<T> getListByKey(String key, Class<T> type);
}
