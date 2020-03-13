package net.sixpointsix.carpo.common.validation;

import net.sixpointsix.carpo.common.exception.PropertyValidationException;
import net.sixpointsix.carpo.common.model.CarpoPropertyEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

/**
 * Validate entities
 *
 * @author Andrew Tarry
 * @since 0.7.0
 */
public class DirectValidationManager implements ValidationManager {

    private static final Logger logger = LoggerFactory.getLogger(DirectValidationManager.class);
    private final Validator validator;

    public DirectValidationManager(Validator validator) {
        this.validator = validator;
    }

    /**
     * Validate the entity
     *
     * @param entity entity to be validated
     * @throws PropertyValidationException when the validation fails
     */
    @Override
    public void validate(CarpoPropertyEntity entity) {
        Set<ConstraintViolation<CarpoPropertyEntity>> constraints = validator.validate(entity);

        if(!constraints.isEmpty()) {
            constraints.forEach(c -> logger.error("Invalid {}", c.getMessage()));
            throw new RuntimeException();
        }
    }
}
