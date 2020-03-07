package net.sixpointsix.carpo.common.extractor;

import net.sixpointsix.carpo.common.extractor.method.DataElement;
import net.sixpointsix.carpo.common.extractor.method.ReadOnlyExtractionMethodList;
import net.sixpointsix.carpo.common.model.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Extract data using java streams
 *
 * @author Andrew Tarry
 * @since 0.2.0
 */
public class StreamingDataSetExtractor implements DataSetExtractor {

    private final PropertyExtractor propertyExtractor;

    public StreamingDataSetExtractor(PropertyExtractor propertyExtractor) {
        this.propertyExtractor = propertyExtractor;
    }

    public DataSet getData(List<PropertyHoldingEntity> data, ReadOnlyExtractionMethodList extractionMethodList) {
        if(data == null || extractionMethodList == null || data.isEmpty() || extractionMethodList.isEmpty()) {
            return DataSet.empty();
        }

        List<DataPointRow> rows = data
                .parallelStream()
                .map(e -> {
                    return extractionMethodList
                            .stream()
                            .map(m -> extractToDataPoint(e, m))
                            .collect(Collectors.toList());
                })
                .map(DataPointRow::new)
                .collect(Collectors.toList());

        return new ListDataSet(rows);
    }

    private DataPoint extractToDataPoint(PropertyHoldingEntity entity, DataElement<?> element) {
        String value = element.extract(entity, propertyExtractor);

        return new DataPoint(element.getRowHeader(), value);
    }

}
