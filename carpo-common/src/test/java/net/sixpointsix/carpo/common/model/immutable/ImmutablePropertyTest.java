package net.sixpointsix.carpo.common.model.immutable;

import net.sixpointsix.carpo.common.model.Property;
import net.sixpointsix.carpo.common.model.PropertyCollection;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ImmutablePropertyTest {

    public static class Example {
        private int a;

        public Example(int a) {
            this.a = a;
        }

        public int getA() {
            return a;
        }
    }

    @Test
    void stringValue() {
        Property property = ImmutableProperty.build("a", "b");

        assertEquals("a", property.getKey());
        assertEquals("b", property.getStringValue().get());

        assertTrue(property.hasStringValue());
        assertFalse(property.hasDoubleValue());
        assertFalse(property.hasLongValue());
        assertFalse(property.hasListValue());
        assertFalse(property.hasObjectValue());
        assertFalse(property.hasBooleanValue());

        assertFalse(property.isNull());

        assertTrue(property.getStringValue().isPresent());
        assertFalse(property.getDoubleValue().isPresent());
        assertFalse(property.getLongValue().isPresent());
        assertFalse(property.getBooleanValue().isPresent());
    }

    @Test
    void doubleValue() {
        double value = 1.2d;
        Property property = ImmutableProperty.build("a", value);

        assertEquals("a", property.getKey());
        assertEquals(value, property.getDoubleValue().get());

        assertFalse(property.hasStringValue());
        assertTrue(property.hasDoubleValue());
        assertFalse(property.hasLongValue());
        assertFalse(property.hasListValue());
        assertFalse(property.hasObjectValue());
        assertFalse(property.hasBooleanValue());

        assertFalse(property.isNull());

        assertFalse(property.getStringValue().isPresent());
        assertTrue(property.getDoubleValue().isPresent());
        assertFalse(property.getLongValue().isPresent());
        assertFalse(property.getBooleanValue().isPresent());
    }

    @Test
    void longValue() {
        long value = 1L;
        Property property = ImmutableProperty.build("a", value);

        assertEquals("a", property.getKey());
        assertEquals(value, property.getLongValue().get());

        assertFalse(property.hasStringValue());
        assertFalse(property.hasDoubleValue());
        assertTrue(property.hasLongValue());
        assertFalse(property.hasBooleanValue());
        assertFalse(property.hasListValue());
        assertFalse(property.hasObjectValue());

        assertFalse(property.isNull());

        assertFalse(property.getStringValue().isPresent());
        assertFalse(property.getDoubleValue().isPresent());
        assertTrue(property.getLongValue().isPresent());
        assertFalse(property.getBooleanValue().isPresent());
    }

    @Test
    void booleanValue() {
        boolean value = true;
        Property property = ImmutableProperty.build("a", value);

        assertEquals("a", property.getKey());
        assertEquals(value, property.getBooleanValue().get());

        assertFalse(property.hasStringValue());
        assertFalse(property.hasDoubleValue());
        assertFalse(property.hasLongValue());
        assertTrue(property.hasBooleanValue());
        assertFalse(property.hasListValue());
        assertFalse(property.hasObjectValue());

        assertFalse(property.isNull());

        assertFalse(property.getStringValue().isPresent());
        assertFalse(property.getDoubleValue().isPresent());
        assertFalse(property.getLongValue().isPresent());
        assertTrue(property.getBooleanValue().isPresent());
    }

    @Test
    void objectValue() {
        Example value = new Example(10);
        Property property = ImmutableProperty.build("a", value);

        assertEquals("a", property.getKey());
        assertEquals(value, property.getObjectValue(Example.class).get());

        assertFalse(property.hasStringValue());
        assertFalse(property.hasDoubleValue());
        assertFalse(property.hasLongValue());
        assertFalse(property.hasBooleanValue());
        assertFalse(property.hasListValue());
        assertTrue(property.hasObjectValue());

        assertFalse(property.isNull());

        assertFalse(property.getStringValue().isPresent());
        assertFalse(property.getDoubleValue().isPresent());
        assertFalse(property.getLongValue().isPresent());
        assertFalse(property.getBooleanValue().isPresent());
    }

    @Test
    void propertyValue() {
        Example value = new Example(10);
        Property secondaryProperty = ImmutableProperty.build("b", value);
        Property primaryProperty = ImmutableProperty.build("a", secondaryProperty);

        assertEquals("a", primaryProperty.getKey());
        assertTrue(primaryProperty.getObjectValue(PropertyCollection.class).isPresent());

        PropertyCollection expectedPropertyCollection = primaryProperty.getObjectValue(PropertyCollection.class).get();
        Property expectedProperty = expectedPropertyCollection.getByKey("b").get();
        assertEquals(secondaryProperty, expectedProperty);

        assertFalse(primaryProperty.hasStringValue());
        assertFalse(primaryProperty.hasDoubleValue());
        assertFalse(primaryProperty.hasLongValue());
        assertFalse(primaryProperty.hasBooleanValue());
        assertFalse(primaryProperty.hasListValue());
        assertTrue(primaryProperty.hasObjectValue());

        assertFalse(primaryProperty.isNull());

        assertFalse(primaryProperty.getStringValue().isPresent());
        assertFalse(primaryProperty.getDoubleValue().isPresent());
        assertFalse(primaryProperty.getLongValue().isPresent());
        assertFalse(primaryProperty.getBooleanValue().isPresent());
    }

    @Test
    void wrongObjectValue() {
        Example value = new Example(10);
        Property property = ImmutableProperty.build("a", value);

        assertEquals("a", property.getKey());
        assertFalse(property.getObjectValue(String.class).isPresent());

        assertFalse(property.hasStringValue());
        assertFalse(property.hasDoubleValue());
        assertFalse(property.hasLongValue());
        assertFalse(property.hasBooleanValue());
        assertFalse(property.hasListValue());
        assertTrue(property.hasObjectValue());

        assertFalse(property.isNull());

        assertFalse(property.getStringValue().isPresent());
        assertFalse(property.getDoubleValue().isPresent());
        assertFalse(property.getLongValue().isPresent());
        assertFalse(property.getBooleanValue().isPresent());
    }

    @Test
    void wrongNullObjectValue() {
        Example value = null;
        Property property = ImmutableProperty.build("a", value);

        assertEquals("a", property.getKey());
        assertFalse(property.getObjectValue(String.class).isPresent());

        assertFalse(property.hasStringValue());
        assertFalse(property.hasDoubleValue());
        assertFalse(property.hasLongValue());
        assertFalse(property.hasBooleanValue());
        assertFalse(property.hasListValue());
        assertFalse(property.hasObjectValue());

        assertTrue(property.isNull());

        assertFalse(property.getStringValue().isPresent());
        assertFalse(property.getDoubleValue().isPresent());
        assertFalse(property.getLongValue().isPresent());
        assertFalse(property.getBooleanValue().isPresent());
    }

    @Test
    void listValue() {
        Example value = new Example(10);
        Property property = ImmutableProperty.build("a", List.of(value));

        assertEquals(value, property.getListValue(Example.class).get(0));

        assertFalse(property.hasStringValue());
        assertFalse(property.hasDoubleValue());
        assertFalse(property.hasLongValue());
        assertFalse(property.hasBooleanValue());
        assertTrue(property.hasListValue());
        assertFalse(property.hasObjectValue());

        assertFalse(property.isNull());

        assertFalse(property.getStringValue().isPresent());
        assertFalse(property.getDoubleValue().isPresent());
        assertFalse(property.getLongValue().isPresent());
        assertFalse(property.getBooleanValue().isPresent());
    }

    @Test
    void emptyListValue() {
        Property property = ImmutableProperty.build("a", List.of());

        assertFalse(property.hasStringValue());
        assertFalse(property.hasDoubleValue());
        assertFalse(property.hasLongValue());
        assertFalse(property.hasBooleanValue());
        assertTrue(property.hasListValue());
        assertFalse(property.hasObjectValue());

        assertFalse(property.isNull());

        assertFalse(property.getStringValue().isPresent());
        assertFalse(property.getDoubleValue().isPresent());
        assertFalse(property.getLongValue().isPresent());
        assertFalse(property.getBooleanValue().isPresent());
        assertTrue(property.getListValue(Object.class).isEmpty());

    }

    @Test
    void wrongListValue() {
        Example value = new Example(10);
        Property property = ImmutableProperty.build("a", List.of(value));

        assertTrue(property.getListValue(String.class).isEmpty());

        assertFalse(property.hasStringValue());
        assertFalse(property.hasDoubleValue());
        assertFalse(property.hasLongValue());
        assertFalse(property.hasBooleanValue());
        assertTrue(property.hasListValue());
        assertFalse(property.hasObjectValue());

        assertFalse(property.getStringValue().isPresent());
        assertFalse(property.getDoubleValue().isPresent());
        assertFalse(property.getLongValue().isPresent());
        assertFalse(property.getBooleanValue().isPresent());
    }
}