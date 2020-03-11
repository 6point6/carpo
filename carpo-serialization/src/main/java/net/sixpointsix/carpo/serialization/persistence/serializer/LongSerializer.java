package net.sixpointsix.carpo.serialization.persistence.serializer;

import com.google.common.primitives.Longs;
import net.sixpointsix.carpo.common.model.Property;
import net.sixpointsix.carpo.common.model.PropertyType;
import net.sixpointsix.carpo.serialization.persistence.ImmutableSerializedProperty;
import net.sixpointsix.carpo.serialization.persistence.PersistenceSerializer;
import net.sixpointsix.carpo.serialization.persistence.SerializedProperty;

/**
 * Serialize long data
 *
 * @author Andrew Tarry
 * @since 0.0.1
 */
public class LongSerializer implements PersistenceSerializer {

    /**
     * Test that the property can be serialized
     *
     * @param property to be serialized
     * @return True if it can be serialized
     */
    @Override
    public boolean canSerialize(Property property) {
        return property != null &&
                property.hasLongValue();
    }

    /**
     * Serialize a property
     *
     * @param property to be serialized
     * @return Serialized Property
     */
    @Override
    public SerializedProperty serialize(Property property) {
        byte[] data = property.getLongValue().map(Longs::toByteArray).orElse(null);

        return new ImmutableSerializedProperty(PropertyType.LONG, property.getKey(), data);
    }
}
