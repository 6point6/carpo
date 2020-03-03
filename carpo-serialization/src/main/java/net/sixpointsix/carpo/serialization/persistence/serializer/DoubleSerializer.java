package net.sixpointsix.carpo.serialization.persistence.serializer;

import net.sixpointsix.carpo.common.model.Property;
import net.sixpointsix.carpo.common.model.PropertyType;
import net.sixpointsix.carpo.serialization.persistence.ImmutableSerializedProperty;
import net.sixpointsix.carpo.serialization.persistence.PersistenceSerializer;
import net.sixpointsix.carpo.serialization.persistence.SerializedProperty;

import java.nio.ByteBuffer;

/**
 * Serialize a double value
 *
 * @author Andrew Tarry
 * @since 0.0.1
 */
public class DoubleSerializer implements PersistenceSerializer {

    /**
     * Test that the property can be serialized
     *
     * @param property to be serialized
     * @return True if it can be serialized
     */
    @Override
    public boolean canSerialize(Property property) {
        return property != null &&
                property.hasDoubleValue();
    }

    /**
     * Serialize a property
     *
     * @param property to be serialized
     * @return Serialized Property
     */
    @Override
    public SerializedProperty serialize(Property property) {
        if(property.getDoubleValue().isEmpty()) {
            return new ImmutableSerializedProperty(PropertyType.DOUBLE, property.getKey());
        }

        ByteBuffer buffer = ByteBuffer.allocate(Double.BYTES);
        property.getDoubleValue().ifPresent(buffer::putDouble);
        byte[] data = buffer.array();

        return new ImmutableSerializedProperty(PropertyType.DOUBLE, property.getKey(), data);
    }
}
