package net.sixpointsix.carpo.serialization.persistence.serializer;

import com.google.common.primitives.Longs;
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

class LongSerializerTest {

    public static Stream<Arguments> getCanSerialize() {
        Object nullObject = null;
        return Stream.of(
                Arguments.arguments(ImmutableProperty.build("a", "b"), false),
                Arguments.arguments(ImmutableProperty.build("a", 2L), true),
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
        LongSerializer longSerializer = new LongSerializer();

        assertEquals(expected, longSerializer.canSerialize(property));
    }

    @Test
    void serialize() {
        Property property = ImmutableProperty.build("a", 10L);
        LongSerializer longSerializer = new LongSerializer();

        SerializedProperty serializedProperty = longSerializer.serialize(property);

        assertEquals(PropertyType.LONG, serializedProperty.getType());
        assertEquals("a", serializedProperty.getKey());
        assertArrayEquals(Longs.toByteArray(10L), serializedProperty.getData());
    }

    @Test
    void nullSerialize() {
        Property property = ImmutableProperty.build("a", "other");
        LongSerializer longSerializer = new LongSerializer();

        SerializedProperty serializedProperty = longSerializer.serialize(property);

        assertEquals(PropertyType.LONG, serializedProperty.getType());
        assertEquals("a", serializedProperty.getKey());
        assertNull(serializedProperty.getData());
    }

}