SELECT e.employee_id, e.first_name, e.last_name, e.salary, j.job_title
FROM employees e
JOIN jobs j
ON e.job_id = j.job_id
WHERE e.manager_id = (SELECT e2.employee_id
					FROM employees e2
					WHERE e2.first_name = 'Nancy' AND e2.last_name = 'GreenBerg')
ORDER BY e.employee_id ASC;