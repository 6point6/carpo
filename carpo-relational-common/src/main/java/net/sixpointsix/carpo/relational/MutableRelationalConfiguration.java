package net.sixpointsix.carpo.relational;

/**
 * Configuration for jdbi relation management
 *
 * @author Andrew Tarry
 * @since 0.4.0
 */
public class MutableRelationalConfiguration implements RelationalConfiguration {

    private String entityDataTable;
    private String propertyTable;

    @Override
    public String getEntityDataTable() {
        return entityDataTable;
    }

    public RelationalConfiguration setEntityDataTable(String entityDataTable) {
        this.entityDataTable = entityDataTable;
        return this;
    }

    @Override
    public String getPropertyTable() {
        return propertyTable;
    }

    public RelationalConfiguration setPropertyTable(String propertyTable) {
        this.propertyTable = propertyTable;
        return this;
    }
}
