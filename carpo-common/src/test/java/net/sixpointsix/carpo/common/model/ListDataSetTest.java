package net.sixpointsix.carpo.common.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ListDataSetTest {

    @Test
    void nonEmptyRowSet() {
        DataSet dataSet = new ListDataSet(List.of(mock(DataPointRow.class)));

        assertFalse(dataSet.isEmpty());
        assertFalse(dataSet.getRows().isEmpty());
    }

    public static Stream<Arguments> invalidHeaders() {
        List<DataPointRow> nullRow = null;

        return Stream.of(
                Arguments.arguments(nullRow),
                Arguments.arguments(List.of())
        );
    }

    @ParameterizedTest
    @MethodSource("invalidHeaders")
    void getHeadersWithoutValue(List<DataPointRow> rows) {
        DataSet dataSet = new ListDataSet(rows);

        assertTrue(dataSet.getHeaders().isEmpty());
    }

    @Test
    void headersFromRows() {
        DataPoint dataPoint = new DataPoint("x", "y");
        DataPointRow dataPointRow = new DataPointRow(List.of(dataPoint));
        DataSet dataSet = new ListDataSet(List.of(dataPointRow));

        assertFalse(dataSet.getHeaders().isEmpty());
        assertEquals("x", dataSet.getHeaders().get(0));
    }
}