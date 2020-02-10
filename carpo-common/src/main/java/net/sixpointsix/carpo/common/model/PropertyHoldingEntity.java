package net.sixpointsix.carpo.common.model;

/**
 * An entity can have properties with this interface.
 *
 * @author Andrew Tarry
 * @since 0.0.1
 */
public interface PropertyHoldingEntity {

    /**
     * Get the set of properties for the entity
     *
     * <p>
     *     This method should never return null. If there are no properties an empty collection should be returned
     * </p>
     *
     * @return PropertyCollection
     */
    PropertyCollection getProperties();
}
