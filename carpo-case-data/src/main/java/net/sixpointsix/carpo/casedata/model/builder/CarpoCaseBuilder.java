package net.sixpointsix.carpo.casedata.model.builder;

import net.sixpointsix.carpo.casedata.model.CarpoCase;
import net.sixpointsix.carpo.casedata.model.immutable.ImmutableCarpoCase;
import net.sixpointsix.carpo.common.model.AbstractPropertyEntityBuilder;

/**
 * Build a carpo case
 *
 * @author Andrew Tarry
 * @since 0.0.1
 */
public class CarpoCaseBuilder extends AbstractPropertyEntityBuilder<CarpoCase> {

    /**
     * {@inheritDoc}
     */
    @Override
    public CarpoCase build() {
        return new ImmutableCarpoCase(carpoId, timestamp, properties);
    }
}
