package net.sixpointsix.carpo.common.repository;

import net.sixpointsix.carpo.common.model.CarpoEntity;

import java.util.Optional;

/**
 * Common entity repository that can be extended to offer common persistence methods
 *
 * @param <E> Data type
 * @author Andrew Tarry
 * @since 0.0.1
 */
public interface CrudEntityRepository<E extends CarpoEntity> {

    /**
     * Create a new record of the entity
     *
     * @param entity to be saved
     */
    void create(E entity);

    /**
     * Save the changes to entity
     * @param entity to be saved
     */
    void update(E entity);

    /**
     * Delete an entity
     *
     * @param entity to be deleted
     */
    void delete(E entity);

    /**
     * Get an entity by an id
     *
     * @param id id to search for
     * @return entity data
     */
    Optional<E> getById(String id);
}
