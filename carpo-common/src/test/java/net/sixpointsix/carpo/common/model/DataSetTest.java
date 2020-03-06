package net.sixpointsix.carpo.common.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DataSetTest {

    @Test
    void emptyRowSet() {
        DataSet dataSet = DataSet.empty();

        assertTrue(dataSet.isEmpty());
        assertTrue(dataSet.getRows().isEmpty());
    }

}