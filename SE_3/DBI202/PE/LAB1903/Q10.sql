DELETE FROM product
WHERE Id NOT IN (
    SELECT ProductId 
    FROM OrderItem 
    WHERE ProductId IS NOT NULL
);