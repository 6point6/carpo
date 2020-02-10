package net.sixpointsix.carpo.common.model;

/**
 * Root interface of entities within the Carpo application.
 *
 * The entity interface is intended to provide only the id and timestamps as the most basic information that other
 * modules could depend on
 *
 * @author Andrew Tarry
 * @since 0.0.1
 */
public interface CarpoEntity {

    /**
     * Get the entity id as a string
     * <p>
     *     The Carpo entity interface has no opinion on the format of the id. It could be a number, UUID or something else
     *     but it must be able to be represented as a string
     * </p>
     * <p>
     *     This method uses the carpoId not just id because some systems will already be using a getId method
     * </p>
     *
     * @return Entity ID
     */
    String getCarpoId();

    /**
     * Get the timestamp associated with the entity
     *
     * <p>
     *     A Capo entity should have a timestamp linked with if for audit purposes but it is not a essential
     * </p>
     *
     * @return Timestamp
     */
    Timestamp getTimestamp();

}
