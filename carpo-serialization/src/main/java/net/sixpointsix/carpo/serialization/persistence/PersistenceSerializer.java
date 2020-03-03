package net.sixpointsix.carpo.serialization.persistence;

import net.sixpointsix.carpo.common.model.Property;

public interface PersistenceSerializer {

    int DEFAULT_PRIORITY = 1000;

    /**
     * Test that the property can be serialized
     * @param property to be serialized
     * @return True if it can be serialized
     */
    boolean canSerialize(Property property);

    /**
     * Serialize a property
     * @param property to be serialized
     * @return Serialized Property
     */
    SerializedProperty serialize(Property property);

    /**
     * Get the priority of the serializer
     *  <p>
     *      Serializers with a lower priority will be tried first
     *  </p>
     *
     * @return priority of the serializer
     */
    default int getPriority() {
        return DEFAULT_PRIORITY;
    }
}
