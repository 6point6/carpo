package net.sixpointsix.carpo.mi.spring.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * Spring configuration for mi
 *
 * <p>
 *     The configuration is designed with Yaml in mind. It needs a list of MI configuration that can be loaded by name
 * </p>
 *
 * <code>
 *     carpo:
 *          mi:
 *              instances:
 *                  - name: myMI
 *                  - values:
 *                      - propertyName: propertyA
 *                        columnName: My Heading
 *                      - propertyName: propertyB
 *                        columnName: My Second Heading
 * </code>
 *
 * <p>
 *     The configuration can be accessed from the MIManager by passing propertyName value
 * </p>
 *
 * @author Andrew Tarry
 * @since 0.3.0
 */
@ConfigurationProperties(prefix = "carpo.mi")
public class MIConfiguration {

    /**
     * MI Values to be loaded in the configuration
     */
    private List<MIInstance> instances = new ArrayList<>();

    public List<MIInstance> getInstances() {
        return instances;
    }

    public MIConfiguration setInstances(List<MIInstance> instances) {
        this.instances = instances;
        return this;
    }
}
