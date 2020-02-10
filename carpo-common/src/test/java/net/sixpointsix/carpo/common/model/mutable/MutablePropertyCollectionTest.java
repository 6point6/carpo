package net.sixpointsix.carpo.common.model.mutable;

import net.sixpointsix.carpo.common.model.Property;
import net.sixpointsix.carpo.common.model.immutable.ImmutableProperty;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MutablePropertyCollectionTest {

    private static class Example {
        public String x;
    }

    @Test
    void getNoneByKey() {
        MutablePropertyCollection mutablePropertyCollection = new MutablePropertyCollection();

        assertFalse(mutablePropertyCollection.hasPropertyByKey("a"));
        assertFalse(mutablePropertyCollection.getByKey("a").isPresent());
    }

    @Test
    void getNoneByNullKey() {
        MutablePropertyCollection mutablePropertyCollection = new MutablePropertyCollection();
        mutablePropertyCollection.add(ImmutableProperty.build("a", "b"));

        assertFalse(mutablePropertyCollection.hasPropertyByKey(null));
        assertFalse(mutablePropertyCollection.getByKey(null).isPresent());
    }

    @Test
    void getStringByKey() {
        Property property = ImmutableProperty.build("a", "b");
        MutablePropertyCollection mutablePropertyCollection = new MutablePropertyCollection();
        mutablePropertyCollection.add(property);

        assertTrue(mutablePropertyCollection.hasPropertyByKey("a"));
        assertEquals("b", mutablePropertyCollection.getByKey("a").get().getStringValue().get());
        assertEquals("b", mutablePropertyCollection.getStringByKey("a").get());
        assertFalse(mutablePropertyCollection.getLongByKey("b").isPresent());
    }

    @Test
    void getLongByKey() {
        Property property = ImmutableProperty.build("a", 10L);
        MutablePropertyCollection mutablePropertyCollection = new MutablePropertyCollection();
        mutablePropertyCollection.add(property);

        assertTrue(mutablePropertyCollection.hasPropertyByKey("a"));
        assertEquals(10L, mutablePropertyCollection.getLongByKey("a").get());
    }

    @Test
    void getDoubleByKey() {
        Property property = ImmutableProperty.build("a", 10.1d);
        MutablePropertyCollection mutablePropertyCollection = new MutablePropertyCollection();
        mutablePropertyCollection.add(property);

        assertTrue(mutablePropertyCollection.hasPropertyByKey("a"));
        assertEquals(10.1d, mutablePropertyCollection.getDoubleByKey("a").get());
    }

    @Test
    void getBooleanByKey() {
        Property property = ImmutableProperty.build("a", true);
        MutablePropertyCollection mutablePropertyCollection = new MutablePropertyCollection();
        mutablePropertyCollection.add(property);

        assertTrue(mutablePropertyCollection.hasPropertyByKey("a"));
        assertTrue(mutablePropertyCollection.getBooleanByKey("a").get());
    }

    @Test
    void getObjectByKey() {
        Example example = new Example();
        example.x = "a";
        Property property = ImmutableProperty.build("a", example);
        MutablePropertyCollection mutablePropertyCollection = new MutablePropertyCollection();
        mutablePropertyCollection.add(property);

        assertTrue(mutablePropertyCollection.hasPropertyByKey("a"));
        assertEquals(example, mutablePropertyCollection.getObjectByKey("a", Example.class).get());
    }

    @Test
    void getOtherObjectByKey() {
        Object example = new Object();
        Property property = ImmutableProperty.build("a", example);
        MutablePropertyCollection mutablePropertyCollection = new MutablePropertyCollection();
        mutablePropertyCollection.add(property);

        assertTrue(mutablePropertyCollection.hasPropertyByKey("a"));
        assertFalse(mutablePropertyCollection.getObjectByKey("a", Example.class).isPresent());
    }

    @Test
    void getListObjectByKey() {
        Example example = new Example();
        example.x = "a";

        Property property = ImmutableProperty.build("a", List.of(example));
        MutablePropertyCollection mutablePropertyCollection = new MutablePropertyCollection();
        mutablePropertyCollection.add(property);

        assertTrue(mutablePropertyCollection.hasPropertyByKey("a"));
        assertEquals(1, mutablePropertyCollection.getListByKey("a", Example.class).size());
        assertEquals(example, mutablePropertyCollection.getListByKey("a", Example.class).get(0));
    }
}