DELETE s
FROM stocks s
JOIN products p
ON s.product_id = p.product_id
WHERE p.category_name = 'Mountain';