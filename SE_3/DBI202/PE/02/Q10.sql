CREATE TRIGGER trig1 
ON Employees 
AFTER UPDATE
AS
BEGIN
	UPDATE employees
	SET last_name = UPPER(last_name)
	WHERE employee_id IN (SELECT employee_id 
						FROM Inserted)			
END