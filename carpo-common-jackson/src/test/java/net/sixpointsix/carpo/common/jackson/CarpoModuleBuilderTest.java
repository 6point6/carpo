package net.sixpointsix.carpo.common.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import net.sixpointsix.carpo.common.model.CarpoPropertyEntity;
import net.sixpointsix.carpo.common.model.Property;
import net.sixpointsix.carpo.common.model.PropertyCollection;
import net.sixpointsix.carpo.common.model.Timestamp;
import net.sixpointsix.carpo.common.model.immutable.ImmutableProperty;
import net.sixpointsix.carpo.common.model.immutable.ImmutableTimestamp;
import net.sixpointsix.carpo.common.model.mutable.MutablePropertyCollection;
import org.apache.poi.util.IOUtils;
import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class CarpoModuleBuilderTest {

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.registerModule(CarpoModuleBuilder.getCarpoModule());
    }

    public static Stream<Arguments> serializeArgs() {
        return Stream.of(
                Arguments.of(ImmutableProperty.build("a", "b"), "example_string"),
                Arguments.of(ImmutableProperty.build("a", 100), "example_long"),
                Arguments.of(ImmutableProperty.build("a", 100.5), "example_double"),
                Arguments.of(ImmutableProperty.build("a", true), "example_boolean")
        );
    }

    @ParameterizedTest
    @MethodSource("serializeArgs")
    void serialize(Property property, String file) throws IOException, JSONException, URISyntaxException {
        CarpoPropertyEntity entity = getEntity(property);

        String output = objectMapper.writeValueAsString(entity);
        Path path = Paths.get(getClass().getClassLoader().getResource("json/" + file + ".json").toURI());
        String expected = Files.readString(path, StandardCharsets.UTF_8);

        JSONAssert.assertEquals(expected, output, JSONCompareMode.LENIENT);
    }

    private CarpoPropertyEntity getEntity(Property property) {
        return new CarpoPropertyEntity() {
            @Override
            public String getCarpoId() {
                return "1234";
            }
            @Override
            public Timestamp getTimestamp() {
                return ImmutableTimestamp.build(
                        LocalDateTime.parse("2020-01-01T12:01:24"),
                        LocalDateTime.parse("2020-01-01T12:01:24")
                );
            }

            @Override
            public PropertyCollection getProperties() {
                MutablePropertyCollection properties = new MutablePropertyCollection();
                properties.add(property);
                return properties;
            }
        };
    }
}