package net.sixpointsix.carpo.serialization.persistence;

import net.sixpointsix.carpo.common.model.PropertyType;

/**
 * Immutable record of a property
 *
 * @author Andrew Tarry
 * @since 0.0.1
 */
public class ImmutableSerializedProperty implements SerializedProperty {

    private final PropertyType propertyType;
    private final String key;
    private final byte[] data;

    public ImmutableSerializedProperty(String key) {
        this(PropertyType.NULL, key, null);
    }

    public ImmutableSerializedProperty(PropertyType propertyType, String key) {
        this(propertyType, key, null);
    }

    public ImmutableSerializedProperty(PropertyType propertyType, String key, byte[] data) {
        this.propertyType = propertyType;
        this.key = key;
        this.data = data;
    }

    @Override
    public PropertyType getType() {
        return propertyType;
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public byte[] getData() {
        return data;
    }
}
