SELECT order_id, customer_id, order_date, store_id, staff_id
FROM orders
WHERE order_date BETWEEN '2025-08-05' AND '2025-08-15'
ORDER BY order_date ASC;