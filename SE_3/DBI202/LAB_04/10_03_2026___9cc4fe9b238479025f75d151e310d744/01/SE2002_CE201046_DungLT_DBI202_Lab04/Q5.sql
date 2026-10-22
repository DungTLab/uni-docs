SELECT c.country_name, COUNT(d.department_id) AS department_no
FROM countries c
    JOIN locations l
    ON c.country_id = l.country_id
    JOIN departments d
    ON l.location_id = d.location_id
GROUP BY c.country_name
HAVING COUNT(d.department_id) > 1;