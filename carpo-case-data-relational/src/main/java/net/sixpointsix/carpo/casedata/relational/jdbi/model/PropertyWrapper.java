package net.sixpointsix.carpo.casedata.relational.jdbi.model;

import net.sixpointsix.carpo.common.model.Property;
import net.sixpointsix.carpo.common.model.PropertyType;

import java.util.UUID;

/**
 * Wrap a Carpo property to make is easier to use JDBI mapping
 *
 * @author Andrew Tarry
 * @since 0.4.0
 */
public class PropertyWrapper {

    private String id = null;
    private final Property property;

    public PropertyWrapper(Property property) {
        this.property = property;
    }

    public String getId() {
        if(property.getId() == null) {
            return getRandomId();
        }
        return property.getId();
    }

    public String getKey() {
        return property.getKey();
    }

    public String getStringValue() {
        return property.getStringValue().orElse(null);
    }

    public Long getLongValue() {
        return property.getLongValue().orElse(null);
    }

    public Double getDoubleValue() {
        return property.getDoubleValue().orElse(null);
    }

    public Boolean getBooleanValue() {
        return property.getBooleanValue().orElse(null);
    }

    public boolean isNull() {
        return property.isNull();
    }

    public PropertyType getType() {
        return property.getType();
    }

    private String getRandomId() {
        if(id == null) {
            id = UUID.randomUUID().toString();
        }
        return id;
    }
}
