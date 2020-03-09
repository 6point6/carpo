INSERT INTO carpo_case_property(id, property_key, string_value, long_value, double_value, boolean_value, null_value, property_type, carpo_case_id)
VALUES (:p.getId,
        :p.getKey,
        :p.getStringValue,
        :p.getLongValue,
        :p.getDoubleValue,
        :p.getBooleanValue,
        :p.isNull,
        :p.getType,
        :caseId)