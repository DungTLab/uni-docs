SELECT TOP 1
    e.Ssn,
    e.Lname, 
    e.Fname, 
    COUNT(p.Pnumber) AS DoneProjects
FROM EMPLOYEE e
JOIN WORKS_ON w ON e.Ssn = w.Essn 
JOIN PROJECT p ON w.Pno = p.Pnumber 
GROUP BY e.Ssn,e.Lname,e.Fname
ORDER BY COUNT(p.Pnumber) DESC;