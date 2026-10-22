CREATE PROCEDURE Create_an_invoice
    @OrderId INT
AS
BEGIN
    SELECT 
        p.ProductName, 
        oi.Quantity, 
        oi.UnitPrice, 
        (oi.Quantity * oi.UnitPrice) AS 'Price'
    FROM Product p
    JOIN OrderItem oi ON p.Id = oi.ProductId
    WHERE oi.OrderId = @OrderId 
    ORDER BY p.ProductName ASC; 
END