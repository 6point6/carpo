package net.sixpointsix.carpo.common.jackson.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import net.sixpointsix.carpo.common.jackson.deserialize.PropertyDeserializer;
import net.sixpointsix.carpo.common.model.CarpoPropertyEntity;

import net.sixpointsix.carpo.common.model.PropertyCollection;
import net.sixpointsix.carpo.common.model.Timestamp;

/**
 * Carpo property entity that can be deserialized by jackson
 *
 * @author Andrew Tarry
 * @since 0.5.0
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class JacksonPropertyEntity implements CarpoPropertyEntity {

    private final String carpoId;
    private final Timestamp timestamp;
    private final PropertyCollection properties;

    @JsonCreator
    public JacksonPropertyEntity(@JsonProperty("carpoId") String carpoId,
                                 @JsonProperty("timestamp") @JsonDeserialize(as = JacksonTimestamp.class) Timestamp timestamp,
                                 @JsonProperty("properties") @JsonDeserialize(using = PropertyDeserializer.class) PropertyCollection properties) {
        this.carpoId = carpoId;
        this.timestamp = timestamp;
        this.properties = properties;
    }

    /**
     * Get the entity id as a string
     * <p>
     * The Carpo entity interface has no opinion on the format of the id. It could be a number, UUID or something else
     * but it must be able to be represented as a string
     * </p>
     * <p>
     * This method uses the carpoId not just id because some systems will already be using a getId method
     * </p>
     *
     * @return Entity ID
     */
    @Override
    public String getCarpoId() {
        return carpoId;
    }

    /**
     * Get the timestamp associated with the entity
     *
     * <p>
     * A Capo entity should have a timestamp linked with if for audit purposes but it is not a essential
     * </p>
     *
     * @return Timestamp
     */
    @Override
    public Timestamp getTimestamp() {
        return timestamp;
    }

    /**
     * Get the set of properties for the entity
     *
     * <p>
     * This method should never return null. If there are no properties an empty collection should be returned
     * </p>
     *
     * @return PropertyCollection
     */
    @Override
    public PropertyCollection getProperties() {
        return properties;
    }
}
