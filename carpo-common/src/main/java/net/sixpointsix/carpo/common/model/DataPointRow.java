package net.sixpointsix.carpo.common.model;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Wrapper for a row of data in the extraction
 *
 * @author Andrew Tarry
 * @since 0.2.0
 */
public class DataPointRow {

    private final List<DataPoint> dataPoints;

    public DataPointRow(List<DataPoint> dataPoints) {
        this.dataPoints = dataPoints;
    }

    /**
     * Get the data as a list
     * @return list of data
     */
    public List<DataPoint> getDataPoints() {
        return dataPoints;
    }

    /**
     * Get the data as a stream
     * @return stream of data
     */
    public Stream<DataPoint> stream() {
        return dataPoints.stream();
    }

    /**
     * Get all the values from the data points
     * @return list of values
     */
    public List<String> getValues() {
        return stream()
                .map(DataPoint::getValue)
                .collect(Collectors.toList());
    }
}
