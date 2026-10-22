SELECT o.OrderID, o.OrderDate, c.FullName AS 'CustomerFullName', c.PhoneNumber, rt.TableNumber, rt.Capacity
FROM Orders o
JOIN RestaurantTables rt ON o.TableID = rt.TableID AND rt.Capacity =6
JOIN Customers c ON o.CustomerID = c.CustomerID
WHERE YEAR(o.OrderDate) = 2024 AND (MONTH(o.OrderDate) = 11 OR MONTH(o.OrderDate) = 12)