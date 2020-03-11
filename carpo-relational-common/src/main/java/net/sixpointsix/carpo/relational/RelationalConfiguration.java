package net.sixpointsix.carpo.relational;

/**
 * Configuration for relational data
 *
 * @author Andrew Tarry
 * @since 0.4.0
 */
public interface RelationalConfiguration {

    /**
     * Get the table that entity needs read
     *
     * @return table name
     */
    String getEntityDataTable();

    /**
     * Get the name of the properties table
     *
     * @return table name
     */
    String getPropertyTable();
}
