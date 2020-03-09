package net.sixpointsix.carpo.mi;

import net.sixpointsix.carpo.common.extractor.DataSetExtractor;
import net.sixpointsix.carpo.common.extractor.method.ReadOnlyExtractionMethodList;
import net.sixpointsix.carpo.common.model.DataSet;
import net.sixpointsix.carpo.common.model.PropertyHoldingEntity;
import net.sixpointsix.carpo.common.writer.CsvDataWriter;
import net.sixpointsix.carpo.mi.exception.MethodListNotFound;

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
    private final MIExtractionConfiguration miExtractionConfiguration;

    public CsvMIManager(DataSetExtractor dataSetExtractor, CsvDataWriter csvDataWriter) {
        this(dataSetExtractor, csvDataWriter, MIExtractionConfiguration.empty());
    }

    public CsvMIManager(DataSetExtractor dataSetExtractor, CsvDataWriter csvDataWriter, MIExtractionConfiguration miExtractionConfiguration) {
        this.dataSetExtractor = dataSetExtractor;
        this.csvDataWriter = csvDataWriter;
        this.miExtractionConfiguration = miExtractionConfiguration;
    }

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
    @Override
    public OutputStream createMI(List<PropertyHoldingEntity> entityList, ReadOnlyExtractionMethodList methodList) {
        final DataSet dataSet = dataSetExtractor.getData(entityList, methodList);

        return csvDataWriter.writeDataSet(dataSet);
    }

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
    @Override
    public OutputStream createMI(List<PropertyHoldingEntity> entityList, String methodListName) {
        final ReadOnlyExtractionMethodList methodList = miExtractionConfiguration.getExtractionMethodList(methodListName);

        if(methodList == null) {
            throw new MethodListNotFound("Method list " + methodListName + " not found");
        }

        return createMI(entityList, methodList);
    }
}
