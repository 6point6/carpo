package net.sixpointsix.carpo.relational;

import net.sixpointsix.carpo.relational.jdbi.PropertyDao;
import net.sixpointsix.carpo.relational.jdbi.model.PropertyWrapper;
import net.sixpointsix.carpo.common.model.CarpoEntity;
import net.sixpointsix.carpo.common.model.CarpoPropertyEntity;
import net.sixpointsix.carpo.common.model.Property;
import net.sixpointsix.carpo.relational.jdbi.EntityDao;
import org.jdbi.v3.core.Jdbi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Relational data manager using jdbi
 *
 * @since 0.4.0
 * @author Andrew Tarry
 */
public class JdbiRelationalManager {

    private static final Logger logger = LoggerFactory.getLogger(JdbiRelationalManager.class);

    private final Jdbi jdbi;
    private final RelationalConfiguration relationalConfiguration;

    public JdbiRelationalManager(Jdbi jdbi, RelationalConfiguration relationalConfiguration) {
        this.jdbi = jdbi;
        this.relationalConfiguration = relationalConfiguration;
    }

    public void create(CarpoPropertyEntity entity) {
        jdbi.useExtension(EntityDao.class,
                h -> h.insert(
                        relationalConfiguration.getEntityDataTable(),
                        entity.getCarpoId(),
                        entity.getTimestamp().getCreatedAt(),
                        entity.getTimestamp().getLastUpdated()));

        entity.getProperties()
                .forEach(p -> insertProperty(p, entity));
    }

    /**
     * Save the changes to entity
     *
     * @param entity to be saved
     */
    public void update(CarpoPropertyEntity entity) {
        jdbi.useExtension(EntityDao.class, h -> h.update(relationalConfiguration.getEntityDataTable(), entity.getCarpoId(), LocalDateTime.now()));
        entity
                .getProperties()
                .parallelStream()
                .forEach(p -> {
                    Optional<Property> existingPropertyOptional = jdbi.withExtension(PropertyDao.class, h -> h.selectByKey(
                            relationalConfiguration.getPropertyTable(),
                            entity.getCarpoId(),
                            p.getKey()));

                    if(existingPropertyOptional.isPresent()) {
                        final Property existingProperty = existingPropertyOptional.get();

                        if(!existingProperty.equals(p)) {
                            jdbi.useExtension(PropertyDao.class, h -> h.updateProperty(relationalConfiguration.getPropertyTable(), p));
                        }
                    } else {
                        insertProperty(p, entity);
                    }
                });
    }

    /**
     * Get an entity by an id
     *
     * @param id id to search for
     * @return entity data
     */
    public Optional<CarpoPropertyEntity> getById(String id) {
        return jdbi.withExtension(EntityDao.class, h -> h.selectById(
                relationalConfiguration.getEntityDataTable(),
                relationalConfiguration.getPropertyTable(),
                id));
    }

    /**
     * Insert a property into the database
     * @param property to be inserted
     * @param carpoCase case that owns the property
     */
    private void insertProperty(Property property, CarpoEntity carpoCase) {
        logger.info("Adding property {}", property.toString());
        PropertyWrapper wrapper = new PropertyWrapper(property);
        jdbi.useExtension(PropertyDao.class, h -> h.insert(
                relationalConfiguration.getPropertyTable(),
                wrapper,
                carpoCase.getCarpoId()));
    }
}
