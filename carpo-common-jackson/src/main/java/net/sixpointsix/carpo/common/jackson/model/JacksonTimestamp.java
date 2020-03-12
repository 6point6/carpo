package net.sixpointsix.carpo.common.jackson.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import net.sixpointsix.carpo.common.model.Timestamp;

import java.time.LocalDateTime;

/**
 * Timestamp representation with Jackson
 *
 * @author Andrew Tarry
 * @since 0.5.0
 */
public class JacksonTimestamp implements Timestamp {

    private final LocalDateTime createdAt;
    private final LocalDateTime lastUpdated;

    @JsonCreator
    public JacksonTimestamp(
            @JsonProperty("createdAt") LocalDateTime createdAt,
            @JsonProperty("lastUpdated") LocalDateTime lastUpdated) {
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
