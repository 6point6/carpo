package net.sixpointsix.carpo.common.validation;

import net.sixpointsix.carpo.common.model.PropertyType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Details of the property to validated
 *
 * @author Andrew Tarry
 * @since 0.6.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.ANNOTATION_TYPE)
public @interface RequiredProperty {

    /**
     * Property key
     * @return key
     */
    String key();

    /**
     * Property type
     *
     * <p>
     *     Unknown is used as the default to say don't validate the type. Only use that if you can handle different types
     *     for the same property
     * </p>
     * @return property type
     */
    PropertyType propertyType() default PropertyType.UNKNOWN;
}
