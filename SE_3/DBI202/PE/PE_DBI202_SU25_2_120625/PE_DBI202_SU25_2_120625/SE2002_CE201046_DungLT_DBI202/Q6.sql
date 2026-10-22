SELECT e.employee_name, o.order_id, SUM(l.quantity) AS total
FROM Employee e
JOIN Orders o ON o.customer_id = e.employee_id
JOIN LineItem l ON o.order_id = l.order_id
WHERE YEAR(o.order_date) = 2020
GROUP BY e.employee_name, o.order_id
HAVING SUM(l.quantity) >= 10;