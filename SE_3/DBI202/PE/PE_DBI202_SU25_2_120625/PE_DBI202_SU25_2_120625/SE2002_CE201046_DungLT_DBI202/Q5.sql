SELECT DISTINCT e.employee_name
FROM Employee e
JOIN Orders o ON e.employee_id = o.employee_id
WHERE o.order_id NOT IN (
    SELECT order_id 
    FROM LineItem
    WHERE order_id IS NOT NULL
)