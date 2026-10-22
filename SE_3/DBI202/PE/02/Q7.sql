SELECT d.department_id, d.department_name, AVG(e.salary) AS 'avgg_salary'
FROM departments d
JOIN employees e
ON d.department_id = e.department_id
GROUP BY d.department_id, d.department_name
HAVING AVG(e.salary) < (
	SELECT AVG(e2.salary)
	FROM employees e2
)
ORDER BY d.department_id ASC;