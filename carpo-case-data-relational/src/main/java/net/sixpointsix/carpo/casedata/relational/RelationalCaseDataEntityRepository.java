package net.sixpointsix.carpo.casedata.relational;

import net.sixpointsix.carpo.casedata.model.CarpoCase;
import net.sixpointsix.carpo.casedata.repository.CaseDataEntityRepository;

import java.util.Optional;

public class RelationalCaseDataEntityRepository implements CaseDataEntityRepository {

    /**
     * Create a new record of the entity
     *
     * @param entity to be saved
     */
    @Override
    public void create(CarpoCase entity) {

    }

    /**
     * Save the changes to entity
     *
     * @param entity to be saved
     */
    @Override
    public void update(CarpoCase entity) {

    }

    /**
     * Delete an entity
     *
     * @param entity to be deleted
     */
    @Override
    public void delete(CarpoCase entity) {

    }

    /**
     * Get an entity by an id
     *
     * @param id id to search for
     * @return entity data
     */
    @Override
    public Optional<CarpoCase> getById(String id) {
        return Optional.empty();
    }
}
