package net.sixpointsix.carpo.common.model;

import net.sixpointsix.carpo.common.model.immutable.ImmutableTimestamp;
import net.sixpointsix.carpo.common.model.mutable.MutablePropertyCollection;

/**
 * Parent for builder classes to make it easier to create entities
 * @param <T> entity class to be built
 * @author Andrew Tarry
 * @since 0.0.1
 */
abstract public class AbstractPropertyEntityBuilder<T extends CarpoPropertyEntity> {

    protected String carpoId;
    protected Timestamp timestamp;
    protected PropertyCollection properties = new MutablePropertyCollection();

    /**
     * Build the entity
     * @return entity
     */
    public abstract T build();

    /**
     * Set the Id
     *
     * @param carpoId Carpo ID value
     */
    public void setCarpoId(String carpoId) {
        this.carpoId = carpoId;
    }

    /**
     * Set the timestamp
     *
     * @param timestamp Timestamp to set
     */
    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Set the timestamp with the current time as the values
     */
    public void setTimestampToNow() {
        this.timestamp = ImmutableTimestamp.build();
    }

    /**
     * Add a property
     *
     * @param property property to add
     */
    public void addProperty(Property property) {
        this.properties.add(property);
    }
}
