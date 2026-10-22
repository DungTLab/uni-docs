SELECT p.Id AS ProductId, p.ProductName, SUM(oi.Quantity) AS Quantity
FROM Product p
JOIN OrderItem oi ON p.Id = oi.ProductId
JOIN [Order] o ON oi.OrderId = o.Id AND MONTH(o.OrderDate) = 12
GROUP BY p.Id, p.ProductName
HAVING SUM(oi.Quantity) >= 120
ORDER BY Quantity ASC;