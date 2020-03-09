package net.sixpointsix.carpo.casedata.relational.jdbi;

import net.sixpointsix.carpo.casedata.relational.jdbi.model.PropertyWrapper;
import net.sixpointsix.carpo.casedata.relational.jdbi.rowmapper.ImmutablePropertyRowMapper;
import net.sixpointsix.carpo.common.model.Property;
import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindMethods;
import org.jdbi.v3.sqlobject.locator.UseClasspathSqlLocator;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.Optional;

@UseClasspathSqlLocator
public interface PropertyDao {

    @SqlUpdate
    void insert(@BindMethods("p") PropertyWrapper property, @Bind("caseId") String caseId);

    @SqlQuery
    @RegisterRowMapper(ImmutablePropertyRowMapper.class)
    Optional<Property> selectById(@Bind("id") String id);

    @SqlQuery
    @RegisterRowMapper(ImmutablePropertyRowMapper.class)
    Optional<Property> selectByKey(@Bind("caseId") String caseId, @Bind("propertyKey") String propertyKey);

    @SqlUpdate
    boolean updateProperty(@BindMethods("p") Property property);
}
