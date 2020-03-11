SELECT c.id c_id,
       c.created_at c_created_at,
       c.last_updated c_last_updated,
       p.id p_id,
       p.property_key p_property_key,
       p.string_value p_string_value,
       p.long_value p_long_value,
       p.double_value p_double_value,
       p.boolean_value p_boolean_value,
       p.null_value p_null_value,
       p.property_type p_property_type
FROM carpo_case c
LEFT JOIN carpo_case_property p on c.id = p.carpo_case_id
WHERE c.id = :id