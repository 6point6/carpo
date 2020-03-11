package net.sixpointsix.carpo.common.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DataPointRowTest {

    @Test
    void create() {
        DataPoint datapoint = mock(DataPoint.class);
        DataPointRow row = new DataPointRow(List.of(datapoint));

        assertEquals(1, row.getDataPoints().size());
        assertEquals(1, row.stream().count());
    }

    @Test
    void values() {
        DataPoint datapoint = mock(DataPoint.class);
        when(datapoint.getValue()).thenReturn("abc");
        DataPointRow row = new DataPointRow(List.of(datapoint));

        assertEquals("abc", row.getValues().get(0));
    }
}