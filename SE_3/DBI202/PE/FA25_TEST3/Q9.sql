CREATE TRIGGER trg_UpdateTotalAmount 
ON OrderDetails 
AFTER INSERT, UPDATE, DELETE
AS
BEGIN
    UPDATE o
    SET o.TotalAmount = ISNULL((
        SELECT SUM(od.Quantity * od.Price) 
        FROM OrderDetails od  
        WHERE od.OrderID = o.OrderID
    ), 0)
    FROM Orders o
    WHERE o.OrderID IN (SELECT OrderID FROM inserted)
       OR o.OrderID IN (SELECT OrderID FROM deleted);
END;
GO