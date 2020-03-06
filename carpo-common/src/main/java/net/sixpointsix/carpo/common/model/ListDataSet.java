package net.sixpointsix.carpo.common.model;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Immutable data set to be used in a data parser
 *
 * @author Andrew Tarry
 * @since 0.2.0
 */
public class ListDataSet implements DataSet {

    private final List<DataPointRow> dataPointRowList;

    public ListDataSet(List<DataPointRow> dataPointRowList) {
        this.dataPointRowList = dataPointRowList;
    }

    /**
     * Get the list of rows
     * @return rows in the set
     */
    @Override
    public List<DataPointRow> getRows() {
        return dataPointRowList;
    }

    /**
     * Get all the headers in the data set
     *
     * <p>
     *     This method assumes the headers are consistent since a csv could not support anything else
     * </p>
     * @return list of the headers
     */
    @Override
    public List<String> getHeaders() {
        if(dataPointRowList == null || dataPointRowList.isEmpty()) {
            return List.of();
        }

        return dataPointRowList
                .get(0)
                .stream()
                .map(DataPoint::getColumn)
                .collect(Collectors.toList());
    }

    /**
     * Test if the list of rows is empty
     * @return true if empty
     */
    @Override
    public boolean isEmpty() {
        return dataPointRowList.isEmpty();
    }
}
