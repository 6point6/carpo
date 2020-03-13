package net.sixpointsix.carpo.common.repository;

import net.sixpointsix.carpo.common.model.Property;

import java.util.List;

/**
 * Reference properties implementation
 *
 * @author Andrew Tarry
 * @since 0.7.0
 */
public class MutableSelectProperties implements SelectProperties {

    private long offset;
    private long length;
    private List<Property> properties;

    public MutableSelectProperties(long offset, long length) {
        this(offset, length, List.of());
    }

    public MutableSelectProperties(long offset, long length, List<Property> properties) {
        this.offset = offset;
        this.length = length;
        this.properties = properties;
    }

    /**
     * Offset from the start of the data to return
     *
     * @return dataset offset
     */
    @Override
    public long getOffset() {
        return offset;
    }

    /**
     * Max length of the data set
     *
     * @return dataset length
     */
    @Override
    public long getLength() {
        return length;
    }

    /**
     * Get the properties to search for
     *
     * <p>
     * This should be a list of constraints on the data, each property will limit the results further. An empty list
     * means return everything
     * </p>
     *
     * @return list of properties to search by
     */
    @Override
    public List<Property> getSearchProperties() {
        return properties;
    }
}
