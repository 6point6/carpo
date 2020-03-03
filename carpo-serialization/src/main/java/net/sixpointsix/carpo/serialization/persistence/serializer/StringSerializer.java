package net.sixpointsix.carpo.serialization.persistence.serializer;

import net.sixpointsix.carpo.common.model.Property;
import net.sixpointsix.carpo.common.model.PropertyType;
import net.sixpointsix.carpo.serialization.persistence.ImmutableSerializedProperty;
import net.sixpointsix.carpo.serialization.persistence.PersistenceSerializer;
import net.sixpointsix.carpo.serialization.persistence.SerializedProperty;

/**
 * Serialize a string based value
 *
 * @author Andrew Tarry
 * @since 0.0.1
 */
public class StringSerializer implements PersistenceSerializer {

    /**
     * Test that the property is a string
     * @param property to be serialized
     * @return True if a string
     */
    @Override
    public boolean canSerialize(Property property) {
        return property != null && property.hasStringValue();
    }

    /**
     * Serialize a property
     *
     * @param property to be serialized
     * @return Serialized Property
     */
    @Override
    public SerializedProperty serialize(Property property) {
        byte[] data = property.getStringValue().map(String::getBytes).orElse(new byte[0]);

        return new ImmutableSerializedProperty(PropertyType.STRING, property.getKey(), data);
    }
}
