CREATE TRIGGER trig2 
ON employees
AFTER INSERT
AS
BEGIN
	UPDATE employees
	SET email = LOWER(employees.email)
	WHERE employee_id IN (SELECT employee_id FROM inserted)
END;