SELECT c.FirstName, c.LastName, SUM(oi.Quantity) AS 'TotalQuantity'
FROM Customer c
JOIN [Order] o
ON c.Id = o.CustomerId
JOIN OrderItem oi
ON o.Id = oi.OrderId
JOIN [Product] p
ON oi.ProductId = p.Id AND p.ProductName = 'Longlife Tofu'
GROUP BY c.FirstName, c.LastName
ORDER BY c.FirstName ASC;
