SELECT s.store_id, s.store_name, SUM(sk.quantity) AS 'total_qty_in_stock'
FROM stores s
JOIN stocks sk
ON s.store_id = sk.store_id
GROUP BY s.store_id, s.store_name
ORDER BY total_qty_in_stock DESC;

