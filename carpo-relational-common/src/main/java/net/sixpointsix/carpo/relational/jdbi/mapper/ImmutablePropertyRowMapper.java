package net.sixpointsix.carpo.relational.jdbi.mapper;

import net.sixpointsix.carpo.common.model.Property;
import net.sixpointsix.carpo.common.model.PropertyType;
import net.sixpointsix.carpo.common.model.immutable.ImmutableProperty;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;

import static net.sixpointsix.carpo.relational.CarpoColumn.*;

/**
 * Map properties from data rows
 *
 * @author Andrew Tarry
 * @since 0.4.0
 */
public class ImmutablePropertyRowMapper implements RowMapper<Property> {

    private static final Logger logger = LoggerFactory.getLogger(ImmutablePropertyRowMapper.class);

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
    public Property map(ResultSet rs, StatementContext ctx) throws SQLException {
        PropertyType type = PropertyType.valueOf(rs.getString(PROPERTY_TYPE_COL));
        Property property = null;

        logger.debug("Mapping property with type {}", type);

        switch (type) {
            case STRING:
                property = getStringProperty(rs);
                break;
            case LONG:
                property = getLongProperty(rs);
                break;
            case DOUBLE:
                property = getDoubleProperty(rs);
                break;
            case BOOLEAN:
                property = getBooleanProperty(rs);
                break;
        }

        return property;
    }

    private Property getStringProperty(ResultSet resultSet) throws SQLException {
        String string = resultSet.getString(PROPERTY_STRING_COL);

        return ImmutableProperty.build(getKey(resultSet), string, getId(resultSet));
    }

    private Property getLongProperty(ResultSet resultSet) throws SQLException {
        Long longVal = resultSet.getLong(PROPERTY_LONG_COL);

        return ImmutableProperty.build(getKey(resultSet), longVal, getId(resultSet));
    }

    private Property getDoubleProperty(ResultSet resultSet) throws SQLException {
        Double doubleVal = resultSet.getDouble(PROPERTY_DOUBLE_COL);

        return ImmutableProperty.build(getKey(resultSet), doubleVal, getId(resultSet));
    }

    private Property getBooleanProperty(ResultSet resultSet) throws SQLException {
        Boolean booleanVal = resultSet.getBoolean(PROPERTY_BOOLEAN_COL);

        return ImmutableProperty.build(getKey(resultSet), booleanVal, getId(resultSet));
    }

    private String getKey(ResultSet resultSet) throws SQLException {
        return resultSet.getString(PROPERTY_KEY_COL);
    }

    private String getId(ResultSet resultSet) throws SQLException {
        return resultSet.getString(PROPERTY_ID);
    }
}
