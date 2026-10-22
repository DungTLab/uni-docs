SELECT s.StudentID, s.StudentLastName,  s.StudentFirstName
FROM Students s
WHERE s.StudentScholarship = (SELECT MAX(s2.StudentScholarship)
								FROM Students s2
								JOIN Departments d ON s2.DepartmentID = d.DepartmentID
								WHERE d.DepartmentName = 'Mathematics');
