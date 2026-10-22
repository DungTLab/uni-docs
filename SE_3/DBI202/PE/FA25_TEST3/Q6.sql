WITH ItemSales AS (
    SELECT 
        m.ItemID,
        m.ItemName,
        m.Category,
        ISNULL(SUM(CASE 
            WHEN o.Status = 'Completed'
             AND YEAR(o.OrderTime) = 2023
            THEN od.Quantity * od.Price
            ELSE 0
        END), 0) AS TotalAmount
    FROM MenuItems m
    LEFT JOIN OrderDetails od 
        ON m.ItemID = od.ItemID
    LEFT JOIN Orders o 
        ON od.OrderID = o.OrderID
    GROUP BY m.ItemID, m.ItemName, m.Category
)
SELECT 
    ItemID,
    ItemName,
    Category,
    TotalAmount
FROM ItemSales
WHERE TotalAmount < (
    SELECT AVG(TotalAmount * 1.0)
    FROM ItemSales
)
ORDER BY TotalAmount;