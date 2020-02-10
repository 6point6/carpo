package net.sixpointsix.carpo.common.model.immutable;

import net.sixpointsix.carpo.common.model.Timestamp;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ImmutableTimestampTest {

    @Test
    void buildWithTimestamp() {
        LocalDateTime now = LocalDateTime.now();

        Timestamp timestamp = ImmutableTimestamp.build(now, now);

        assertEquals(now, timestamp.getCreatedAt());
        assertEquals(now, timestamp.getLastUpdated());
    }

    @Test
    void buildNow() {
        Timestamp timestamp = ImmutableTimestamp.build();

        assertNotNull(timestamp.getCreatedAt());
        assertNotNull(timestamp.getLastUpdated());
    }
}