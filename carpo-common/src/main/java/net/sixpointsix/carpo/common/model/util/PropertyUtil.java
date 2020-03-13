package net.sixpointsix.carpo.common.model.util;

import net.sixpointsix.carpo.common.model.Property;

public class PropertyUtil {

    public static Object getValue(Property property) {
        Object value = null;
        switch (property.getType()) {
            case STRING:
                value = property.getStringValue().get();
                break;
            case BOOLEAN:
                value = property.getBooleanValue().get();
                break;
            case LONG:
                value = property.getLongValue().get();
                break;
            case DOUBLE:
                value = property.getDoubleValue().get();
                break;
            case OBJECT:
                value = property.getObjectValue(Object.class).get();
                break;
            case LIST:
                value = property.getListValue(Object.class);
                break;
        }

        return value;
    }
}
