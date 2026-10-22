CREATE FUNCTION func1(@dept_id INT) 
	RETURNS INT
	
	AS
	BEGIN
	DECLARE @numberOfEmployees INT;

	SELECT @numberOfEmployees = COUNT(*)
	FROM employees
	WHERE department_id = @dept_id;

	RETURN @numberOfEmployees;
	END;