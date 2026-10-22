CREATE FUNCTION fn_students (@departmentID INT)
RETURNS TABLE
AS
RETURN (
    SELECT * 
    FROM Students s
    WHERE DepartmentID = @departmentID 
      AND s.StudentScholarship > 0
);

