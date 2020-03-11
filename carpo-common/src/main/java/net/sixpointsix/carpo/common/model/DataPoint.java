package net.sixpointsix.carpo.common.model;

/**
 * Generic string based data point for extracting values
 *
 * @author Andrew Tarry
 * @since 0.2.0
 */
public class DataPoint {

    /**
     * Header to add to the CSV
     */
    private final String column;

    /**
     * Value to write in the cell
     */
    private final String value;

    public DataPoint(String column) {
        this(column, null);
    }

    public DataPoint(String column, String value) {
        this.column = column;
        this.value = value;
    }

    public String getColumn() {
        return column;
    }

    public String getValue() {
        return value;
    }
}
