package net.sixpointsix.carpo.common.model;

import java.time.LocalDateTime;

/**
 * Timestamps provide standard creation and update times for entities
 *
 * <p>
 *     The purpose is to make it easy to record data on when an entity is changed.
 * </p>
 *
 * @author Andrew Tarry
 * @since 0.0.1
 */
public interface Timestamp {

    /**
     * Get the time that the entity was created.
     *
     * @return Creation time
     */
    LocalDateTime getCreatedAt();

    /**
     * Get the time that the entity was last updated
     *
     * @return Last update time
     */
    LocalDateTime getLastUpdated();
}
