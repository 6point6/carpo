package net.sixpointsix.carpo.mi;

import net.sixpointsix.carpo.common.extractor.DataSetExtractor;
import net.sixpointsix.carpo.common.extractor.method.ReadOnlyExtractionMethodList;
import net.sixpointsix.carpo.common.model.DataSet;
import net.sixpointsix.carpo.common.model.PropertyHoldingEntity;
import net.sixpointsix.carpo.common.writer.CsvDataWriter;

import java.io.OutputStream;
import java.util.List;

/**
 * Create CSV MI data
 *
 * @author Andrew Tarry
 * @since 0.2.0
 */
public class CsvMIManager implements MIManager {

    private final DataSetExtractor dataSetExtractor;
    private final CsvDataWriter csvDataWriter;

    public CsvMIManager(DataSetExtractor dataSetExtractor, CsvDataWriter csvDataWriter) {
        this.dataSetExtractor = dataSetExtractor;
        this.csvDataWriter = csvDataWriter;
    }

    @Override
    public OutputStream createMi(List<PropertyHoldingEntity> entityList, ReadOnlyExtractionMethodList methodList) {
        final DataSet dataSet = dataSetExtractor.getData(entityList, methodList);

        return csvDataWriter.writeDataSet(dataSet);
    }
}
