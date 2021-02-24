package net.sixpointsix.carpo.common.model;

import java.util.Optional;

public class PropertyMapper {

    Optional<String> getStringValue(PropertyHoldingEntity entity, String path) {

        PropertyCollection propertyCollection = entity.getProperties();
        Optional<Property> subProperty = Optional.empty();
        Optional<String> stringValue = Optional.of("");

        String [] pathArray = path.split("\\.");
        String key;
        for(int i = 0; i < pathArray.length; i++) {
            key = pathArray[i];
            subProperty = propertyCollection.getByKey(key);

            // The value at the end of the path should point to the string value
            if(i == pathArray.length - 1) break;
            // Checks that the value that the subProperty holds is a PropertyCollection
            if(!checkObjectValueIsAPropertyCollection(subProperty)) return stringValue;

            propertyCollection = subProperty.get().getObjectValue(PropertyCollection.class).get();
        }
        // Check that the value that the subProperty holds is a string value
        if(checkPropertyValueIsAString(subProperty)) stringValue = subProperty.get().getStringValue();
        
        return stringValue;
    }

    private boolean checkObjectValueIsAPropertyCollection(Optional<Property> property) {
        if(property.isEmpty()) return false;
        
        // Checks if the objectValue is a PropertyCollection
        Optional<PropertyCollection> propertyCollection = property.get().getObjectValue(PropertyCollection.class);
        if(propertyCollection.isEmpty()) return false;

        return true;
    }

    private boolean checkPropertyValueIsAString(Optional<Property> property) {
        if(property.isEmpty()) return false;
        if(!property.get().hasStringValue()) return false;

        return true;
    }
}