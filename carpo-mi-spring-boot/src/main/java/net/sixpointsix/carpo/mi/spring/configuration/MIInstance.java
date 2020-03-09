package net.sixpointsix.carpo.mi.spring.configuration;

import net.sixpointsix.carpo.mi.MIFormat;

import java.util.ArrayList;
import java.util.List;

/**
 * Instance of the MI to be loaded
 *
 * @author Andrew Tarry
 * @since 0.3.0
 */
public class MIInstance {

    /**
     * Name of the MI to be loaded
     */
    private String name;

    /**
     * Output format to write to
     */
    private MIFormat outputFormat = MIFormat.CSV;

    /**
     * Values to be extracted
     */
    private List<MIValue> values = new ArrayList<>();

    public String getName() {
        return name;
    }

    public MIFormat getOutputFormat() {
        return outputFormat;
    }

    public List<MIValue> getValues() {
        return values;
    }

    public MIInstance setName(String name) {
        this.name = name;
        return this;
    }

    public MIInstance setOutputFormat(MIFormat outputFormat) {
        this.outputFormat = outputFormat;
        return this;
    }

    public MIInstance setValues(List<MIValue> values) {
        this.values = values;
        return this;
    }
}
