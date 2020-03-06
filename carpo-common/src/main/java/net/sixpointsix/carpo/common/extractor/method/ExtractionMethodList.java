package net.sixpointsix.carpo.common.extractor.method;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * Group of properties to be used to extract a dataset from an entity
 *
 * @author Andrew Tarry
 * @since 0.2.0
 */
public class ExtractionMethodList implements ReadOnlyExtractionMethodList {

    private final List<DataElement<?>> elements = new ArrayList<>();

    /**
     * Add a new extraction to the set
     * @param rowHeader header
     * @param value value to get from the collection
     * @return this
     */
    public ExtractionMethodList add(String rowHeader, String value) {
        elements.add(new DataElement<>(rowHeader, value));

        return this;
    }

    /**
     * Add an extraction to get a value from an object
     * @param rowHeader header
     * @param value value to get from the collection
     * @param propertyType expected type of the object
     * @param extractor function to get the string value
     * @param <T> expected type
     * @return this
     */
    public <T> ExtractionMethodList add(String rowHeader, String value, Class<T> propertyType, Function<T, String> extractor) {
        return add(rowHeader, value, propertyType, extractor, null);
    }

    /**
     * Add an extraction to get a value from an object
     * @param rowHeader header
     * @param value value to get from the collection
     * @param propertyType expected type of the object
     * @param extractor function to get the string value
     * @param index index of the value to get
     * @param <T> expected type
     * @return this
     */
    public <T> ExtractionMethodList add(String rowHeader, String value, Class<T> propertyType, Function<T, String> extractor, Integer index) {
        elements.add(new DataElement<>(rowHeader, value, propertyType, extractor, index));

        return this;
    }

    /**
     * Get the size of the collection
     * @return collection size
     */
    @Override
    public int size() {
        return elements.size();
    }

    /**
     * Test if the method list is empty
     * @return true if empty
     */
    @Override
    public boolean isEmpty() {
        return elements.isEmpty();
    }

    @Override
    public Stream<DataElement<?>> stream() {
        return elements.stream();
    }

}
