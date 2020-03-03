package net.sixpointsix.carpo.serialization.persistence;

import net.sixpointsix.carpo.common.model.Property;

public interface PersistenceSerializationManger {

    SerializedProperty serializeProperty(Property property);
}
