package net.sixpointsix.carpo.common.validation;

import net.sixpointsix.carpo.common.model.Property;
import net.sixpointsix.carpo.common.model.PropertyHoldingEntity;
import net.sixpointsix.carpo.common.model.PropertyType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Validate property collections
 *
 * @author Andrew Tarry
 * @since 0.6.0
 */
public class RequiredPropertyValidator implements ConstraintValidator<RequiredProperties, PropertyHoldingEntity> {

    private static final Logger logger = LoggerFactory.getLogger(RequiredPropertyValidator.class);
    private List<RequiredProperty> requiredPropertyList;
    private boolean allowUnknown;

    /**
     * Set up the annotation configuration
     * @param constraintAnnotation annotation
     */
    @Override
    public void initialize(RequiredProperties constraintAnnotation) {
        requiredPropertyList = Arrays.asList(constraintAnnotation.required());
        allowUnknown = constraintAnnotation.allowUnknown();
    }

    /**
     * Test the validity of the property collection
     * @param entity entity to be tested
     * @param constraintValidatorContext context
     * @return true if valid
     */
    @Override
    public boolean isValid(PropertyHoldingEntity entity, ConstraintValidatorContext constraintValidatorContext) {
        if(entity.getProperties() == null) {
            return false;
        }

        for (RequiredProperty requiredProperty: requiredPropertyList) {
            String key = requiredProperty.key();
            Optional<Property> propertyOptional = entity.getProperties().getByKey(key);

            if(propertyOptional.isEmpty()) {
                logger.warn("Property {} not found", key);
                constraintValidatorContext
                        .buildConstraintViolationWithTemplate("{carpo.property_not_found}")
                        .addConstraintViolation();
                return false;
            }

            Property property = propertyOptional.get();
            if(!validType(requiredProperty.propertyType(), property)) {
                logger.warn("Property {} was {} but {} was expected", key, property.getType(), requiredProperty.propertyType());
                constraintValidatorContext.buildConstraintViolationWithTemplate("{carpo.type_invalid}").addConstraintViolation();
                return false;
            }
        }

        if (!allowUnknown) {
            List<String> actualKeys = entity
                    .getProperties()
                    .stream()
                    .map(Property::getKey)
                    .map(String::toUpperCase)
                    .collect(Collectors.toList());
            List<String> expectedKeys = requiredPropertyList
                    .stream()
                    .map(RequiredProperty::key)
                    .map(String::toUpperCase)
                    .collect(Collectors.toList());

            for (String actual: actualKeys) {
                if(!expectedKeys.contains(actual)) {
                    constraintValidatorContext.buildConstraintViolationWithTemplate("{carpo.additional_property_found}").addConstraintViolation();

                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Test if the type is valid
     * @param required required type
     * @param property actual property
     * @return false if validation error
     */
    private boolean validType(PropertyType required, Property property) {
        if(PropertyType.UNKNOWN.equals(required)) {
            return true;
        }

        return required.equals(property.getType());
    }
}
