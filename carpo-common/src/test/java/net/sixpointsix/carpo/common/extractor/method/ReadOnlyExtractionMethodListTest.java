package net.sixpointsix.carpo.common.extractor.method;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReadOnlyExtractionMethodListTest {

    @Test
    void emptyList() {
        assertNotNull(ReadOnlyExtractionMethodList.empty());
        assertTrue(ReadOnlyExtractionMethodList.empty().isEmpty());
    }
}