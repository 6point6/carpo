package net.sixpointsix.carpo.casedata.relational.jdbi;

import net.sixpointsix.carpo.casedata.relational.jdbi.model.PropertyWrapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindMethods;
import org.jdbi.v3.sqlobject.locator.UseClasspathSqlLocator;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

@UseClasspathSqlLocator
public interface PropertyDao {

    @SqlUpdate
    void insert(@BindMethods("p") PropertyWrapper property, @Bind("caseId") String caseId);

}
