package net.sixpointsix.carpo.common.validation;

import net.sixpointsix.carpo.common.model.PropertyCollection;
import net.sixpointsix.carpo.common.model.PropertyHoldingEntity;
import net.sixpointsix.carpo.common.model.PropertyType;
import net.sixpointsix.carpo.common.model.immutable.ImmutableProperty;
import net.sixpointsix.carpo.common.model.mutable.MutablePropertyCollection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class RequiredPropertyValidatorTest {

    private Validator validator;

    @RequiredProperties
    private static class EmptyExample implements PropertyHoldingEntity {

        @Override
        public PropertyCollection getProperties() {
            return new MutablePropertyCollection();
        }
    }

    @RequiredProperties
    private static class NullExample implements PropertyHoldingEntity {

        @Override
        public PropertyCollection getProperties() {
            return null;
        }
    }

    @RequiredProperties(
            required = @RequiredProperty(key = "a")
    )
    private static class MissingValue implements PropertyHoldingEntity {

        @Override
        public PropertyCollection getProperties() {
            return new MutablePropertyCollection();
        }
    }

    @RequiredProperties(
            required = @RequiredProperty(key = "a", propertyType = PropertyType.DOUBLE)
    )
    private static class WrongValueType implements PropertyHoldingEntity {

        @Override
        public PropertyCollection getProperties() {
            MutablePropertyCollection mutablePropertyCollection = new MutablePropertyCollection();
            mutablePropertyCollection.add(ImmutableProperty.build("a", "b"));

            return mutablePropertyCollection;
        }
    }

    @RequiredProperties(
            required = @RequiredProperty(key = "a", propertyType = PropertyType.STRING)
    )
    private static class ValidType implements PropertyHoldingEntity {

        @Override
        public PropertyCollection getProperties() {
            MutablePropertyCollection mutablePropertyCollection = new MutablePropertyCollection();
            mutablePropertyCollection.add(ImmutableProperty.build("a", "b"));

            return mutablePropertyCollection;
        }
    }

    @RequiredProperties(
            required = @RequiredProperty(key = "a", propertyType = PropertyType.STRING),
            allowUnknown = false
    )
    private static class UnknownProperty implements PropertyHoldingEntity {

        @Override
        public PropertyCollection getProperties() {
            MutablePropertyCollection mutablePropertyCollection = new MutablePropertyCollection();
            mutablePropertyCollection.add(ImmutableProperty.build("a", "b"));
            mutablePropertyCollection.add(ImmutableProperty.build("c", "d"));

            return mutablePropertyCollection;
        }
    }

    @RequiredProperties(
            required = {
                    @RequiredProperty(key = "a", propertyType = PropertyType.STRING),
                    @RequiredProperty(key = "c", propertyType = PropertyType.STRING)
            },
            allowUnknown = false
    )
    private static class KnownProperty implements PropertyHoldingEntity {

        @Override
        public PropertyCollection getProperties() {
            MutablePropertyCollection mutablePropertyCollection = new MutablePropertyCollection();
            mutablePropertyCollection.add(ImmutableProperty.build("a", "b"));
            mutablePropertyCollection.add(ImmutableProperty.build("c", "d"));

            return mutablePropertyCollection;
        }
    }

    @BeforeEach
    void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    void validateEmpty() {
        Set<ConstraintViolation<PropertyHoldingEntity>> violationSet = validator.validate(new EmptyExample());

        assertTrue(violationSet.isEmpty());
    }

    @Test
    void validateNull() {
        Set<ConstraintViolation<PropertyHoldingEntity>> violationSet = validator.validate(new NullExample());

        assertFalse(violationSet.isEmpty());
    }

    @Test
    void validateMissingValue() {
        Set<ConstraintViolation<PropertyHoldingEntity>> violationSet = validator.validate(new MissingValue());

        assertFalse(violationSet.isEmpty());
    }

    @Test
    void wrongPropertyType() {
        Set<ConstraintViolation<PropertyHoldingEntity>> violationSet = validator.validate(new WrongValueType());

        assertFalse(violationSet.isEmpty());
    }

    @Test
    void correctPropertyType() {
        Set<ConstraintViolation<PropertyHoldingEntity>> violationSet = validator.validate(new ValidType());

        assertTrue(violationSet.isEmpty());
    }

    @Test
    void unknownProperty() {
        Set<ConstraintViolation<PropertyHoldingEntity>> violationSet = validator.validate(new UnknownProperty());

        assertFalse(violationSet.isEmpty());
    }

    @Test
    void knownProperty() {
        Set<ConstraintViolation<PropertyHoldingEntity>> violationSet = validator.validate(new KnownProperty());

        assertTrue(violationSet.isEmpty());
    }

}