SELECT c.CustomerID, c.FullName, COUNT(o.OrderID) AS NumberOfOrders, ISNULL(SUM(o.TotalAmount), 0) AS 'Total Amount'
FROM Customers c
LEFT JOIN Orders o
ON c.CustomerID = o.CustomerID AND
	YEAR (o.OrderTime) = 2025 AND
	o.Status = 'Completed'
WHERE c.FullName LIKE N'Nguyễn%' OR c.FullName LIKE N'Bùi%'
GROUP BY c.CustomerID, c.FullName