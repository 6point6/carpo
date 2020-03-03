package net.sixpointsix.carpo.serialization.persistence;

import net.sixpointsix.carpo.common.model.PropertyType;

/**
 * Property data with meta data for storage
 *
 * @author Andrew Tarry
 * @since 0.0.1
 */
public interface SerializedProperty {

    /**
     * Get the type of the entity
     * @return Property Type
     */
    PropertyType getType();

    /**
     * Get the property key
     * @return Key of the property
     */
    String getKey();

    /**
     * Get the data of the entity
     * @return object data
     */
    byte[] getData();
}
