CREATE PROCEDURE proc_SumSalary
    @input INT,
    @output INT OUTPUT
AS
BEGIN
    SELECT @output = ISNULL(SUM(e.salary), 0)
    FROM EMPLOYEE e
    WHERE @input = e.Dno
END;

DECLARE @x INT

EXEC proc_SumSalary 1, @x OUTPUT

SELECT @x AS sumOfSalarys
