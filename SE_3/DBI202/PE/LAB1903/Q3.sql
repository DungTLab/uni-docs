SELECT o.Id AS 'OrderID', o.TotalAmount, c.FirstName + ' ' + c.LastName AS 'Fullname', c.Country
FROM Customer c
JOIN [Order] o
ON c.Id = o.CustomerId
WHERE o.TotalAmount BETWEEN 6400 AND 10000
ORDER BY o.TotalAmount ASC;