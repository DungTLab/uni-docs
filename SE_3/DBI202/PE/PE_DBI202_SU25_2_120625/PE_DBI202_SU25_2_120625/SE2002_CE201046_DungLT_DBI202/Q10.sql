DELETE FROM Customer
WHERE customer_id NOT IN (
    SELECT o.customer_id
    FROM Orders o
    WHERE o.customer_id IS NOT NULL
);