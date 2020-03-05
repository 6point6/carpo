package net.sixpointsix.carpo.common.writer;

import net.sixpointsix.carpo.common.model.DataPoint;
import net.sixpointsix.carpo.common.model.DataPointRow;
import net.sixpointsix.carpo.common.model.DataSet;
import org.junit.jupiter.api.Test;

import java.io.OutputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CsvDataWriterTest {

    private static final String lineSeparator = System.lineSeparator();


    @Test
    void writeData() {
        DataPointRow row = mock(DataPointRow.class);
        when(row.getValues()).thenReturn(List.of("a", "b"));

        DataSet dataSet = mock(DataSet.class);
        when(dataSet.getHeaders()).thenReturn(List.of("H1", "H2"));
        when(dataSet.getRows()).thenReturn(List.of(row));

        CsvDataWriter csvDataWriter = new CsvDataWriter();
        OutputStream outputStream = csvDataWriter.writeDataSet(dataSet);

        String expected = "H1,H2" + lineSeparator +
                "a,b" + lineSeparator;

        assertEquals(expected, outputStream.toString());
    }
}