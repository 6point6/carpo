package net.sixpointsix.carpo.mi.spring;

import net.sixpointsix.carpo.common.extractor.method.ReadOnlyExtractionMethodList;
import net.sixpointsix.carpo.mi.spring.configuration.MIConfiguration;
import net.sixpointsix.carpo.mi.spring.configuration.MIInstance;
import net.sixpointsix.carpo.mi.spring.configuration.MIValue;
import net.sixpointsix.carpo.mi.spring.exception.MappingNotFoundException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SpringMIExtractionConfigurationTest {

    @Test
    void methodNotFound() {
        MIConfiguration miConfiguration = new MIConfiguration();
        SpringMIExtractionConfiguration springMIExtractionConfiguration = new SpringMIExtractionConfiguration(miConfiguration);

        assertThrows(MappingNotFoundException.class, () -> springMIExtractionConfiguration.getExtractionMethodList("a"));
    }

    @Test
    void getMIwithoutValues() {
        MIInstance miInstance = new MIInstance();
        miInstance.setName("a");

        MIConfiguration miConfiguration = new MIConfiguration();
        miConfiguration.getInstances().add(miInstance);
        SpringMIExtractionConfiguration springMIExtractionConfiguration = new SpringMIExtractionConfiguration(miConfiguration);

        ReadOnlyExtractionMethodList methodList = springMIExtractionConfiguration.getExtractionMethodList("a");
        assertNotNull(methodList);
        assertTrue(methodList.isEmpty());
    }

    @Test
    void getMIwithValues() {
        MIValue miValue = new MIValue();
        miValue.setColumnName("x");
        miValue.setPropertyName("y");
        MIInstance miInstance = new MIInstance();
        miInstance.setName("a");
        miInstance.getValues().add(miValue);

        MIConfiguration miConfiguration = new MIConfiguration();
        miConfiguration.getInstances().add(miInstance);
        SpringMIExtractionConfiguration springMIExtractionConfiguration = new SpringMIExtractionConfiguration(miConfiguration);

        ReadOnlyExtractionMethodList methodList = springMIExtractionConfiguration.getExtractionMethodList("a");
        assertNotNull(methodList);
        assertFalse(methodList.isEmpty());
    }
}