CREATE PROCEDURE List_product
    @input INT
AS
BEGIN
    SELECT p.ProductName, oi.Quantity, oi.UnitPrice, (oi.Quantity * oi.UnitPrice) AS Price
    FROM Customer c
    JOIN [Order] o
    ON c.Id = o.CustomerId
    JOIN OrderItem Oi
    ON o.Id = Oi.OrderId
    JOIN [Product] p
    ON Oi.ProductId = p.Id
    WHERE c.Id = @input
    ORDER BY p.ProductName
END;