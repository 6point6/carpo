package net.sixpointsix.carpo.common.extractor;

import net.sixpointsix.carpo.common.model.PropertyCollection;
import net.sixpointsix.carpo.common.model.PropertyHoldingEntity;

import java.util.function.Function;

/**
 * Extract properties from a flat property entity
 */
public interface PropertyExtractor {

    /**
     * Get a datapoint from an entity
     * @param propertyHoldingEntity
     * @param value value to find
     * @return String value
     */
    String getDatapoint(PropertyHoldingEntity propertyHoldingEntity, String value);

    /**
     * Get a datapoint from an entity
     * @param propertyCollection
     * @param value to find
     * @return String value
     */
    String getDatapoint(PropertyCollection propertyCollection, String value);

    /**
     * Get a datapoint from an entity
     * @param propertyHoldingEntity
     * @param value to find
     * @param propertyType expected property type
     * @param extractor function to get the value
     * @return String value
     */
    <T> String getDatapoint(PropertyHoldingEntity propertyHoldingEntity, String value, Class<T> propertyType, Function<T, String> extractor);

    /**
     * Get a datapoint from an entity
     * @param propertyCollection
     * @param value to find
     * @param propertyType expected property type
     * @param extractor function to get the value
     * @return String value
     */
    <T> String getDatapoint(PropertyCollection propertyCollection, String value, Class<T> propertyType, Function<T, String> extractor);

    /**
     * Get a datapoint from an entity
     *
     * @param propertyHoldingEntity
     * @param value                 to find
     * @param propertyType          expected property type
     * @param extractor             function to get the value
     * @return Datapoint value
     */
    <T> String getDatapoint(PropertyHoldingEntity propertyHoldingEntity, String value, Class<T> propertyType, Function<T, String> extractor, Integer index);

    /**
     * Get a datapoint from an entity
     *
     * @param propertyCollection
     * @param value              to find
     * @param propertyType       expected property type
     * @param extractor          function to get the value
     * @return Datapoint value
     */
    <T> String getDatapoint(PropertyCollection propertyCollection, String value, Class<T> propertyType, Function<T, String> extractor, Integer index);

}
