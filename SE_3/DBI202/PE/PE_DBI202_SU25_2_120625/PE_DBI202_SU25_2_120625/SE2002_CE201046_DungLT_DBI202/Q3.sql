SELECT p.product_name
FROM Product p
JOIN LineItem li ON p.product_id = li.product_id
WHERE p.product_name = 'Mouse';