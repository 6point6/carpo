package net.sixpointsix.carpo.casedata.model;

import net.sixpointsix.carpo.casedata.model.builder.CarpoCaseBuilder;
import net.sixpointsix.carpo.common.model.CarpoPropertyEntity;

/**
 * Carpo case is at the heart of the case management system will be used by most other modules
 *
 * @author Andrew Tarry
 * @since 0.0.1
 */
public interface CarpoCase extends CarpoPropertyEntity {

    /**
     * Create a new case builder
     *
     * @return case builder
     */
    static CarpoCaseBuilder builder() {
        return new CarpoCaseBuilder();
    }
}
