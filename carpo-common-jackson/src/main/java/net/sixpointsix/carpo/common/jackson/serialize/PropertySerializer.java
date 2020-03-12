package net.sixpointsix.carpo.common.jackson.serialize;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import net.sixpointsix.carpo.common.jackson.JsonKey;
import net.sixpointsix.carpo.common.model.Property;

import java.io.IOException;

/**
 * Serialize a Carpo property object
 *
 * @since 0.5.0
 * @author Andrew Tarry
 */
public class PropertySerializer extends StdSerializer<Property> {

    public PropertySerializer() {
        this(null);
    }

    protected PropertySerializer(Class<Property> t) {
        super(t);
    }

    @Override
    public void serialize(Property property, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField(JsonKey.KEY, property.getKey());
        jsonGenerator.writeStringField(JsonKey.TYPE_KEY, property.getType().name());

        if(property.hasStringValue()) {
            jsonGenerator.writeStringField(JsonKey.VALUE_KEY, property.getStringValue().get());
        }

        if(property.hasLongValue()) {
            jsonGenerator.writeNumberField(JsonKey.VALUE_KEY, property.getLongValue().get());
        }

        if(property.hasDoubleValue()) {
            jsonGenerator.writeNumberField(JsonKey.VALUE_KEY, property.getDoubleValue().get());
        }

        if(property.hasBooleanValue()) {
            jsonGenerator.writeBooleanField(JsonKey.VALUE_KEY, property.getBooleanValue().get());
        }

        jsonGenerator.writeEndObject();
    }


}
