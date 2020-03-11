package net.sixpointsix.carpo.casedata.relational;

import net.sixpointsix.carpo.casedata.model.CarpoCase;
import net.sixpointsix.carpo.casedata.model.builder.CarpoCaseBuilder;
import net.sixpointsix.carpo.casedata.repository.CaseDataEntityRepository;
import net.sixpointsix.carpo.common.model.CarpoPropertyEntity;
import net.sixpointsix.carpo.relational.JdbiRelationalManager;
import net.sixpointsix.carpo.relational.MutableRelationalConfiguration;
import org.jdbi.v3.core.Jdbi;

import java.util.Optional;

/**
 * Case data repository using a relational database
 *
 * @author Andrew Tarry
 * @since 0.3.0
 */
public class RelationalCaseDataEntityRepository implements CaseDataEntityRepository {

    private final JdbiRelationalManager jdbiRelationalManager;

    /**
     * Build the repository from JDBI
     * @param jdbi jdbi instance
     * @return repository
     */
    public static RelationalCaseDataEntityRepository build(Jdbi jdbi) {
        MutableRelationalConfiguration relationalConfiguration = new MutableRelationalConfiguration();
        relationalConfiguration.setPropertyTable("carpo_case_property");
        relationalConfiguration.setEntityDataTable("carpo_case");

        return new RelationalCaseDataEntityRepository(new JdbiRelationalManager(jdbi, relationalConfiguration));
    }

    public RelationalCaseDataEntityRepository(JdbiRelationalManager jdbiRelationalManager) {
        this.jdbiRelationalManager = jdbiRelationalManager;
    }

    /**
     * Create a new record of the entity
     *
     * @param entity to be saved
     */
    @Override
    public void create(CarpoCase entity) {
        jdbiRelationalManager.create(entity);
    }

    /**
     * Save the changes to entity
     *
     * @param entity to be saved
     */
    @Override
    public void update(CarpoCase entity) {
        jdbiRelationalManager.update(entity);
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
        Optional<CarpoPropertyEntity> entity = jdbiRelationalManager.getById(id);

        return entity.map(c -> new CarpoCaseBuilder(c).build());
    }
}
