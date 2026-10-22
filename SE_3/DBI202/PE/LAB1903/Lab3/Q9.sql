CREATE TRIGGER Auto_fill_fax
ON Supplier
AFTER INSERT, UPDATE
AS
BEGIN
	UPDATE Supplier
	SET Fax = i.Phone
	FROM Supplier s
	JOIN inserted i ON s.Id = i.Id
	WHERE i.Fax  IS NULL
END