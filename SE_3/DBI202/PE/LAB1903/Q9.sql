CREATE TRIGGER Auto_fill_phone
ON Customer
AFTER INSERT, UPDATE
AS
BEGIN
    UPDATE Customer
    SET Phone = '(1) 135-9999'
    FROM Customer c
    JOIN inserted i ON c.Id = i.Id
    WHERE i.Phone IS NULL;
END;