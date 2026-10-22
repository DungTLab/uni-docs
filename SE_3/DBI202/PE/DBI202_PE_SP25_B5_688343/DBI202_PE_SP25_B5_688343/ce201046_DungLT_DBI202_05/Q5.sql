SELECT 
    mi.MenuItemID, 
    mi.Name, 
    mi.Category, 
    ISNULL(temp.TotalQuantity, 0) AS TotalQuantity, 
    ISNULL(temp.TotalAmount, 0) AS TotalAmount, 
    ISNULL(temp.NumberOfCustomers, 0) AS NumberOfCustomers
FROM MenuItems mi
LEFT JOIN (
    SELECT 
        od.MenuItemID, 
        SUM(od.Quantity) AS TotalQuantity, 
        SUM(od.Price * od.Quantity) AS TotalAmount,
        COUNT(DISTINCT o.CustomerID) AS NumberOfCustomers
    FROM OrderDetails od
    INNER JOIN Orders o ON od.OrderID = o.OrderID
    WHERE YEAR(o.OrderDate) = 2024 AND MONTH(o.OrderDate) = 10
    GROUP BY od.MenuItemID
) AS temp ON mi.MenuItemID = temp.MenuItemID
WHERE mi.Category = 'Main Course'
ORDER BY mi.MenuItemID ASC;