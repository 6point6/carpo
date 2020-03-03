package net.sixpointsix.carpo.casedata.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarpoCaseTest {

    @Test
    void getBuilder() {
        assertNotNull(CarpoCase.builder());
    }
}