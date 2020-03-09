SELECT p.id p_id,
       p.property_key p_property_key,
       p.string_value p_string_value,
       p.long_value p_long_value,
       p.double_value p_double_value,
       p.boolean_value p_boolean_value,
       p.null_value p_null_value,
       p.property_type p_property_type
FROM carpo_case_property p
WHERE p.id = :id