package net.sixpointsix.carpo.casedata.relational.jdbi;

public abstract class CarpoCaseColumn {

    // Case

    public static final String CASE_ID_COL = "c_id";
    public static final String CASE_CREATED_COL = "c_created_at";
    public static final String CASE_LAST_UPDATED = "c_last_updated";

    // Property

    public static final String PROPERTY_TYPE_COL = "p_property_type";
    public static final String PROPERTY_KEY_COL = "p_property_key";
    public static final String PROPERTY_STRING_COL = "p_string_value";
    public static final String PROPERTY_LONG_COL = "p_long_value";
    public static final String PROPERTY_DOUBLE_COL = "p_double_value";
    public static final String PROPERTY_BOOLEAN_COL = "p_boolean_value";
    public static final String PROPERTY_ID = "p_id";
}
