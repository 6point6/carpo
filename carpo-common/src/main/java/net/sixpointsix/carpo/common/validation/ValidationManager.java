package net.sixpointsix.carpo.common.validation;

import net.sixpointsix.carpo.common.exception.PropertyValidationException;
import net.sixpointsix.carpo.common.model.CarpoPropertyEntity;

/**
 * Manage validations for clients
 *
 * @author Andrew Tarry
 * @since 0.7.0
 */
public interface ValidationManager {

    /**
     * Validate the entity
     *
     * @throws PropertyValidationException when the validation fails
     * @param entity entity to be validated
     */
    void validate(CarpoPropertyEntity entity);
}
