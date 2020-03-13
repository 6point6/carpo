package net.sixpointsix.carpo.common.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = CompleteEntityValidator.class)
public @interface CompleteEntity {

    Class<?>[] groups() default {};

    String message() default "Incomplete entity";

    Class<? extends Payload>[] payload() default {};
}
