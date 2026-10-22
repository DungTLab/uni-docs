SELECT category_name, product_id, product_name, list_price
FROM products p1
WHERE list_price = (
    SELECT MAX(list_price) 
    FROM products p2 
    WHERE p1.category_name = p2.category_name 
)
ORDER BY category_name ASC, product_id ASC;


-- 2
SELECT p.category_name, p.product_id, p.product_name, p.list_price
FROM products p
INNER JOIN (
    -- Truy vấn con: Tìm giá cao nhất cho từng danh mục
    SELECT category_name, MAX(list_price) AS max_price
    FROM products
    GROUP BY category_name
) t ON p.category_name = t.category_name AND p.list_price = t.max_price
ORDER BY p.category_name ASC, p.product_id ASC;