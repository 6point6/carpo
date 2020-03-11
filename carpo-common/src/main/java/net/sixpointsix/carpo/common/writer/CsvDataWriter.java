package net.sixpointsix.carpo.common.writer;

import com.univocity.parsers.csv.CsvWriter;
import com.univocity.parsers.csv.CsvWriterSettings;
import net.sixpointsix.carpo.common.model.DataPointRow;
import net.sixpointsix.carpo.common.model.DataSet;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

/**
 * Write data as CSV
 *
 * @author Andrew Tarry
 * @since 0.2.0
 */
public class CsvDataWriter {

    /**
     * Convert the data into a CSV output stream
     * @param dataSet to be written
     * @return Output data
     */
    public OutputStream writeDataSet(DataSet dataSet) {
        OutputStream outputStream = new ByteArrayOutputStream();
        CsvWriter writer = new CsvWriter(outputStream, new CsvWriterSettings());

        writer.writeHeaders(dataSet.getHeaders());
        dataSet
                .getRows()
                .stream()
                .map(DataPointRow::getValues)
                .forEach(writer::writeRow);
        writer.close();

        return outputStream;
    }
}
