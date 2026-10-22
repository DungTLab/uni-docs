SELECT 
    s.StudentID, 
    s.StudentFirstName, 
    s.StudentLastName, 
    sj.SubjectName, 
    MAX(r.Score) AS FinalGrade
FROM 
    Students s
JOIN 
    Results r ON s.StudentID = r.StudentID
JOIN 
    Subjects sj ON r.SubjectID = sj.SubjectID
GROUP BY 
    s.StudentID, 
    s.StudentFirstName, 
    s.StudentLastName, 
    sj.SubjectName
ORDER BY 
    s.StudentID ASC;