package net.sixpointsix.carpo.mi;

import net.sixpointsix.carpo.common.extractor.method.ReadOnlyExtractionMethodList;

import java.util.Map;

public class MIExtractionConfigurationBuilder {

    private final Map<String, ReadOnlyExtractionMethodList> extractionMethods;

    public MIExtractionConfigurationBuilder(Map<String, ReadOnlyExtractionMethodList> extractionMethods) {
        this.extractionMethods = extractionMethods;
    }
}
