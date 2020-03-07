package net.sixpointsix.carpo.mi.spring.configuration;

/**
 * Value of an MI data point
 *
 * @author Andrew Tarry
 * @since 0.3.0
 */
public class MIValue {

    /**
     * Property key within the collection
     */
    private String propertyName;

    /**
     * Name of the column in the output file
     */
    private String columnName;

    public String getPropertyName() {
        return propertyName;
    }

    public MIValue setPropertyName(String propertyName) {
        this.propertyName = propertyName;
        return this;
    }

    public String getColumnName() {
        return columnName;
    }

    public MIValue setColumnName(String columnName) {
        this.columnName = columnName;
        return this;
    }
}
