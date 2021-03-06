package net.sixpointsix.carpo.mi;

import net.sixpointsix.carpo.common.extractor.method.ReadOnlyExtractionMethodList;
import net.sixpointsix.carpo.common.model.PropertyHoldingEntity;
import net.sixpointsix.carpo.mi.exception.MethodListNotFound;

import java.io.OutputStream;
import java.util.List;

/**
 * Manage the generation of MI data
 *
 * @author Andrew Tarry
 * @since 0.2.0
 */
public interface MIManager {

    /**
     * Convert an entity list to a CSV data set
     *
     * <p>
     *     In the future this method is likely to change to allow alternative data formats
     * </p>
     *
     * @param entityList list of entities
     * @param methodList methods to get data
     * @return output
     */
    OutputStream createMI(List<PropertyHoldingEntity> entityList, ReadOnlyExtractionMethodList methodList);

    /**
     * Convert an entity list to a CSV data set
     *
     * <p>
     *     Pass in a configuration key to load the extractors
     * </p>
     * @param entityList list of entities
     * @param methodListName name of the method list
     * @throws MethodListNotFound if the method list is not available
     * @return output stream
     */
    OutputStream createMI(List<PropertyHoldingEntity> entityList, String methodListName);
}
