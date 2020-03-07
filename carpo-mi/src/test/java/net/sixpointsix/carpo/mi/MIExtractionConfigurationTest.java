package net.sixpointsix.carpo.mi;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MIExtractionConfigurationTest {

    @Test
    void emptyConfiguration() {
        assertNotNull(MIExtractionConfiguration.empty());
    }
}