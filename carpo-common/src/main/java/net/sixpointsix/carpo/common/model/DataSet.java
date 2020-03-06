package net.sixpointsix.carpo.common.model;

import java.util.List;

public interface DataSet {
    /**
     * Get an empty data set
     * @return empty data set
     */
    static DataSet empty() {
        return new ListDataSet(List.of());
    }

    /**
     * Get the list of rows
     * @return rows in the set
     */
    List<DataPointRow> getRows();

    /**
     * Get all the headers in the data set
     *
     * <p>
     *     This method assumes the headers are consistent since a csv could not support anything else
     * </p>
     * @return list of the headers
     */
    List<String> getHeaders();

    /**
     * Test if the list of rows is empty
     * @return true if empty
     */
    boolean isEmpty();
}
