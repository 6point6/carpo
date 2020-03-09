package net.sixpointsix.carpo.casedata.relational.jdbi.rowmapper;

import net.sixpointsix.carpo.casedata.model.CarpoCase;
import net.sixpointsix.carpo.casedata.model.builder.CarpoCaseBuilder;
import net.sixpointsix.carpo.casedata.relational.jdbi.CarpoCaseColumn;
import net.sixpointsix.carpo.common.model.Timestamp;
import net.sixpointsix.carpo.common.model.immutable.ImmutableTimestamp;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

/**
 * Build a carpo case from the result set
 *
 * @author Andrew Tarry
 * @since 0.2.0
 */
public class ImmutableCarpoCaseRowMapper implements RowMapper<CarpoCase> {

    @Override
    public CarpoCase map(ResultSet rs, StatementContext ctx) throws SQLException {
        String id = rs.getString(CarpoCaseColumn.CASE_ID_COL);
        LocalDateTime createdAt = rs.getTimestamp(CarpoCaseColumn.CASE_CREATED_COL).toLocalDateTime();
        LocalDateTime lastUpdated = rs.getTimestamp(CarpoCaseColumn.CASE_LAST_UPDATED).toLocalDateTime();

        Timestamp timestamp = ImmutableTimestamp.build(createdAt, lastUpdated);

        CarpoCaseBuilder builder = CarpoCase.builder();
        builder.setCarpoId(id);
        builder.setTimestamp(timestamp);

        return builder.build();
    }
}
