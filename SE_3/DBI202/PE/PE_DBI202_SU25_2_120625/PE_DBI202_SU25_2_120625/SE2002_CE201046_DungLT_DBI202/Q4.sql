SELECT o.order_id, e.employee_id, o.order_date
FROM Orders o
JOIN Employee e ON o.employee_id = e.employee_id
WHERE YEAR(o.order_date) = 2020;