package net.sixpointsix.carpo.casedata.relational;

import net.sixpointsix.carpo.casedata.model.CarpoCase;
import net.sixpointsix.carpo.casedata.relational.jdbi.CaseDataDao;
import net.sixpointsix.carpo.casedata.relational.jdbi.PropertyDao;
import net.sixpointsix.carpo.casedata.relational.jdbi.model.PropertyWrapper;
import net.sixpointsix.carpo.casedata.repository.CaseDataEntityRepository;
import net.sixpointsix.carpo.common.model.Property;
import org.jdbi.v3.core.Jdbi;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Case data repository using a relational database
 */
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
                .forEach(p -> insertProperty(p, entity));
    }

    /**
     * Save the changes to entity
     *
     * @param entity to be saved
     */
    @Override
    public void update(CarpoCase entity) {
        jdbi.useExtension(CaseDataDao.class, h -> h.updateCase(entity.getCarpoId(), LocalDateTime.now()));
        entity
                .getProperties()
                .parallelStream()
                .forEach(p -> {
                    Optional<Property> existingPropertyOptional = jdbi.withExtension(PropertyDao.class, h -> h.selectByKey(entity.getCarpoId(), p.getKey()));

                    if(existingPropertyOptional.isPresent()) {
                        final Property existingProperty = existingPropertyOptional.get();

                        if(!existingProperty.equals(p)) {
                            jdbi.useExtension(PropertyDao.class, h -> h.updateProperty(p));
                        }
                    } else {
                        insertProperty(p, entity);
                    }
                });
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

    /**
     * Insert a property into the database
     * @param property to be inserted
     * @param carpoCase case that owns the property
     */
    private void insertProperty(Property property, CarpoCase carpoCase) {
        PropertyWrapper wrapper = new PropertyWrapper(property);
        jdbi.useExtension(PropertyDao.class, h -> h.insert(wrapper, carpoCase.getCarpoId()));
    }
}
