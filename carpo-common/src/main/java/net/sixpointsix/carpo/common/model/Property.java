package net.sixpointsix.carpo.common.model;

import java.util.List;
import java.util.Optional;

/**
 * A property is a generic data structure to that contains data required by modules or end users
 *
 * <p>
 *     In order to support a flexible schema a Carpo case needs to be able to support a flexible set of properties. Storing
 *     data in this structure is not the most efficient approach but it provides flexibility
 * </p>
 *
 * @author Andrew Tarry
 * @since 0.0.1
 */
public interface Property {

    /**
     * The key is the unique identifier for the property
     *
     * <p>
     *     A key is expected to be unique in the context of the collection of properties that it is in. There is no
     *     enforcement of that within the property itself since there might be special cases in which that is not true
     *     but it is expected
     * </p>
     *
     * @return property key
     */
    String getKey();

    /**
     * Test if the property is null
     *
     * @return true if the property is null
     */
    Boolean isNull();

    /**
     * Get the value of the property as a string
     *
     * @return optional string value
     */
    Optional<String> getStringValue();

    /**
     * Get the value of the property as a numeric long
     *
     * @return optional long value
     */
    Optional<Long> getLongValue();

    /**
     * Get the value of the property as a numeric double
     *
     * @return optional double value
     */
    Optional<Double> getDoubleValue();

    /**
     * Get the value of the property as a boolean
     *
     * @return optional boolean value
     */
    Optional<Boolean> getBooleanValue();

    /**
     * Get the value of the property as an object of type T
     *
     * <p>
     *     When this method is called the object will be cast as T in order to be returned. This method will catch an
     *     exception linked to casting and return an empty optional
     * </p>
     *
     * @param type class to be cast as
     * @param <T> generic type to be applied
     * @return Optional of object value
     */
    <T> Optional<T> getObjectValue(Class<T> type);

    /**
     * Get the value of the property as a list of objects with type T
     *
     * <p>
     *     When this method is called the objects will be cast as T in order to be returned. This method will catch an
     *     exception linked to casting and return a list of the objects that were successfully cast. In the event of all
     *     the objects failing to cast the list will be empty
     * </p>
     *
     * @param type class to be cast as
     * @param <T> generic type to be applied
     * @return List of object values
     */
    <T> List<T> getListValue(Class<T> type);

    /**
     * Test if the property has a value of the string type
     *
     * @return true if the value is a string
     */
    Boolean hasStringValue();

    /**
     * Test if the property has a value of the long type
     *
     * @return true if the value is a long
     */
    Boolean hasLongValue();

    /**
     * Test if the property has a value of the double type
     *
     * @return true if the double is a string
     */
    Boolean hasDoubleValue();

    /**
     * Test if the property has a value of the boolean type
     *
     * <p>
     *     This method is only to test if the value is a boolean not what its value is. If the property value is the
     *     boolean 'FALSE' this method will return true
     * </p>
     *
     * @return true if the value is a boolean
     */
    Boolean hasBooleanValue();

    /**
     * Test if the property has a value of the object type
     *
     * @return true if the object is a string
     */
    Boolean hasObjectValue();

    /**
     * Test if the property has a value of the list type
     *
     * @return true if the list is a string
     */
    Boolean hasListValue();
}
