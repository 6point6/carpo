package net.sixpointsix.carpo.serialization.persistence.serializer;

import net.sixpointsix.carpo.common.model.Property;
import net.sixpointsix.carpo.common.model.PropertyType;
import net.sixpointsix.carpo.common.model.immutable.ImmutableProperty;
import net.sixpointsix.carpo.serialization.persistence.SerializedProperty;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class StringSerializerTest {

    public static Stream<Arguments> getCanSerialize() {
        Object nullObject = null;
        return Stream.of(
                Arguments.arguments(ImmutableProperty.build("a", "b"), true),
                Arguments.arguments(ImmutableProperty.build("a", 2L), false),
                Arguments.arguments(ImmutableProperty.build("a", 2.2D), false),
                Arguments.arguments(ImmutableProperty.build("a", false), false),
                Arguments.arguments(ImmutableProperty.build("a", new Object()), false),
                Arguments.arguments(ImmutableProperty.build("a", List.of()), false),
                Arguments.arguments(ImmutableProperty.build("a", nullObject), false),
                Arguments.arguments(null, false)
        );
    }

    @ParameterizedTest
    @MethodSource("getCanSerialize")
    void canSerialize(Property property, boolean expected) {
        StringSerializer stringSerializer = new StringSerializer();

        assertEquals(expected, stringSerializer.canSerialize(property));
    }

    @Test
    void serialize() {
        Property property = ImmutableProperty.build("a", "b");
        StringSerializer stringSerializer = new StringSerializer();

        SerializedProperty serializedProperty = stringSerializer.serialize(property);

        assertEquals(PropertyType.STRING, serializedProperty.getType());
        assertEquals("a", serializedProperty.getKey());
        assertArrayEquals("b".getBytes(), serializedProperty.getData());
    }
}