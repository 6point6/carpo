package net.sixpointsix.carpo.common.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Validation for property holding entities
 *
 * <p>
 *     Any property holding entity might need to have a limitation on the properties it will accept
 * </p>
 *
 * <code>
 *         @RequiredProperties(
 *             required = {
 *                     @RequiredProperty(key = "a", propertyType = PropertyType.STRING),
 *                     @RequiredProperty(key = "c", propertyType = PropertyType.STRING)
 *             },
 *             allowUnknown = false
 *     )
 *     private static class KnownProperty implements PropertyHoldingEntity
 * </code>
 *
 * @author Andrew Tarry
 * @since 0.6.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = RequiredPropertyValidator.class)
@Documented
public @interface RequiredProperties {

    Class<?>[] groups() default {};

    String message() default "Properties Invalid";

    Class<? extends Payload>[] payload() default {};

    /**
     * Set of annotations for each property
     *
     * <code>
     *         @RequiredProperties(
     *             required = {
     *                     @RequiredProperty(key = "a", propertyType = PropertyType.STRING),
     *                     @RequiredProperty(key = "c", propertyType = PropertyType.STRING)
     *             }
     *     )
     * </code>
     * @return requirements
     */
    RequiredProperty[] required() default {};

    /**
     * Set to false if properties that are not in the required block should cause a violation
     * @return false to trigger violations
     */
    boolean allowUnknown() default true;
}
