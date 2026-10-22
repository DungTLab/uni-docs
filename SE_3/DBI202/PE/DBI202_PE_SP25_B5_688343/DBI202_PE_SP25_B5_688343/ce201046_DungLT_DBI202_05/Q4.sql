SELECT e.EmployeeID, e.FullName AS EmployeeFullName, e.[Role], o.OrderID, o.OrderDate, c.CustomerID, c.FullName AS CustomerFullName 
FROM Employees e
LEFT JOIN Orders o ON e.EmployeeID = o.EmployeeID 
AND (YEAR(o.OrderDate) = 2025 AND MONTH(o.OrderDate) BETWEEN 3  AND 4)
LEFT JOIN Customers c ON o.CustomerID = c.CustomerID
WHERE e.[Role] = 'Chef'
ORDER BY e.FullName ASC, o.OrderID DESC;