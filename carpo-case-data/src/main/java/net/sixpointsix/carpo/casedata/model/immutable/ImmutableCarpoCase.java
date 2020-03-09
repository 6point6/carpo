package net.sixpointsix.carpo.casedata.model.immutable;

import net.sixpointsix.carpo.casedata.model.CarpoCase;
import net.sixpointsix.carpo.common.model.PropertyCollection;
import net.sixpointsix.carpo.common.model.Timestamp;
import net.sixpointsix.carpo.common.model.immutable.AbstractImmutableCarpoPropertyEntity;

/**
 * Immutable case data
 *
 * @author Andrew Tarry
 * @since 0.0.1
 */
public class ImmutableCarpoCase extends AbstractImmutableCarpoPropertyEntity implements CarpoCase {


    /**
     * Build the basic properties of a carpo case
     * @param carpoId id of the entity
     * @param timestamp created timestamp
     * @param properties property collection
     */
    public ImmutableCarpoCase(String carpoId, Timestamp timestamp, PropertyCollection properties) {
        super(carpoId, timestamp, properties);
    }
}
