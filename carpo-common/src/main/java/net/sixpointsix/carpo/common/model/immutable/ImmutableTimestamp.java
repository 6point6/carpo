package net.sixpointsix.carpo.common.model.immutable;

import net.sixpointsix.carpo.common.model.Timestamp;

import java.time.LocalDateTime;

/**
 * Immutable timestamp
 *
 * @author Andrew Tarry
 * @since 0.0.1
 */
public final class ImmutableTimestamp implements Timestamp {

    private final LocalDateTime createdAt;
    private final LocalDateTime lastUpdated;

    /**
     * Build a timestamp with now as the values
     *
     * @return timestamp
     */
    public static Timestamp build() {
        return build(LocalDateTime.now(), LocalDateTime.now());
    }

    public static Timestamp build(LocalDateTime createdAt, LocalDateTime lastUpdated) {
        return new ImmutableTimestamp(createdAt, lastUpdated);
    }

    private ImmutableTimestamp(LocalDateTime createdAt, LocalDateTime lastUpdated) {
        this.createdAt = createdAt;
        this.lastUpdated = lastUpdated;
    }

    /**
     * Get the time that the entity was created.
     *
     * @return Creation time
     */
    @Override
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * Get the time that the entity was last updated
     *
     * @return Last update time
     */
    @Override
    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }
}
