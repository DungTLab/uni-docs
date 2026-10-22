SELECT TOP 1 WITH TIES d.DepartmentName, s.SubjectName, COUNT(s.SubjectID) AS 'numberOfFailExams'
FROM Subjects s
JOIN Departments d ON s.DepartmentID = d.DepartmentID
JOIN Results r ON s.SubjectID = r.SubjectID
WHERE r.Score < 5
GROUP BY d.DepartmentName, s.SubjectName
ORDER BY numberOfFailExams DESC;