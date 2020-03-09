package net.sixpointsix.carpo.common.model.immutable;

import net.sixpointsix.carpo.common.model.Property;
import net.sixpointsix.carpo.common.model.PropertyCollection;
import net.sixpointsix.carpo.common.model.PropertyHoldingEntity;
import net.sixpointsix.carpo.common.model.mutable.MutablePropertyCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Immutable property that flexibly store data
 *
 * @author Andrew Tarry
 * @since 0.0.1
 */
public final class ImmutableProperty implements Property {

    private static final Logger logger = LoggerFactory.getLogger(ImmutableProperty.class);

    /**
     * Property key
     */
    private final String key;

    /**
     * String value
     */
    private final String stringValue;

    /**
     * Double value
     */
    private final Double doubleValue;

    /**
     * Long value
     */
    private final Long longValue;

    /**
     * Boolean value
     */
    private final Boolean booleanValue;

    /**
     * Object value
     */
    private final Object objectValue;

    /**
     * List value
     */
    private final List<Object> listValue;

    public static ImmutableProperty build(String key, String value) {
        return new ImmutableProperty(
                key,
                value,
                null,
                null,
                null,
                null,
                null
        );
    }

    public static ImmutableProperty build(String key, Double value) {
        return new ImmutableProperty(
                key,
                null,
                value,
                null,
                null,
                null,
                null
        );
    }

    public static ImmutableProperty build(String key, Long value) {
        return new ImmutableProperty(
                key,
                null,
                null,
                value,
                null,
                null,
                null
        );
    }

    public static ImmutableProperty build(String key, Boolean value) {
        return new ImmutableProperty(
                key,
                null,
                null,
                null,
                value,
                null,
                null
        );
    }

    public static ImmutableProperty build(String key, Object value) {
        return new ImmutableProperty(
                key,
                null,
                null,
                null,
                null,
                value,
                null
        );
    }

    public static ImmutableProperty build(String key, Property value) {
        return new ImmutableProperty(
                key,
                null,
                null,
                null,
                null,
                new MutablePropertyCollection() {{
                    add(value);
                }},
                null
        );
    }

    public static ImmutableProperty build(String key, List<Object> value) {
        return new ImmutableProperty(
                key,
                null,
                null,
                null,
                null,
                null,
                value
        );
    }

    private ImmutableProperty(String key, String stringValue, Double doubleValue, Long longValue, Boolean booleanValue, Object objectValue, List<Object> listValue) {
        this.key = key;
        this.stringValue = stringValue;
        this.doubleValue = doubleValue;
        this.longValue = longValue;
        this.booleanValue = booleanValue;
        this.objectValue = objectValue;
        this.listValue = listValue;
    }

    /**
     * The key is the unique identifier for the property
     *
     * <p>
     * A key is expected to be unique in the context of the collection of properties that it is in. There is no
     * enforcement of that within the property itself since there might be special cases in which that is not true
     * but it is expected
     * </p>
     *
     * @return property key
     */
    @Override
    public String getKey() {
        return key;
    }

    /**
     * Test if the property is null
     *
     * @return true if the property is null
     */
    @Override
    public Boolean isNull() {
        return !hasBooleanValue()
                && !hasDoubleValue()
                && !hasListValue()
                && !hasLongValue()
                && !hasObjectValue()
                && !hasStringValue();
    }

    /**
     * Get the value of the property as a string
     *
     * @return optional string value
     */
    @Override
    public Optional<String> getStringValue() {
        return Optional.ofNullable(stringValue);
    }

    /**
     * Get the value of the property as a numeric long
     *
     * @return optional long value
     */
    @Override
    public Optional<Long> getLongValue() {
        return Optional.ofNullable(longValue);
    }

    /**
     * Get the value of the property as a numeric double
     *
     * @return optional double value
     */
    @Override
    public Optional<Double> getDoubleValue() {
        return Optional.ofNullable(doubleValue);
    }

    /**
     * Get the value of the property as a boolean
     *
     * @return optional boolean value
     */
    @Override
    public Optional<Boolean> getBooleanValue() {
        return Optional.ofNullable(booleanValue);
    }

    /**
     * Get the value of the property as an object of type T
     *
     * <p>
     * When this method is called the object will be cast as T in order to be returned. This method will catch an
     * exception linked to casting and return an empty optional
     * </p>
     *
     * @param type class to be cast as
     * @return Optional of object value
     */
    @Override
    public <T> Optional<T> getObjectValue(Class<T> type) {
        return getObject(type, objectValue);
    }

    /**
     * Get the value of the property as a list of objects with type T
     *
     * <p>
     * When this method is called the objects will be cast as T in order to be returned. This method will catch an
     * exception linked to casting and return a list of the objects that were successfully cast. In the event of all
     * the objects failing to cast the list will be empty
     * </p>
     *
     * @param type class to be cast as
     * @return List of object values
     */
    @Override
    public <T> List<T> getListValue(Class<T> type) {
        if(listValue == null || listValue.isEmpty()) {
            return List.of();
        }

        return listValue
                .stream()
                .map(o -> getObject(type, o))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    /**
     * Test if the property has a value of the string type
     *
     * @return true if the value is a string
     */
    @Override
    public Boolean hasStringValue() {
        return getStringValue().isPresent();
    }

    /**
     * Test if the property has a value of the long type
     *
     * @return true if the value is a long
     */
    @Override
    public Boolean hasLongValue() {
        return getLongValue().isPresent();
    }

    /**
     * Test if the property has a value of the double type
     *
     * @return true if the double is a string
     */
    @Override
    public Boolean hasDoubleValue() {
        return getDoubleValue().isPresent();
    }

    /**
     * Test if the property has a value of the boolean type
     *
     * <p>
     * This method is only to test if the value is a boolean not what its value is. If the property value is the
     * boolean 'FALSE' this method will return true
     * </p>
     *
     * @return true if the value is a boolean
     */
    @Override
    public Boolean hasBooleanValue() {
        return getBooleanValue().isPresent();
    }

    /**
     * Test if the property has a value of the object type
     *
     * @return true if the object is a string
     */
    @Override
    public Boolean hasObjectValue() {
        return objectValue != null;
    }

    /**
     * Test if the property has a value of the list type
     *
     * @return true if the list is a string
     */
    @Override
    public Boolean hasListValue() {
        return listValue != null;
    }

    /**
     * Get the object and cast it as needed
     *
     * @param type type to cast to
     * @param data data to cast
     * @param <T> type to cast to
     * @return optional of the object
     */
    private <T> Optional<T> getObject(Class<T> type, Object data) {
        if(data == null) {
            return Optional.empty();
        }

        try {
            T instance = type.cast(data);
            return Optional.of(instance);
        }catch (ClassCastException e) {
            logger.debug("Unable to cast property", e);

            return Optional.empty();
        }
    }
}
