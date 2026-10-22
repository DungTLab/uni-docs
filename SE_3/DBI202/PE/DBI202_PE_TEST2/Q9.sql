SELECT s.staff_id, s.first_name, s.last_name
FROM staffs s
JOIN orders o
ON s.staff_id = o.staff_id
JOIN customers c
ON o.customer_id = c.customer_id
WHERE o.order_status = 4 AND c.state = 'CA'
ORDER BY s.staff_id ASC;