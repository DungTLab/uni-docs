CREATE TRIGGER tr_insert_car
ON Cars
AFTER INSERT
AS
BEGIN
    SELECT carID, serialNumber, model, colour, year
    FROM Cars
    WHERE year = (SELECT year FROM inserted)
      AND model = (SELECT model FROM inserted);
END;