package net.sixpointsix.carpo.relational.jdbi.reducer;

import net.sixpointsix.carpo.common.model.CarpoPropertyEntity;
import net.sixpointsix.carpo.common.model.Property;
import net.sixpointsix.carpo.relational.CarpoColumn;
import org.jdbi.v3.core.result.LinkedHashMapRowReducer;
import org.jdbi.v3.core.result.RowReducer;
import org.jdbi.v3.core.result.RowView;

import java.util.Map;

/**
 * Generic property reducer to map properties to the parent entity
 *
 * @author Andrew Tarry
 * @since 0.4.0
 */
public class CarpoPropertyReducer implements LinkedHashMapRowReducer<String, CarpoPropertyEntity> {

    /**
     * Accumulate data from the current row into the result container. Do not attempt
     * to accumulate the {@link RowView} itself into the result container--it is only
     * valid within the {@link RowReducer#accumulate(Object, RowView) accumulate()}
     * method invocation. Instead, extract mapped types from the RowView by calling
     * {@code RowView.getRow()} or {@code RowView.getColumn()} and store those values
     * in the container.
     *
     * @param container the result container
     * @param rowView   row view over the current result set row.
     */
    @Override
    public void accumulate(Map<String, CarpoPropertyEntity> container, RowView rowView) {
        final String caseId = rowView.getColumn(CarpoColumn.ENTITY_ID_COL, String.class);
        final String propertyKey = rowView.getColumn(CarpoColumn.PROPERTY_KEY_COL, String.class);
        final CarpoPropertyEntity entity = container.computeIfAbsent(caseId, id -> rowView.getRow(CarpoPropertyEntity.class));

        if (propertyKey != null) {
            Property property = rowView.getRow(Property.class);

            if(property != null ) {
                entity.getProperties().add(property);
            }
        }
    }
}
