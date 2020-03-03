package net.sixpointsix.carpo.serialization.persistence.serializer;

import net.sixpointsix.carpo.common.model.Property;
import net.sixpointsix.carpo.serialization.persistence.ImmutableSerializedProperty;
import net.sixpointsix.carpo.serialization.persistence.PersistenceSerializer;
import net.sixpointsix.carpo.serialization.persistence.SerializedProperty;

/**
 * Serialize a null based value
 *
 * @author Andrew Tarry
 * @since 0.0.1
 */
public class NullSerializer implements PersistenceSerializer {

    /**
     * Test that the property can be serialized
     *
     * @param property to be serialized
     * @return True if it can be serialized
     */
    @Override
    public boolean canSerialize(Property property) {
        return property != null
                && property.isNull();
    }

    /**
     * Serialize a property
     *
     * @param property to be serialized
     * @return Serialized Property
     */
    @Override
    public SerializedProperty serialize(Property property) {
        return new ImmutableSerializedProperty(property.getKey());
    }

    /**
     * Get the priority of the serializer
     * <p>
     * Serializers with a lower priority will be tried first
     * </p>
     *
     * @return priority of the serializer
     */
    @Override
    public int getPriority() {
        return DEFAULT_PRIORITY + 5000;
    }
}
