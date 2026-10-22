SELECT u.UniversityName, r.RankingID, r.RankingYear,
    COUNT(rd.CriterionID) AS NumberOfCriterion, 
    AVG(rd.Score) AS AverageScore
FROM University u
INNER JOIN Country c
    ON u.CountryCode = c.CountryCode
LEFT JOIN Ranking r
    ON u.UniversityID = r.UniversityID AND r.RankingYear = 2024
LEFT JOIN RankingDetail rd
    ON r.RankingID = rd.RankingID
WHERE c.CountryName IN ('Vietnam', 'United Kingdom', 'United States')
GROUP BY 
    u.UniversityName, 
    r.RankingID, 
    r.RankingYear
ORDER BY 
    u.UniversityName ASC, 
    r.RankingID ASC;