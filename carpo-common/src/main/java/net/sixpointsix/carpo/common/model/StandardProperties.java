package net.sixpointsix.carpo.common.model;

/**
 * Standard set of properties that can be used by entities and easily referenced
 *
 * <p>
 *     This class should not be extended, it is here to hold constants
 * </p>
 *
 * @author Andrew Tarry
 * @since 0.4.0
 */
abstract public class StandardProperties {

    /**
     * Name property that can be used to give an entity a easy identifier
     */
    public static final String NAME = "name";

    /**
     * Property that can be set to enable a soft delete
     */
    public static final String DELETED = "deleted";
}
