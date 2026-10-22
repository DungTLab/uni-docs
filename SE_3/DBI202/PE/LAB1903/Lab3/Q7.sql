SELECT TOP 1 WITH TIES s.Id, s.CompanyName, s.ContactName, s.City, s.Country, s.Phone
FROM Supplier s
JOIN [Product] p ON s.Id = p.SupplierId
GROUP BY s.Id, s.CompanyName, s.ContactName, s.City, s.Country, s.Phone
ORDER BY COUNT(p.Id) DESC;