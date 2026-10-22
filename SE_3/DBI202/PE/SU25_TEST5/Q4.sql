SELECT u.UniversityName, c.CountryName, f.FacultyName, p.ProgramName, p.DegreeType
FROM University u
LEFT JOIN Country c
  ON u.CountryCode = c.CountryCode
LEFT JOIN Faculty f
  ON u.UniversityID = f.UniversityID
LEFT JOIN Program p
  ON f.FacultyID = p.FacultyID
WHERE c.CountryName = 'Vietnam'
ORDER BY u.UniversityName ASC, f.FacultyName DESC, p.ProgramName ASC;