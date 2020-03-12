package net.sixpointsix.carpo.common.jackson.serialize;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import net.sixpointsix.carpo.common.jackson.JsonKey;
import net.sixpointsix.carpo.common.model.Timestamp;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Serialize timestamps into a standard form
 *
 * @author Andrew Tarry
 * @since 0.5.0
 */
public class TimestampSerializer extends StdSerializer<Timestamp> {

    public TimestampSerializer() {
        this(null);
    }

    public TimestampSerializer(Class<Timestamp> t) {
        super(t);
    }

    @Override
    public void serialize(Timestamp timestamp, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField(JsonKey.CREATED_AT, format(timestamp.getCreatedAt()));
        jsonGenerator.writeStringField(JsonKey.LAST_UPDATED, format(timestamp.getLastUpdated()));
        jsonGenerator.writeEndObject();
    }

    private String format(LocalDateTime localDateTime) {
        return DateTimeFormatter.ISO_DATE_TIME.format(localDateTime);
    }
}
