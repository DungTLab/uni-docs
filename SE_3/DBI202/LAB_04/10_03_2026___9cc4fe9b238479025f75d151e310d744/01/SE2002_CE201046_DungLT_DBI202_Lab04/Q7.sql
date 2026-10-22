SELECT 
    d.department_id, 
    d.department_name, 
    ROUND(AVG(e.salary), 2) AS avg_salary
FROM departments d
JOIN employees e ON d.department_id = e.department_id
GROUP BY d.department_id, d.department_name
HAVING AVG(e.salary) BETWEEN 5000 AND 7000
ORDER BY avg_salary ASC;