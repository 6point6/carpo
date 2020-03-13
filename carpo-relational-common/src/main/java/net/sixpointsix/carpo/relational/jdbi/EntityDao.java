package net.sixpointsix.carpo.relational.jdbi;

import net.sixpointsix.carpo.common.model.CarpoPropertyEntity;
import net.sixpointsix.carpo.common.repository.SelectProperties;
import net.sixpointsix.carpo.relational.jdbi.mapper.ImmutableEntityRowMapper;
import net.sixpointsix.carpo.relational.jdbi.mapper.ImmutablePropertyRowMapper;
import net.sixpointsix.carpo.relational.jdbi.reducer.CarpoPropertyReducer;
import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.customizer.BindMap;
import org.jdbi.v3.sqlobject.customizer.Define;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.statement.UseRowReducer;
import org.jdbi.v3.stringtemplate4.UseStringTemplateSqlLocator;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Generic Entity DAO
 *
 * @author Andrew Tarry
 * @since 0.4.0
 */
public interface EntityDao {

    /**
     * Insert the entity
     *
     * @param tableName   name of the table to add the data to
     * @param id          to be added
     * @param createdAt   created date
     * @param lastUpdated updated date
     */
    @SqlUpdate
    @UseStringTemplateSqlLocator
    void insert(@Define("tableName") String tableName,
                @Bind("id") String id,
                @Bind("createdAt") LocalDateTime createdAt,
                @Bind("lastUpdated") LocalDateTime lastUpdated);

    /**
     * Update the last updated timestamp
     * @param id id of the case
     * @param lastUpdated current time
     */
    @SqlUpdate
    @UseStringTemplateSqlLocator
    void update(
            @Define("tableName") String tableName,
            @Bind("id") String id,
            @Bind("lastUpdated") LocalDateTime lastUpdated);

    /**
     * Insert the entity
     *
     * @param tableName   name of the table to add the data to
     * @param id          to be added
     */
    @SqlQuery
    @RegisterRowMapper(ImmutablePropertyRowMapper.class)
    @RegisterRowMapper(ImmutableEntityRowMapper.class)
    @UseRowReducer(CarpoPropertyReducer.class)
    @UseStringTemplateSqlLocator
    Optional<CarpoPropertyEntity> selectById(@Define("tableName") String tableName,
                                             @Define("propertyTableName") String propertyTableName,
                                             @Bind("id") String id);

    /**
     * Get a list of entities
     * @param tableName name of the table
     * @param propertyTableName properties table
     * @param selectProperties properties to select by
     * @return list of entities
     */
    @SqlQuery
    @RegisterRowMapper(ImmutablePropertyRowMapper.class)
    @RegisterRowMapper(ImmutableEntityRowMapper.class)
    @UseRowReducer(CarpoPropertyReducer.class)
    @UseStringTemplateSqlLocator
    List<CarpoPropertyEntity> selectWithProperties(@Define("tableName") String tableName,
                                                   @Define("propertyTableName") String propertyTableName,
                                                   @Define("selectByProps") boolean selectByProps,
                                                   @BindBean("p") SelectProperties selectProperties,
                                                   @BindMap("v") Map<String, ?> search,
                                                   @Define("where") String where);
}
