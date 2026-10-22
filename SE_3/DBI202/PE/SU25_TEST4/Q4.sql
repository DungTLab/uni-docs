SELECT e.Lname, e.Fname
FROM EMPLOYEE e
WHERE e.Salary > (SELECT MAX(e.Salary)
					WHERE e.Dno = 5)
