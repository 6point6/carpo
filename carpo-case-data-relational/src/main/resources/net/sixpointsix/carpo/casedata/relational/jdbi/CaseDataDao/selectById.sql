SELECT c.id c_id,
       c.created_at c_created_at,
       c.last_updated c_last_updated
FROM carpo_case c
WHERE c.id = :id