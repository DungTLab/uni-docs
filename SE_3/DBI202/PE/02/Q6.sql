SELECT e.employee_id, (e.first_name + ' ' + e.last_name) AS 'full_name', j.job_title, e.hire_date,  2022 - YEAR(e.hire_date) AS 'seniority'
FROM employees e
JOIN jobs j
ON e.job_id = j.job_id
WHERE (2022 - YEAR(e.hire_date)) > 30
ORDER BY seniority DESC;