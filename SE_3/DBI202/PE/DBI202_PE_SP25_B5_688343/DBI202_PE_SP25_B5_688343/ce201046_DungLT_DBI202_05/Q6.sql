SELECT e.EmployeeID, e.FullName, e.Role, COUNT(o.OrderID) AS OrderCount
FROM Employees e
JOIN Orders o ON e.EmployeeID = o.EmployeeID
GROUP BY e.EmployeeID, e.FullName, e.Role
HAVING COUNT(o.OrderID) > (
    SELECT AVG(CAST(SoDon AS FLOAT)) 
    FROM (
        SELECT COUNT(OrderID) AS SoDon 
        FROM Orders 
        GROUP BY EmployeeID 
    ) AS TinhToanTam
)
ORDER BY OrderCount DESC, FullName ASC;