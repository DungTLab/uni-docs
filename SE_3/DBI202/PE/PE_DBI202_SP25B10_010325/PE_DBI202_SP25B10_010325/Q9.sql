CREATE PROCEDURE proc_report 
@deptname VARCHAR(50),
@result INT OUTPUT
AS
BEGIN
	SELECT @result = COUNT(s.StudentID)
	FROM Departments d
	JOIN Students s ON d.DepartmentID = s.DepartmentID
	WHERE d.DepartmentName = @deptname
END

