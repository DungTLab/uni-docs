SELECT p.product_id,p.product_name,p.product_price
FROM [Product] p
JOIN LineItem li ON li.product_id = p.product_id
GROUP BY p.product_id,p.product_name,p.product_price
HAVING AVG(li.price) > p.product_price