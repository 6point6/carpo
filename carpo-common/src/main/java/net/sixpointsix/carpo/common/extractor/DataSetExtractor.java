package net.sixpointsix.carpo.common.extractor;

import net.sixpointsix.carpo.common.extractor.method.ReadOnlyExtractionMethodList;
import net.sixpointsix.carpo.common.model.DataSet;
import net.sixpointsix.carpo.common.model.PropertyHoldingEntity;

import java.util.List;

/**
 * Extractor of data for MI or render purposes
 *
 * @author Andrew Tarry
 * @since 0.2.0
 */
public interface DataSetExtractor {

    /**
     * Extract data from a list of entities
     * @param data data to be parsed
     * @param extractionMethodList list of methods to get the values
     * @return complete data set
     */
    DataSet getData(List<PropertyHoldingEntity> data, ReadOnlyExtractionMethodList extractionMethodList);
}
