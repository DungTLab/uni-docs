SELECT u.UniversityName, f.FacultyName
FROM University u
JOIN Faculty f
ON u.UniversityID = f.UniversityID
WHERE u.UniversityID IN (
    SELECT TOP 1 WITH TIES r.UniversityID 
    FROM Ranking r
    JOIN University un ON r.UniversityID = un.UniversityID
    JOIN Country co ON un.CountryCode = co.CountryCode
    WHERE co.CountryName = 'Vietnam' 
    GROUP BY r.UniversityID
    ORDER BY COUNT(r.RankingID) DESC
);
