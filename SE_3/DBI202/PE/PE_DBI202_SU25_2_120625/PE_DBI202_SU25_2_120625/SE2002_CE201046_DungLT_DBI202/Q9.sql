CREATE PROCEDURE proc_insert
    @employee_name NVARCHAR(50),
    @salary FLOAT,
    @supervisor_id INT
AS
BEGIN
    DECLARE @count INT;

    SELECT @count = COUNT(*)
    FROM Employee
    WHERE supervisor_id = @supervisor_id;
    
    IF (@count >= 2)
    BEGIN PRINT 'no insert';
    END
    ELSE
    BEGIN
        INSERT INTO Employee(employee_name, salary, supervisor_id)
        VALUES (@employee_name, @salary, @supervisor_id);
    END
END;