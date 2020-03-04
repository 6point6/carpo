package net.sixpointsix.carpo.casedata.relational.jdbi;

import net.sixpointsix.carpo.casedata.model.CarpoCase;
import net.sixpointsix.carpo.casedata.relational.jdbi.rowmapper.ImmutableCarpoCaseRowMapper;
import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.locator.UseClasspathSqlLocator;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.time.LocalDateTime;
import java.util.Optional;

@UseClasspathSqlLocator
public interface CaseDataDao {

    /**
     * Insert the case data
     * @param id to be added
     * @param createdAt created date
     * @param lastUpdated updated date
     */
    @SqlUpdate
    void insertCaseData(@Bind("id") String id,
                        @Bind("createdAt") LocalDateTime createdAt,
                        @Bind("lastUpdated") LocalDateTime lastUpdated);


    @SqlQuery
    @RegisterRowMapper(ImmutableCarpoCaseRowMapper.class)
    Optional<CarpoCase> selectById(@Bind("id") String id);
}
