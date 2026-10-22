SELECT c.country_name
FROM countries c
LEFT JOIN locations l
ON c.country_id = l.country_id
LEFT JOIN departments d
ON l.location_id = d.location_id
WHERE d.location_id IS NULL
ORDER BY c.country_name ASC;