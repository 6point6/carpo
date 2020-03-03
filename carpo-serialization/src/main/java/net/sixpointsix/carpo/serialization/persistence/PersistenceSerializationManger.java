package net.sixpointsix.carpo.serialization.persistence;

import net.sixpointsix.carpo.common.model.Property;

/**
 * Serializer to be used for storing data in the persistent data store
 *
 * @author Andrew Tarry
 * @since 0.0.1
 */
public interface PersistenceSerializationManger {

    /**
     * Serialize a property with the available serializers
     * @param property to be serialized
     * @return Serialized Property
     */
    SerializedProperty serializeProperty(Property property);
}
