SELECT e.EmployeeID, e.FullName, e.[Role], e.HireDate, e.Salary
FROM Employees e
WHERE (e.[role] = 'Chef' OR e.[Role] = 'Manager') and Year(e.HireDate) = 2021;
