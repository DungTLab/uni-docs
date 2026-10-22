SELECT p.ProgramName, f.FacultyName, u.UniversityName
FROM Program p
JOIN Faculty f
ON p.FacultyID = f.FacultyID
JOIN University u
ON f.UniversityID = u.UniversityID
JOIN Country c
ON u.CountryCode = c.CountryCode
WHERE c.CountryName = 'United States'

