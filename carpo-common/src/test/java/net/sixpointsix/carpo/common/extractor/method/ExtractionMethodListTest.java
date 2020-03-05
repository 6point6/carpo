package net.sixpointsix.carpo.common.extractor.method;

import net.sixpointsix.carpo.common.extractor.method.ExtractionMethodList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExtractionMethodListTest {

    @Test
    void emptyList() {
        ExtractionMethodList extractionMethodList = new ExtractionMethodList();

        assertEquals(0, extractionMethodList.size());
        assertTrue(extractionMethodList.isEmpty());
    }

    @Test
    void addString() {
        ExtractionMethodList extractionMethodList = new ExtractionMethodList();
        extractionMethodList.add("a", "b");

        assertEquals(1, extractionMethodList.size());
        assertFalse(extractionMethodList.isEmpty());
    }

    @Test
    void addObject() {
        ExtractionMethodList extractionMethodList = new ExtractionMethodList();
        extractionMethodList.add("a", "b", Object.class, Object::toString);

        assertEquals(1, extractionMethodList.size());
        assertFalse(extractionMethodList.isEmpty());
    }

    @Test
    void addList() {
        ExtractionMethodList extractionMethodList = new ExtractionMethodList();
        extractionMethodList.add("a", "b", Object.class, Object::toString, 2);

        assertEquals(1, extractionMethodList.size());
        assertFalse(extractionMethodList.isEmpty());
    }
}