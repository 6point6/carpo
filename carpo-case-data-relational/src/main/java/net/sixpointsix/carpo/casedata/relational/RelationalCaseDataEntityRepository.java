package net.sixpointsix.carpo.casedata.relational;

import net.sixpointsix.carpo.casedata.model.CarpoCase;
import net.sixpointsix.carpo.casedata.relational.jdbi.CaseDataDao;
import net.sixpointsix.carpo.casedata.relational.jdbi.PropertyDao;
import net.sixpointsix.carpo.casedata.relational.jdbi.model.PropertyWrapper;
import net.sixpointsix.carpo.casedata.repository.CaseDataEntityRepository;
import org.jdbi.v3.core.Jdbi;

import java.util.Optional;

public class RelationalCaseDataEntityRepository implements CaseDataEntityRepository {

    private final Jdbi jdbi;

    public RelationalCaseDataEntityRepository(Jdbi jdbi) {
        this.jdbi = jdbi;
    }

    /**
     * Create a new record of the entity
     *
     * @param entity to be saved
     */
    @Override
    public void create(CarpoCase entity) {
        jdbi.useExtension(CaseDataDao.class, h -> h.insertCaseData(entity.getCarpoId(), entity.getTimestamp().getCreatedAt(), entity.getTimestamp().getLastUpdated()));
        entity.getProperties()
                .forEach(p -> {
                    PropertyWrapper wrapper = new PropertyWrapper(p);
                    jdbi.useExtension(PropertyDao.class, h -> h.insert(wrapper, entity.getCarpoId()));
                });
    }

    /**
     * Save the changes to entity
     *
     * @param entity to be saved
     */
    @Override
    public void update(CarpoCase entity) {
        // TODO
    }

    /**
     * Delete an entity
     *
     * @param entity to be deleted
     */
    @Override
    public void delete(CarpoCase entity) {
        // TODO
    }

    /**
     * Get an entity by an id
     *
     * @param id id to search for
     * @return entity data
     */
    @Override
    public Optional<CarpoCase> getById(String id) {
        return jdbi.withExtension(CaseDataDao.class, h -> h.selectById(id));
    }
}
