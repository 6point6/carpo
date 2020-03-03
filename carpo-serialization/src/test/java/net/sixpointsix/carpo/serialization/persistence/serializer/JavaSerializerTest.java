package net.sixpointsix.carpo.serialization.persistence.serializer;

import net.sixpointsix.carpo.common.model.Property;
import net.sixpointsix.carpo.common.model.PropertyType;
import net.sixpointsix.carpo.common.model.immutable.ImmutableProperty;
import net.sixpointsix.carpo.serialization.persistence.SerializedProperty;
import org.apache.commons.lang3.SerializationUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class JavaSerializerTest {

    private static class Example implements Serializable {
        public int value = 2;
    }

    private static class Example2 {
        public int value = 2;
    }

    public static Stream<Arguments> getCanSerialize() {
        Object nullObject = null;
        return Stream.of(
                Arguments.arguments(ImmutableProperty.build("a", "b"), false),
                Arguments.arguments(ImmutableProperty.build("a", 2L), false),
                Arguments.arguments(ImmutableProperty.build("a", 2.2D), false),
                Arguments.arguments(ImmutableProperty.build("a", false), false),
                Arguments.arguments(ImmutableProperty.build("a", new Object()), false),
                Arguments.arguments(ImmutableProperty.build("a", new Example2()), false),
                Arguments.arguments(ImmutableProperty.build("a", List.of()), false),
                Arguments.arguments(ImmutableProperty.build("a", nullObject), false),
                Arguments.arguments(null, false),
                Arguments.arguments(ImmutableProperty.build("a", new Example()), true)
        );
    }

    @ParameterizedTest
    @MethodSource("getCanSerialize")
    void canSerialize(Property property, boolean expected) {
        JavaSerializer javaSerializer = new JavaSerializer();

        assertEquals(expected, javaSerializer.canSerialize(property));
    }

    @Test
    void serialize() {
        Example example = new Example();
        example.value = 4;
        JavaSerializer javaSerializer = new JavaSerializer();
        SerializedProperty serializedProperty = javaSerializer.serialize(ImmutableProperty.build("a", example));

        assertEquals("a", serializedProperty.getKey());
        assertEquals(PropertyType.OBJECT, serializedProperty.getType());
        assertTrue(serializedProperty.getData().length > 0);

        Example deserialized = SerializationUtils.deserialize(serializedProperty.getData());

        assertEquals(4, deserialized.value);
    }

    @Test
    void nonSerialize() {
        Example2 example = new Example2();
        example.value = 4;

        JavaSerializer javaSerializer = new JavaSerializer();
        SerializedProperty serializedProperty = javaSerializer.serialize(ImmutableProperty.build("a", example));

        assertEquals("a", serializedProperty.getKey());
        assertEquals(PropertyType.OBJECT, serializedProperty.getType());
        assertTrue(serializedProperty.getData().length > 0);

        assertNull(SerializationUtils.deserialize(serializedProperty.getData()));
    }

    @Test
    void getPriority() {
        assertEquals(2000, new JavaSerializer().getPriority());
    }
}