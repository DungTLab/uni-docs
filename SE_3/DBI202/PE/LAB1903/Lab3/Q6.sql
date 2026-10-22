SELECT TOP 10 p.Id AS 'Productid', p.ProductName, SUM(oi.UnitPrice * oi.Quantity) AS 'Revenue'
FROM Product p
JOIN OrderItem oi ON p.Id = oi.ProductId
JOIN [Order] o ON oi.OrderId = o.Id AND YEAR(o.OrderDate) = 2013
GROUP BY p.Id, p.ProductName
ORDER BY Revenue DESC;
