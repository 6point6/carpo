package net.sixpointsix.carpo.common.jackson.deserialize;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import net.sixpointsix.carpo.common.jackson.JsonKey;
import net.sixpointsix.carpo.common.model.Property;
import net.sixpointsix.carpo.common.model.PropertyCollection;
import net.sixpointsix.carpo.common.model.PropertyType;
import net.sixpointsix.carpo.common.model.immutable.ImmutableProperty;
import net.sixpointsix.carpo.common.model.mutable.MutablePropertyCollection;

import java.io.IOException;

import static net.sixpointsix.carpo.common.jackson.JsonKey.VALUE_KEY;

/**
 * Parse properties with jackson
 *
 * @since 0.5.0
 * @author Andrew Tarry
 */
public class PropertyDeserializer extends StdDeserializer<PropertyCollection> {

    public PropertyDeserializer() {
        this(null);
    }

    protected PropertyDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public PropertyCollection deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        MutablePropertyCollection collection = new MutablePropertyCollection();

        for (JsonNode object: node) {
            collection.add(buildProperty(object));
        }

        return collection;
    }

    private Property buildProperty(final JsonNode object) {
        String key = object.get(JsonKey.KEY).textValue();
        String type = object.get(JsonKey.TYPE_KEY).textValue();
        PropertyType propertyType = PropertyType.valueOf(type.toUpperCase());
        Property property = null;

        switch (propertyType) {
            case STRING:
                property = ImmutableProperty
                        .build(key, object.get(VALUE_KEY).textValue());
                break;
            case LONG:
                property = ImmutableProperty
                        .build(key, object.get(VALUE_KEY).longValue());
                break;
            case DOUBLE:
                property = ImmutableProperty.build(key,
                        object.get(VALUE_KEY).doubleValue());
                break;
            case BOOLEAN:
                property = ImmutableProperty.build(
                        key, object.get(VALUE_KEY).booleanValue()
                );
                break;
        }

        return property;
    }
}
