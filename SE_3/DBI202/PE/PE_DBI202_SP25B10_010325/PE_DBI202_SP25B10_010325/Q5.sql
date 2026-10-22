SELECT d.DepartmentID, d.DepartmentName, COUNT(s.StudentID) AS 'NumberOStudents'
FROM Departments d
JOIN Students s ON s.DepartmentID = d.DepartmentID
GROUP BY d.DepartmentID, d.DepartmentName
