package net.sixpointsix.carpo.common.repository;

import net.sixpointsix.carpo.common.model.Property;

import java.util.List;

/**
 * Select data using custom properties
 *
 * @author Andrew Tarry
 * @since 0.7.0
 */
public interface SelectProperties {

    /**
     * Default set of properties that can used to get all data
     */
    SelectProperties OPEN_SELECT = new SelectProperties() {
        @Override
        public long getOffset() {
            return 0;
        }

        @Override
        public long getLength() {
            return Integer.MAX_VALUE; // Using int max since long max is too high for most situations
        }

        @Override
        public List<Property> getSearchProperties() {
            return List.of();
        }
    };

    /**
     * Offset from the start of the data to return
     *
     * @return dataset offset
     */
    long getOffset();

    /**
     * Max length of the data set
     *
     * @return dataset length
     */
    long getLength();

    /**
     * Get the properties to search for
     *
     * <p>
     *     This should be a list of constraints on the data, each property will limit the results further. An empty list
     *     means return everything
     * </p>
     * @return list of properties to search by
     */
    List<Property> getSearchProperties();
}
