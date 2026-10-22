WITH ItemSales AS (
    SELECT 
        mi.Category, mi.MenuItemID, mi.Name,
        SUM(od.Quantity) AS ItemTotalQty,
        RANK() OVER (PARTITION BY mi.Category ORDER BY SUM(od.Quantity) DESC) AS ItemRank
    FROM MenuItems mi
    JOIN OrderDetails od ON mi.MenuItemID = od.MenuItemID
    GROUP BY mi.Category, mi.MenuItemID, mi.Name
),

CustomerSales AS (
    SELECT 
        o.CustomerID, c.FullName, od.MenuItemID,
        SUM(od.Quantity) AS CustTotalQty,
        RANK() OVER (PARTITION BY od.MenuItemID ORDER BY SUM(od.Quantity) DESC) AS CustRank
    FROM Orders o
    JOIN Customers c ON o.CustomerID = c.CustomerID
    JOIN OrderDetails od ON o.OrderID = od.OrderID
    GROUP BY o.CustomerID, c.FullName, od.MenuItemID
)

SELECT 
    its.Category, 
    its.MenuItemID, 
    its.Name, 
    cs.CustomerID, 
    cs.FullName, 
    cs.CustTotalQty AS TotalQuantity
FROM ItemSales its
JOIN CustomerSales cs ON its.MenuItemID = cs.MenuItemID
WHERE its.ItemRank = 1 
  AND cs.CustRank = 1  
ORDER BY its.Category;