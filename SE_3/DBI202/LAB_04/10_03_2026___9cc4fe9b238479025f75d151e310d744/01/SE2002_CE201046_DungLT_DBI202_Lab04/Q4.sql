SELECT e.first_name, e.last_name, j.job_title, d.department_name, c.country_name, e.salary
FROM employees e
    JOIN jobs j ON e.job_id = j.job_id
    JOIN departments d ON e.department_id = d.department_id
    JOIN locations l ON d.location_id = l.location_id
    JOIN countries c ON l.country_id = c.country_id
    JOIN regions r ON c.region_id = r.region_id
WHERE e.salary > 7000 AND r.region_name = 'Europe'
ORDER BY e.salary DESC;
