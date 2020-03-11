package net.sixpointsix.carpo.mi;

import net.sixpointsix.carpo.common.extractor.method.ReadOnlyExtractionMethodList;

/**
 * Read the method list from a static set of resources
 *
 * @author Andrew Tarry
 * @since 0.3.0
 */
public interface MIExtractionConfiguration {

    /**
     * Create an empty set of configuration
     * @return empty configuration
     */
    static MIExtractionConfiguration empty() {
        return name -> ReadOnlyExtractionMethodList.empty();
    }

    /**
     * Get a named method list
     * @param name name of method list
     * @return method list
     */
    ReadOnlyExtractionMethodList getExtractionMethodList(String name);
}
