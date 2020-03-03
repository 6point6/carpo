package net.sixpointsix.carpo.serialization.persistence.serializer;

import net.sixpointsix.carpo.common.model.Property;
import net.sixpointsix.carpo.common.model.PropertyType;
import net.sixpointsix.carpo.serialization.persistence.ImmutableSerializedProperty;
import net.sixpointsix.carpo.serialization.persistence.PersistenceSerializer;
import net.sixpointsix.carpo.serialization.persistence.SerializedProperty;
import org.apache.commons.lang3.SerializationUtils;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Serialize an object based value
 *
 * @author Andrew Tarry
 * @since 0.0.1
 */
public class JavaListSerializer implements PersistenceSerializer {

    /**
     * Test that the property can be serialized
     *
     * @param property to be serialized
     * @return True if it can be serialized
     */
    @Override
    public boolean canSerialize(Property property) {
        return property != null &&
                property.hasListValue() &&
                !property.getListValue(Serializable.class).isEmpty();
    }

    /**
     * Serialize a property
     *
     * @param property to be serialized
     * @return Serialized Property
     */
    @Override
    public SerializedProperty serialize(Property property) {
        ArrayList<Serializable> input = new ArrayList<>(property.getListValue(Serializable.class));
        byte[] data = SerializationUtils.serialize(input);

        return new ImmutableSerializedProperty(PropertyType.LIST, property.getKey(), data);
    }

    /**
     * Get the priority of the serializer
     * <p>
     * Serializers with a lower priority will be tried first
     * </p>
     *
     * <p>
     *     By default the java serializer has a low priority compared to others
     * </p>
     *
     * @return priority of the serializer
     */
    @Override
    public int getPriority() {
        return DEFAULT_PRIORITY + 1000;
    }
}
