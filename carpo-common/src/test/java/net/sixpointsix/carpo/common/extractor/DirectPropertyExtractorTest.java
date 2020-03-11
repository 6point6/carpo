package net.sixpointsix.carpo.common.extractor;

import net.sixpointsix.carpo.common.model.Property;
import net.sixpointsix.carpo.common.model.PropertyHoldingEntity;
import net.sixpointsix.carpo.common.model.immutable.ImmutableProperty;
import net.sixpointsix.carpo.common.model.mutable.MutablePropertyCollection;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class DirectPropertyExtractorTest {

    private static class Example {

        public String getValue() {
            return "b";
        }
    }

    public static Stream<Arguments> getDataArgument() {
        return Stream.of(
                Arguments.arguments(ImmutableProperty.build("a", 10L), "10"),
                Arguments.arguments(ImmutableProperty.build("a", 10.2D), "10.2"),
                Arguments.arguments(ImmutableProperty.build("a", "b"), "b"),
                Arguments.arguments(ImmutableProperty.build("a", true), "true"),
                Arguments.arguments(ImmutableProperty.build("a", false), "false"),
                Arguments.arguments(ImmutableProperty.build("a", new Object()), null),
                Arguments.arguments(ImmutableProperty.build("a", List.of()), null)
        );
    }

    @ParameterizedTest
    @MethodSource("getDataArgument")
    void getDataPoint(Property property, String expected) {
        MutablePropertyCollection mutablePropertyCollection = new MutablePropertyCollection();
        mutablePropertyCollection.add(property);

        DirectPropertyExtractor extractor = new DirectPropertyExtractor();

        assertEquals(expected, extractor.getDatapoint(mutablePropertyCollection, "a"));
    }

    @ParameterizedTest
    @MethodSource("getDataArgument")
    void getDataPointFromEntity(Property property, String expected) {
        MutablePropertyCollection mutablePropertyCollection = new MutablePropertyCollection();
        mutablePropertyCollection.add(property);

        PropertyHoldingEntity entity = () -> mutablePropertyCollection;

        DirectPropertyExtractor extractor = new DirectPropertyExtractor();

        assertEquals(expected, extractor.getDatapoint(entity, "a"));
    }

    @Test
    void getDataFromObject() {
        Example example = new Example();
        Property property = ImmutableProperty.build("a", example);
        MutablePropertyCollection mutablePropertyCollection = new MutablePropertyCollection();
        mutablePropertyCollection.add(property);

        DirectPropertyExtractor extractor = new DirectPropertyExtractor();

        assertEquals("b", extractor.getDatapoint(mutablePropertyCollection, "a", Example.class, Example::getValue));
    }

    @Test
    void getDataFromNullObject() {
        Example example = null;
        Property property = ImmutableProperty.build("a", example);
        MutablePropertyCollection mutablePropertyCollection = new MutablePropertyCollection();
        mutablePropertyCollection.add(property);

        DirectPropertyExtractor extractor = new DirectPropertyExtractor();

        assertNull(extractor.getDatapoint(mutablePropertyCollection, "a", Example.class, Example::getValue));
    }

    @Test
    void getDataFromOtherValue() {
        Property property = ImmutableProperty.build("a", true);
        MutablePropertyCollection mutablePropertyCollection = new MutablePropertyCollection();
        mutablePropertyCollection.add(property);

        DirectPropertyExtractor extractor = new DirectPropertyExtractor();

        assertNull(extractor.getDatapoint(mutablePropertyCollection, "a", Example.class, Example::getValue));
    }

    @Test
    void getDataFromObjectWithEntity() {
        Example example = new Example();
        Property property = ImmutableProperty.build("a", example);
        MutablePropertyCollection mutablePropertyCollection = new MutablePropertyCollection();
        mutablePropertyCollection.add(property);
        PropertyHoldingEntity entity = () -> mutablePropertyCollection;

        DirectPropertyExtractor extractor = new DirectPropertyExtractor();

        assertEquals("b", extractor.getDatapoint(entity, "a", Example.class, Example::getValue));
    }

    @Test
    void getNullFromWrongObjectWithEntity() {
        Object example = new Object();
        Property property = ImmutableProperty.build("a", example);
        MutablePropertyCollection mutablePropertyCollection = new MutablePropertyCollection();
        mutablePropertyCollection.add(property);
        PropertyHoldingEntity entity = () -> mutablePropertyCollection;

        DirectPropertyExtractor extractor = new DirectPropertyExtractor();

        assertNull(extractor.getDatapoint(entity, "a", Example.class, Example::getValue));
    }

    @Test
    void getDataFromObjectInList() {
        Example example = new Example();
        Property property = ImmutableProperty.build("a", List.of(example));
        MutablePropertyCollection mutablePropertyCollection = new MutablePropertyCollection();
        mutablePropertyCollection.add(property);

        DirectPropertyExtractor extractor = new DirectPropertyExtractor();

        assertEquals("b", extractor.getDatapoint(mutablePropertyCollection, "a", Example.class, Example::getValue, 0));
    }

    @Test
    void getNullWithoutList() {
        Example example = new Example();
        Property property = ImmutableProperty.build("a", example);
        MutablePropertyCollection mutablePropertyCollection = new MutablePropertyCollection();
        mutablePropertyCollection.add(property);

        DirectPropertyExtractor extractor = new DirectPropertyExtractor();

        assertNull(extractor.getDatapoint(mutablePropertyCollection, "a", Example.class, Example::getValue, 0));
    }

    @Test
    void getNullWithInvalidIndex() {
        Example example = new Example();
        Property property = ImmutableProperty.build("a", List.of(example));
        MutablePropertyCollection mutablePropertyCollection = new MutablePropertyCollection();
        mutablePropertyCollection.add(property);

        DirectPropertyExtractor extractor = new DirectPropertyExtractor();

        assertNull(extractor.getDatapoint(mutablePropertyCollection, "a", Example.class, Example::getValue, 9999));
    }

    @Test
    void getNullWithWrongType() {
        Object example = new Object();
        Property property = ImmutableProperty.build("a", List.of(example));
        MutablePropertyCollection mutablePropertyCollection = new MutablePropertyCollection();
        mutablePropertyCollection.add(property);

        DirectPropertyExtractor extractor = new DirectPropertyExtractor();

        assertNull(extractor.getDatapoint(mutablePropertyCollection, "a", Example.class, Example::getValue, 0));
    }
}