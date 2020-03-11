package net.sixpointsix.carpo.relational.jdbi.mapper;

import net.sixpointsix.carpo.common.model.CarpoPropertyEntity;
import net.sixpointsix.carpo.common.model.PropertyCollection;
import net.sixpointsix.carpo.common.model.Timestamp;
import net.sixpointsix.carpo.common.model.immutable.ImmutableTimestamp;
import net.sixpointsix.carpo.common.model.mutable.MutablePropertyCollection;
import net.sixpointsix.carpo.relational.CarpoColumn;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

/**
 * Build an entity that can be used by other classes
 *
 * @author Andrew Tarry
 * @since 0.4.0
 */
public class ImmutableEntityRowMapper implements RowMapper<CarpoPropertyEntity> {

    /**
     * Map the current row of the result set.
     * This method should not cause the result set to advance; allow Jdbi to do that, please.
     *
     * @param rs  the result set being iterated
     * @param ctx the statement context
     * @return the value to produce for this row
     * @throws SQLException if anything goes wrong go ahead and let this percolate; Jdbi will handle it
     */
    @Override
    public CarpoPropertyEntity map(ResultSet rs, StatementContext ctx) throws SQLException {
        String id = rs.getString(CarpoColumn.ENTITY_ID_COL);
        LocalDateTime createdAt = rs.getTimestamp(CarpoColumn.ENTITY_CREATED_COL).toLocalDateTime();
        LocalDateTime lastUpdated = rs.getTimestamp(CarpoColumn.ENTITY_LAST_UPDATED).toLocalDateTime();

        return new CarpoPropertyEntity() {

            private final PropertyCollection properties = new MutablePropertyCollection();

            @Override
            public String getCarpoId() {
                return id;
            }

            @Override
            public Timestamp getTimestamp() {
                return ImmutableTimestamp.build(createdAt, lastUpdated);
            }

            @Override
            public PropertyCollection getProperties() {
                return properties;
            }
        };
    }
}
