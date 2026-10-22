CREATE PROCEDURE proc1
	@dept_id INT,
	@numberOfEmployees INT OUTPUT
AS
BEGIN
	SELECT @numberOfEmployees = COUNT(*)
	FROM employees e
	WHERE @dept_id = e.department_id
END