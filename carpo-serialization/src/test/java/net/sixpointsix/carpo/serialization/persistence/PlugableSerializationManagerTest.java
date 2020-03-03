package net.sixpointsix.carpo.serialization.persistence;

import net.sixpointsix.carpo.common.model.Property;
import net.sixpointsix.carpo.common.model.PropertyType;
import net.sixpointsix.carpo.common.model.immutable.ImmutableProperty;
import net.sixpointsix.carpo.serialization.exception.NoSerializerException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class PlugableSerializationManagerTest {

    private static class Example implements Serializable {
        public int value = 2;
    }

    private static class Example2 {
        public int value = 2;
    }

    public static Stream<Arguments> getCanSerialize() {
        Object nullObject = null;
        return Stream.of(
                Arguments.arguments(ImmutableProperty.build("a", "b"), PropertyType.STRING),
                Arguments.arguments(ImmutableProperty.build("a", 2L), PropertyType.LONG),
                Arguments.arguments(ImmutableProperty.build("a", 2.2D), PropertyType.DOUBLE),
                Arguments.arguments(ImmutableProperty.build("a", List.of(new Example())), PropertyType.LIST),
                Arguments.arguments(ImmutableProperty.build("a", nullObject), PropertyType.NULL),
                Arguments.arguments(ImmutableProperty.build("a", new Example()), PropertyType.OBJECT)
        );
    }

    public static Stream<Arguments> cannotSerialize() {
        return Stream.of(
                Arguments.arguments(ImmutableProperty.build("a", new Object())),
                Arguments.arguments(ImmutableProperty.build("a", new Example2())),
                Arguments.arguments(ImmutableProperty.build("a", List.of()))

                );
    }

    @ParameterizedTest
    @MethodSource("getCanSerialize")
    void serializeWithDefaults(Property property, PropertyType type) {
        PersistenceSerializationManger persistenceSerializationManger = PlugableSerializationManager.buildWithDefaultSerializers();
        SerializedProperty serializedProperty = persistenceSerializationManger.serializeProperty(property);

        assertEquals("a", serializedProperty.getKey());
        assertEquals(type, serializedProperty.getType());
    }

    @ParameterizedTest
    @MethodSource("cannotSerialize")
    void canNotserializeWithDefaults(Property property) {
        PersistenceSerializationManger persistenceSerializationManger = PlugableSerializationManager.buildWithDefaultSerializers();

        assertThrows(NoSerializerException.class, () -> persistenceSerializationManger.serializeProperty(property));
    }
}