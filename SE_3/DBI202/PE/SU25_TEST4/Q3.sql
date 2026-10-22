SELECT 
    d.Dname, 
    e.Lname, 
    e.Fname, 
    p.Pname
FROM DEPARTMENT d
JOIN EMPLOYEE e ON d.Dnumber = e.Dno
JOIN WORKS_ON w ON e.Ssn = w.Essn
JOIN PROJECT p ON w.Pno = p.Pnumber
ORDER BY d.Dname,e.Lname;