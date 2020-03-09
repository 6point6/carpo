package net.sixpointsix.carpo.casedata.relational.jdbi.reducer;

import net.sixpointsix.carpo.casedata.model.CarpoCase;
import net.sixpointsix.carpo.casedata.relational.jdbi.CarpoCaseColumn;
import net.sixpointsix.carpo.common.model.Property;
import org.jdbi.v3.core.result.LinkedHashMapRowReducer;
import org.jdbi.v3.core.result.RowReducer;
import org.jdbi.v3.core.result.RowView;

import java.util.Map;

public class CarpoCaseReducer implements LinkedHashMapRowReducer<String, CarpoCase> {

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
    public void accumulate(Map<String, CarpoCase> container, RowView rowView) {
        final String caseId = rowView.getColumn(CarpoCaseColumn.CASE_ID_COL, String.class);
        final String propertyKey = rowView.getColumn(CarpoCaseColumn.PROPERTY_KEY_COL, String.class);
        final CarpoCase carpoCase = container.computeIfAbsent(caseId, id -> rowView.getRow(CarpoCase.class));

        if (propertyKey != null) {
            Property property = rowView.getRow(Property.class);

            if(property != null ) {
                carpoCase.getProperties().add(property);
            }
        }
    }
}
