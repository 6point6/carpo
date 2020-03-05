package net.sixpointsix.carpo.common.extractor.method;

import java.util.stream.Stream;

/**
 * Read only extraction methods
 *
 * @author Andrew Tarry
 * @since 0.2.0
 */
public interface ReadOnlyExtractionMethodList {

    /**
     * Get the size of the collection
     * @return collection size
     */
    int size();

    /**
     * Test if the method list is empty
     * @return true if empty
     */
    boolean isEmpty();

    Stream<DataElement<?>> stream();

}
