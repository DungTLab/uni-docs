SELECT e.employee_id, (e.first_name + ', ' + e.last_name) AS full_name, j.job_title, e.hire_date
FROM employees e
    JOIN jobs j
    ON e.job_id = j.job_id
WHERE YEAR(e.hire_date) BETWEEN 1990 AND 1993;