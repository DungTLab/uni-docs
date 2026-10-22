SELECT e.employee_id, e.first_name, e.last_name
FROM employees e
    LEFT JOIN dependents d ON e.employee_id = d.employee_id
WHERE d.employee_id IS NULL
ORDER BY e.employee_id DESC;