package net.sixpointsix.carpo.mi.spring;

import net.sixpointsix.carpo.common.extractor.method.ReadOnlyExtractionMethodList;
import net.sixpointsix.carpo.mi.MIExtractionConfiguration;
import net.sixpointsix.carpo.mi.spring.configuration.MIConfiguration;

public class SpringMIExtractionConfiguration implements MIExtractionConfiguration {

    private final MIConfiguration miConfiguration;

    /**
     * Get a named method list
     *
     * @param name name of method list
     * @return method list
     */
    @Override
    public ReadOnlyExtractionMethodList getExtractionMethodList(String name) {
        return null;
    }
}
