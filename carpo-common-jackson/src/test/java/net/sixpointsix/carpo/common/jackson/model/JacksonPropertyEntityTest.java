package net.sixpointsix.carpo.common.jackson.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import net.sixpointsix.carpo.common.model.PropertyCollection;
import net.sixpointsix.carpo.common.model.Timestamp;
import net.sixpointsix.carpo.common.model.immutable.ImmutableTimestamp;
import net.sixpointsix.carpo.common.model.mutable.MutablePropertyCollection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class JacksonPropertyEntityTest {

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Test
    void buildEntity() {
        String id = UUID.randomUUID().toString();
        Timestamp timestamp = ImmutableTimestamp.build();
        PropertyCollection properties = new MutablePropertyCollection();

        JacksonPropertyEntity jacksonPropertyEntity = new JacksonPropertyEntity(id, timestamp, properties);

        assertEquals(id, jacksonPropertyEntity.getCarpoId());
        assertEquals(timestamp, jacksonPropertyEntity.getTimestamp());
        assertEquals(properties, jacksonPropertyEntity.getProperties());
    }

    @Test
    void buildWithJackson() throws IOException {
        JacksonPropertyEntity jacksonPropertyEntity = getEntity("example_string.json");

        assertEquals(1, jacksonPropertyEntity.getProperties().size());
        assertEquals("b", jacksonPropertyEntity.getProperties().getStringByKey("a").get());
    }

    @Test
    void buildWithLong() throws IOException {
        JacksonPropertyEntity jacksonPropertyEntity = getEntity("example_long.json");

        assertEquals(1, jacksonPropertyEntity.getProperties().size());
        assertEquals(100, jacksonPropertyEntity.getProperties().getLongByKey("a").get());
    }

    @Test
    void buildWithDouble() throws IOException {
        JacksonPropertyEntity jacksonPropertyEntity = getEntity("example_double.json");

        assertEquals(1, jacksonPropertyEntity.getProperties().size());
        assertEquals(100.5, jacksonPropertyEntity.getProperties().getDoubleByKey("a").get());
    }

    @Test
    void buildWithBoolean() throws IOException {
        JacksonPropertyEntity jacksonPropertyEntity = getEntity("example_boolean.json");

        assertEquals(1, jacksonPropertyEntity.getProperties().size());
        assertTrue(jacksonPropertyEntity.getProperties().getBooleanByKey("a").get());
    }

    private JacksonPropertyEntity getEntity(String fileName) throws IOException {
        InputStream json = getClass().getClassLoader().getResourceAsStream("json/" + fileName);
        JacksonPropertyEntity jacksonPropertyEntity = objectMapper.readValue(json, JacksonPropertyEntity.class);

        assertEquals("1234", jacksonPropertyEntity.getCarpoId());

        return jacksonPropertyEntity;
    }
}