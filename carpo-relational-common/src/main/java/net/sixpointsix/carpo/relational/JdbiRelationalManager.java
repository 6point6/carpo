package net.sixpointsix.carpo.relational;

import net.sixpointsix.carpo.common.model.util.PropertyUtil;
import net.sixpointsix.carpo.common.repository.SelectProperties;
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
import java.util.*;

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

    public List<CarpoPropertyEntity> getBySelectProperties(SelectProperties selectProperties) {
        boolean searchByValue = !selectProperties.getSearchProperties().isEmpty();
        Map<String, Object> searchParams = new HashMap<>();
        List<String> whereClause = new ArrayList<>();

        if(searchByValue) {
            selectProperties
                    .getSearchProperties()
                    .forEach(p -> {
                        StringBuilder where = new StringBuilder();
                        searchParams.put("key_" + p.getKey(), p.getKey());
                        searchParams.put("value_" + p.getKey(), PropertyUtil.getValue(p));
                        where
                                .append("(p.property_key = :v.key_")
                                .append(p.getKey())
                                .append(" AND ");

                        switch (p.getType()) {
                            case STRING:
                                where.append("p.string_value");
                                break;
                            case LONG:
                                where.append("p.long_value");
                                break;
                            case DOUBLE:
                                where.append("p.double_value");
                                break;
                            case BOOLEAN:
                                where.append("p.boolean_value");
                                break;
                        }

                        where.append(" = :v.value_")
                                .append(p.getKey())
                                .append(")");

                        whereClause.add(where.toString());
                    });
        }

        return jdbi.withExtension(EntityDao.class, h -> h.selectWithProperties(
                relationalConfiguration.getEntityDataTable(),
                relationalConfiguration.getPropertyTable(),
                searchByValue,
                selectProperties,
                searchParams,
                String.join(" OR ", whereClause)
        ));
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
