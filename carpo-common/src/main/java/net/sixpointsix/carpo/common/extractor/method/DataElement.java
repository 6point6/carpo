package net.sixpointsix.carpo.common.extractor.method;

import net.sixpointsix.carpo.common.extractor.PropertyExtractor;
import net.sixpointsix.carpo.common.model.PropertyHoldingEntity;

import java.util.function.Function;

/**
 * A data element is a value to be pulled from a set of properties
 *
 * <p>
 *     This class holds the header, property key and an extraction method to
 *     handle entities or lists
 * </p>
 *
 * @param <T> Type of data being held
 * @author Andrew Tarry
 * @since 0.2.0
 */
public class DataElement<T> {

    private final String rowHeader;
    private final String value;
    private final Class<T> propertyType;
    private final Function<T, String> extractor;
    private final Integer index;

    public DataElement(String rowHeader, String value) {
        this(rowHeader, value, null, null, null);
    }

    public DataElement(String rowHeader, String value, Class<T> propertyType, Function<T, String> extractor, Integer index) {
        this.rowHeader = rowHeader;
        this.value = value;
        this.propertyType = propertyType;
        this.extractor = extractor;
        this.index = index;
    }

    public String getRowHeader() {
        return rowHeader;
    }

    /**
     * Test if an extractor is string only
     * @return true if the value is a primitive to be converted to a string
     */
    public boolean isStringExtractor() {
        return propertyType == null;
    }

    public String extract(PropertyHoldingEntity entity, PropertyExtractor propertyExtractor) {
        if(isStringExtractor()) {
            return propertyExtractor.getDatapoint(entity, value);
        }

        return propertyExtractor.getDatapoint(entity, value, propertyType, extractor, index);
    }
}
