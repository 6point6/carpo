package net.sixpointsix.carpo.common.extractor;

import net.sixpointsix.carpo.common.model.Property;
import net.sixpointsix.carpo.common.model.PropertyCollection;
import net.sixpointsix.carpo.common.model.PropertyHoldingEntity;
import net.sixpointsix.carpo.common.model.PropertyType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;
import java.util.PropertyPermission;
import java.util.function.Function;

public class DirectPropertyExtractor implements PropertyExtractor {

    private static final Logger logger = LoggerFactory.getLogger(DirectPropertyExtractor.class);

    /**
     * Get a datapoint from an entity
     *
     * @param propertyHoldingEntity
     * @param value                 value to find
     * @return Datapoint value
     */
    @Override
    public String getDatapoint(PropertyHoldingEntity propertyHoldingEntity, String value) {
        return getDatapoint(propertyHoldingEntity.getProperties(), value);
    }

    /**
     * Get a datapoint from an entity
     *
     * @param propertyCollection
     * @param value              to find
     * @return Datapoint value
     */
    @Override
    public String getDatapoint(PropertyCollection propertyCollection, String value) {
        final Optional<Property> propertyOptional = propertyCollection.getByKey(value);

        if(propertyOptional.isEmpty()) {
            return null;
        }

        final Property property = propertyOptional.get();
        String propertyValue = null;

        switch (property.getType()) {
            case DOUBLE:
                propertyValue = property.getDoubleValue().map(Object::toString).orElse(null);
                break;
            case STRING:
                propertyValue = property.getStringValue().orElse(null);
                break;
            case LONG:
                propertyValue = property.getLongValue().map(Object::toString).orElse(null);
                break;
            case BOOLEAN:
                propertyValue = property.getBooleanValue().map(Object::toString).orElse(null);
                break;
        }

        return propertyValue;
    }

    /**
     * Get a datapoint from an entity
     *
     * @param propertyHoldingEntity
     * @param value                 to find
     * @param propertyType          expected property type
     * @param extractor             function to get the value
     * @return Datapoint value
     */
    @Override
    public <T> String getDatapoint(PropertyHoldingEntity propertyHoldingEntity, String value, Class<T> propertyType, Function<T, String> extractor) {
        return getDatapoint(propertyHoldingEntity.getProperties(), value, propertyType, extractor);
    }

    /**
     * Get a datapoint from an entity
     *
     * @param propertyCollection
     * @param value              to find
     * @param propertyType       expected property type
     * @param extractor          function to get the value
     * @return Datapoint value
     */
    @Override
    public <T> String getDatapoint(PropertyCollection propertyCollection, String value, Class<T> propertyType, Function<T, String> extractor) {
        return getDatapoint(propertyCollection, value, propertyType, extractor, null);
    }


    /**
     * Get a datapoint from an entity
     *
     * @param propertyHoldingEntity
     * @param value                 to find
     * @param propertyType          expected property type
     * @param extractor             function to get the value
     * @return Datapoint value
     */
    @Override
    public <T> String getDatapoint(PropertyHoldingEntity propertyHoldingEntity, String value, Class<T> propertyType, Function<T, String> extractor, Integer index) {
        return getDatapoint(propertyHoldingEntity.getProperties(), value, propertyType, extractor, index);
    }

    /**
     * Get a datapoint from an entity
     *
     * @param propertyCollection
     * @param value              to find
     * @param propertyType       expected property type
     * @param extractor          function to get the value
     * @return Datapoint value
     */
    @Override
    public <T> String getDatapoint(PropertyCollection propertyCollection, String value, Class<T> propertyType, Function<T, String> extractor, Integer index) {
        final Optional<Property> propertyOptional = propertyCollection.getByKey(value);

        if(propertyOptional.isEmpty()) {
            return null;
        }

        final Property property = propertyOptional.get();
        Optional<String> objectValue;

        if(index == null) {
            objectValue = extractFromObject(property, propertyType, extractor);
        } else {
            objectValue = extractFromList(property, propertyType, extractor, index);
        }

        return objectValue.orElse(null);
    }

    private <T> Optional<String> extractFromObject(final Property property, final Class<T> propertyType, final Function<T, String> extractor) {
        if(!property.hasObjectValue()) {
            return Optional.empty();
        }

        final Optional<T> object = property.getObjectValue(propertyType);

        if(object.isEmpty()) {
            return Optional.empty();
        }

        return Optional.ofNullable(extractor.apply(object.get()));
    }

    private <T> Optional<String> extractFromList(final Property property, final Class<T> propertyType, final Function<T, String> extractor, Integer index) {
        if(!property.hasListValue()) {
            return Optional.empty();
        }

        final List<T> list = property.getListValue(propertyType);

        if(list.isEmpty()) {
            return Optional.empty();
        }

        try {
            final T data = list.get(index);

            return Optional.ofNullable(extractor.apply(data));
        } catch (IndexOutOfBoundsException e) {
            logger.debug("Unable to get property data at index {} from list of size {}", index, list.size());

            return Optional.empty();
        }
    }
}
