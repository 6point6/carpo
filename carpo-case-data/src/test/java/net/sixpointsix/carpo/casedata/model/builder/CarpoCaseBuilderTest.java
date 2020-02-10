package net.sixpointsix.carpo.casedata.model.builder;

import net.sixpointsix.carpo.casedata.model.CarpoCase;
import net.sixpointsix.carpo.common.model.immutable.ImmutableProperty;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarpoCaseBuilderTest {

    @Test
    void buildCase() {
        CarpoCaseBuilder carpoCaseBuilder = new CarpoCaseBuilder();
        carpoCaseBuilder.setCarpoId("a");
        carpoCaseBuilder.setTimestampToNow();
        carpoCaseBuilder.addProperty(ImmutableProperty.build("a", "b"));

        CarpoCase carpoCase = carpoCaseBuilder.build();

        assertEquals("a", carpoCase.getCarpoId());
        assertNotNull(carpoCase.getTimestamp());
        assertEquals(1, carpoCase.getProperties().size());
    }
}