package net.sixpointsix.carpo.common.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = RequiredPropertyValidator.class)
@Documented
public @interface RequiredProperties {

    Class<?>[] groups() default {};

    String message() default "Properties Invalid";

    Class<? extends Payload>[] payload() default {};

    RequiredProperty[] required() default {};

    boolean allowUnknown() default true;
}
