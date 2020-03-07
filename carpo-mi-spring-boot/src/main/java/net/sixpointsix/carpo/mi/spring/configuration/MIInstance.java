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

}
