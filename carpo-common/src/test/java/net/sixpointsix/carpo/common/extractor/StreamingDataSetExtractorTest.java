package net.sixpointsix.carpo.common.extractor;

import net.sixpointsix.carpo.common.extractor.method.ExtractionMethodList;
import net.sixpointsix.carpo.common.model.DataSet;
import net.sixpointsix.carpo.common.model.Property;
import net.sixpointsix.carpo.common.model.PropertyHoldingEntity;
import net.sixpointsix.carpo.common.model.immutable.ImmutableProperty;
import net.sixpointsix.carpo.common.model.mutable.MutablePropertyCollection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class StreamingDataSetExtractorTest {

    private StreamingDataSetExtractor streamingDatasetExtractor;

    public static Stream<Arguments> invalidInput() {
        return Stream.of(
                Arguments.arguments(null, null),
                Arguments.arguments(List.of(), null),
                Arguments.arguments(List.of(mock(PropertyHoldingEntity.class)), null),
                Arguments.arguments(null, mock(ExtractionMethodList.class)),
                Arguments.arguments(List.of(), mock(ExtractionMethodList.class))
        );
    }

    @BeforeEach
    void setUp() {
        streamingDatasetExtractor = new StreamingDataSetExtractor(new DirectPropertyExtractor());
    }

    @ParameterizedTest
    @MethodSource("invalidInput")
    void invalidInputTest(List<PropertyHoldingEntity> data, ExtractionMethodList extractionMethodList) {
        DataSet output = streamingDatasetExtractor.getData(data, extractionMethodList);

        assertTrue(output.isEmpty());
    }

    @Test
    void getData() {
        Property stringProperty = ImmutableProperty.build("a", "b");
        Property objectProperty = ImmutableProperty.build("b", new TestClass());

        PropertyHoldingEntity entity = buildEntity(stringProperty, objectProperty);
        PropertyHoldingEntity entity2 = buildEntity(stringProperty, objectProperty);
        PropertyHoldingEntity entity3 = buildEntity(stringProperty, objectProperty);

        ExtractionMethodList extractionMethodList = new ExtractionMethodList();
        extractionMethodList.add("a", "a");
        extractionMethodList.add("b", "b", TestClass.class, t -> t.val);

        DataSet dataSet = streamingDatasetExtractor.getData(List.of(entity, entity2, entity3), extractionMethodList);

        assertFalse(dataSet.isEmpty());
        assertEquals("a", dataSet.getRows().get(0).getDataPoints().get(0).getColumn());
        assertEquals("b", dataSet.getRows().get(0).getDataPoints().get(0).getValue());
    }

    private static class TestClass {
        public final String val = "x";
    }

    private PropertyHoldingEntity buildEntity(Property... props) {
        return () -> {
            MutablePropertyCollection collection = new MutablePropertyCollection();
            collection.addAll(Arrays.asList(props));

            return collection;
        };
    }
}