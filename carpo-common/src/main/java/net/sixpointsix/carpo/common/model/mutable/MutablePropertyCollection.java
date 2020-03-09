package net.sixpointsix.carpo.common.model.mutable;

import net.sixpointsix.carpo.common.model.Property;
import net.sixpointsix.carpo.common.model.PropertyCollection;

import java.util.*;
import java.util.stream.IntStream;

public class MutablePropertyCollection extends ArrayList<Property> implements PropertyCollection {

    /**
     * Get a property by its key
     *
     * @param key property key
     * @return Property optional, empty if the key is not set
     */
    @Override
    public Optional<Property> getByKey(final String key) {
        if(key == null) {
            return Optional.empty();
        }

        return stream()
                .filter(p -> key.equalsIgnoreCase(p.getKey()))
                .findFirst();
    }

    /**
     * Test if a property exists with a key
     *
     * @param key property key
     * @return True if the property exists
     */
    @Override
    public Boolean hasPropertyByKey(final String key) {
        return getByKey(key).isPresent();
    }

    /**
     * Get the string value if it exists
     *
     * <p>
     * This method will get the string value and will return an empty optional if the property with key does not
     * exist or the value is not a string
     * </p>
     *
     * @param key property key
     * @return optional string
     */
    @Override
    public Optional<String> getStringByKey(final String key) {
        return getByKey(key).flatMap(Property::getStringValue);
    }

    /**
     * Get the long value if it exists
     *
     * <p>
     * This method will get the long value and will return an empty optional if the property with key does not
     * exist or the value is not a long
     * </p>
     *
     * @param key property key
     * @return optional long
     */
    @Override
    public Optional<Long> getLongByKey(final String key) {
        return getByKey(key).flatMap(Property::getLongValue);
    }

    /**
     * Get the double value if it exists
     *
     * <p>
     * This method will get the double value and will return an empty optional if the property with key does not
     * exist or the value is not a double
     * </p>
     *
     * @param key property key
     * @return optional double
     */
    @Override
    public Optional<Double> getDoubleByKey(final String key) {
        return getByKey(key).flatMap(Property::getDoubleValue);
    }

    /**
     * Get the boolean value if it exists
     *
     * <p>
     * This method will get the boolean value and will return an empty optional if the property with key does not
     * exist or the value is not a boolean
     * </p>
     *
     * @param key property key
     * @return optional boolean
     */
    @Override
    public Optional<Boolean> getBooleanByKey(final String key) {
        return getByKey(key).flatMap(Property::getBooleanValue);
    }

    /**
     * Get the object value if it exists
     *
     * <p>
     * This method will get the object value and will return an empty optional if the property with key does not
     * exist or the value is not a object or it cannot be cast to T
     * </p>
     *
     * @param key  property key
     * @param type type to cast to
     * @return optional object
     */
    @Override
    public <T> Optional<T> getObjectByKey(final String key, final Class<T> type) {
        return getByKey(key).flatMap(o -> o.getObjectValue(type));
    }

    /**
     * Get the object values as a list
     *
     * <p>
     * This method will get the list of object values and will return an empty list if the property with key does
     * not exist. Each element will be cast a T and only the successful ones will be returned
     * </p>
     *
     * @param key  property key
     * @param type type to cast to
     * @return list of object
     */
    @Override
    public <T> List<T> getListByKey(String key, Class<T> type) {
        return getByKey(key).map(o -> o.getListValue(type)).orElse(List.of());
    }

    /**
     * Replace a property in a collection with another property by key
     *
     * @param property to be put in place of the existing value
     */
    @Override
    public boolean add(Property property) {
        if(property == null) {
            return false;
        }

        OptionalInt index = IntStream
                .range(0, size())
                .filter(i -> property.getKey().equalsIgnoreCase(get(i).getKey()))
                .findFirst();

        if(index.isPresent()) {
            set(index.getAsInt(), property);
        } else {
            super.add(property);
        }

        return true;
    }
}
