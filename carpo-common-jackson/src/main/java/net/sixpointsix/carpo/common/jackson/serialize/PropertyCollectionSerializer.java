package net.sixpointsix.carpo.common.jackson.serialize;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import net.sixpointsix.carpo.common.model.PropertyCollection;

import java.io.IOException;

/**
 * Serialize a property collection
 *
 * @author Andrew Tarry
 * @since 0.5.0
 */
public class PropertyCollectionSerializer extends StdSerializer<PropertyCollection> {

    public PropertyCollectionSerializer() {
        this(null);
    }

    protected PropertyCollectionSerializer(Class<PropertyCollection> t) {
        super(t);
    }

    @Override
    public void serialize(PropertyCollection propertyCollection, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {

        jsonGenerator.writeStartArray();
        propertyCollection.forEach(p -> {
            try {
                jsonGenerator.writeObject(p);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        jsonGenerator.writeEndArray();
    }
}
