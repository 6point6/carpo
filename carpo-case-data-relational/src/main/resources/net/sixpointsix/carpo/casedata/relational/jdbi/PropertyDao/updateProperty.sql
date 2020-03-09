UPDATE carpo_case_property
SET string_value = :p.getStringValue,
    long_value = :p.getLongValue,
    double_value = :p.getDoubleValue,
    boolean_value = :p.getBooleanValue,
    null_value = :p.isNull,
    property_type = :p.getType
WHERE id = :p.getId