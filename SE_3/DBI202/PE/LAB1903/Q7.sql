SELECT s.Id, s.CompanyName, s.ContactName, s.City, s.Country, s.Phone
FROM Supplier s
WHERE s.Id IN (
    SELECT TOP 1 WITH TIES s.Id
    FROM Supplier s
    JOIN [Product] p ON s.Id = p.SupplierId
    GROUP BY s.Id
    ORDER BY COUNT(DISTINCT(p.Id)) ASC
)
ORDER BY s.Id;