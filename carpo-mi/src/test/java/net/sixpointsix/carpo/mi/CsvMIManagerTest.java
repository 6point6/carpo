package net.sixpointsix.carpo.mi;

import net.sixpointsix.carpo.common.extractor.DataSetExtractor;
import net.sixpointsix.carpo.common.extractor.method.ReadOnlyExtractionMethodList;
import net.sixpointsix.carpo.common.model.DataSet;
import net.sixpointsix.carpo.common.model.PropertyHoldingEntity;
import net.sixpointsix.carpo.common.writer.CsvDataWriter;
import net.sixpointsix.carpo.mi.exception.MethodListNotFound;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CsvMIManagerTest {

    @Test
    void createMI() {
        PropertyHoldingEntity entity = mock(PropertyHoldingEntity.class);
        List<PropertyHoldingEntity> entities = List.of(entity);

        ReadOnlyExtractionMethodList readOnlyExtractionMethodList = mock(ReadOnlyExtractionMethodList.class);

        DataSetExtractor dataSetExtractor = mock(DataSetExtractor.class);
        CsvDataWriter csvDataWriter = mock(CsvDataWriter.class);
        DataSet dataSet = mock(DataSet.class);
        OutputStream outputStream = new ByteArrayOutputStream();

        when(dataSetExtractor.getData(entities, readOnlyExtractionMethodList)).thenReturn(dataSet);
        when(csvDataWriter.writeDataSet(dataSet)).thenReturn(outputStream);

        CsvMIManager csvMIManager = new CsvMIManager(dataSetExtractor, csvDataWriter);

        assertEquals(outputStream, csvMIManager.createMI(entities, readOnlyExtractionMethodList));
    }

    @Test
    void createMIFromMapping() {
        PropertyHoldingEntity entity = mock(PropertyHoldingEntity.class);
        List<PropertyHoldingEntity> entities = List.of(entity);

        ReadOnlyExtractionMethodList readOnlyExtractionMethodList = mock(ReadOnlyExtractionMethodList.class);
        MIExtractionConfiguration miExtractionConfiguration = mock(MIExtractionConfiguration.class);
        when(miExtractionConfiguration.getExtractionMethodList("a")).thenReturn(readOnlyExtractionMethodList);

        DataSetExtractor dataSetExtractor = mock(DataSetExtractor.class);
        CsvDataWriter csvDataWriter = mock(CsvDataWriter.class);
        DataSet dataSet = mock(DataSet.class);
        OutputStream outputStream = new ByteArrayOutputStream();

        when(dataSetExtractor.getData(entities, readOnlyExtractionMethodList)).thenReturn(dataSet);
        when(csvDataWriter.writeDataSet(dataSet)).thenReturn(outputStream);

        CsvMIManager csvMIManager = new CsvMIManager(dataSetExtractor, csvDataWriter, miExtractionConfiguration);

        assertEquals(outputStream, csvMIManager.createMI(entities, "a"));
    }

    @Test
    void methodListNotFound() {
        PropertyHoldingEntity entity = mock(PropertyHoldingEntity.class);
        List<PropertyHoldingEntity> entities = List.of(entity);

        MIExtractionConfiguration miExtractionConfiguration = mock(MIExtractionConfiguration.class);
        when(miExtractionConfiguration.getExtractionMethodList("a")).thenReturn(null);

        DataSetExtractor dataSetExtractor = mock(DataSetExtractor.class);
        CsvDataWriter csvDataWriter = mock(CsvDataWriter.class);

        CsvMIManager csvMIManager = new CsvMIManager(dataSetExtractor, csvDataWriter, miExtractionConfiguration);

        assertThrows(MethodListNotFound.class, () -> csvMIManager.createMI(entities, "a"));
    }
}