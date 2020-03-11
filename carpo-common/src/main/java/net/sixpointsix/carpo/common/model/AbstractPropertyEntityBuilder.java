package net.sixpointsix.carpo.common.model;

import net.sixpointsix.carpo.common.model.immutable.ImmutableProperty;
import net.sixpointsix.carpo.common.model.immutable.ImmutableTimestamp;
import net.sixpointsix.carpo.common.model.mutable.MutablePropertyCollection;

import java.util.UUID;

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

    public AbstractPropertyEntityBuilder() {
    }

    public AbstractPropertyEntityBuilder(CarpoPropertyEntity entity) {
        setCarpoId(entity.getCarpoId());
        setTimestamp(entity.getTimestamp());
        properties = entity.getProperties();
    }



    /**
     * Set the Id
     *
     * @param carpoId Carpo ID value
     */
    public void setCarpoId(String carpoId) {
        this.carpoId = carpoId;
    }

    /**
     * Set the Id
     *
     * @param carpoId Carpo ID value
     */
    public void setCarpoId(UUID carpoId) {
        if(carpoId != null) {
            setCarpoId(carpoId.toString());
        }
    }

    /**
     * Set the ID to a random string
     */
    public void setRandomId() {
        setCarpoId(UUID.randomUUID());
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

    /**
     * Set the name property of the entity
     * @param name name
     */
    public void setName(String name) {
        addProperty(ImmutableProperty.build(StandardProperties.NAME, name));
    }
}
