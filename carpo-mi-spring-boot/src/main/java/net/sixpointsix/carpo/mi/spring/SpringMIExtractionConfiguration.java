package net.sixpointsix.carpo.mi.spring;

import net.sixpointsix.carpo.common.extractor.method.ExtractionMethodList;
import net.sixpointsix.carpo.common.extractor.method.ReadOnlyExtractionMethodList;
import net.sixpointsix.carpo.mi.MIExtractionConfiguration;
import net.sixpointsix.carpo.mi.spring.configuration.MIConfiguration;
import net.sixpointsix.carpo.mi.spring.configuration.MIInstance;
import net.sixpointsix.carpo.mi.spring.exception.MappingNotFoundException;

import java.util.HashMap;
import java.util.Map;

/**
 * Spring MI Extraction methods to load from named configuration
 *
 * @author Andrew Tarry
 * @since 0.3.0
 */
public class SpringMIExtractionConfiguration implements MIExtractionConfiguration {

    private final MIConfiguration miConfiguration;
    private final Map<String, ReadOnlyExtractionMethodList> methods = new HashMap<>();

    public SpringMIExtractionConfiguration(MIConfiguration miConfiguration) {
        this.miConfiguration = miConfiguration;
    }

    /**
     * Get a named method list
     *
     * @param name name of method list
     * @return method list
     */
    @Override
    public ReadOnlyExtractionMethodList getExtractionMethodList(String name) {
        return methods.computeIfAbsent(name, s -> {
            MIInstance instance = miConfiguration
            .getInstances()
            .stream()
            .filter(i -> i.getName().equalsIgnoreCase(name))
            .findAny()
            .orElseThrow(() -> new MappingNotFoundException("Mapping " + name + " not found"));

            ExtractionMethodList extractionMethodList = new ExtractionMethodList();
            instance.getValues().forEach(v -> extractionMethodList.add(v.getColumnName(), v.getPropertyName()));

            return extractionMethodList;
        });
    }

}
