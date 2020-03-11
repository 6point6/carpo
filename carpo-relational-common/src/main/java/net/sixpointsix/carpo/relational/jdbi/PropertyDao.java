package net.sixpointsix.carpo.relational.jdbi;

import net.sixpointsix.carpo.relational.jdbi.mapper.ImmutablePropertyRowMapper;
import net.sixpointsix.carpo.relational.jdbi.model.PropertyWrapper;
import net.sixpointsix.carpo.common.model.Property;
import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindMethods;
import org.jdbi.v3.sqlobject.customizer.Define;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.stringtemplate4.UseStringTemplateSqlLocator;

import java.util.Optional;

/**
 * Manage properties within the database
 *
 * @since 0.4.0
 * @author Andrew Tarry
 */
public interface PropertyDao {

    /**
     * Insert a new property
     *
     * @param propertyTableName table to add it to
     * @param property property to add
     * @param entityId entityId
     */
    @SqlUpdate
    @UseStringTemplateSqlLocator
    void insert(
            @Define("propertyTableName") String propertyTableName,
            @BindMethods("p") PropertyWrapper property,
            @Bind("entityId") String entityId);

//    @SqlQuery
//    @RegisterRowMapper(ImmutablePropertyRowMapper.class)
//    Optional<Property> selectById(@Bind("id") String id);
//
    @SqlQuery
    @RegisterRowMapper(ImmutablePropertyRowMapper.class)
    @UseStringTemplateSqlLocator
    Optional<Property> selectByKey(
            @Define("propertyTableName") String propertyTableName,
            @Bind("entityId") String entityId,
            @Bind("propertyKey") String propertyKey);

    @SqlUpdate
    @UseStringTemplateSqlLocator
    boolean updateProperty(
            @Define("propertyTableName") String propertyTableName,
            @BindMethods("p") Property property);
}
