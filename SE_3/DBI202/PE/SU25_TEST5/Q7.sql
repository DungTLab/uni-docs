WITH TopGlobal AS (
	SELECT ra.AgencyID, u.UniversityName, r.Score, ROW_NUMBER() OVER(PARTITION BY ra.AgencyID ORDER BY r.Score DESC) AS RankNum
	FROM RankingAgency ra
	JOIN Ranking r
	ON ra.AgencyID = r.AgencyID AND r.RankingYear = 2024
	JOIN University u
	ON r.UniversityID = u.UniversityID
),	
TopVietnam AS (
	SELECT ra.AgencyID, u.UniversityName, r.Score, ROW_NUMBER() OVER(PARTITION BY ra.AgencyID ORDER BY r.Score DESC) AS RankNum
	FROM RankingAgency ra
	JOIN Ranking r
	ON ra.AgencyID = r.AgencyID AND r.RankingYear = 2024
	JOIN University u
	ON r.UniversityID = u.UniversityID
	WHERE u.CountryCode = 'VN'
)

SELECT 
    ra.AgencyID, 
    ra.AgencyName, 
    tg.UniversityName AS FirstGlobalUniversity, 
    tg.Score AS GlobalHighestScore,
    tv.UniversityName AS FirstVietnameseUniversity,
    tv.Score AS VietnameseHighestScore
FROM RankingAgency ra
LEFT JOIN (SELECT * FROM TopGlobal WHERE RankNum = 1) tg 
    ON ra.AgencyID = tg.AgencyID
LEFT JOIN (SELECT * FROM TopVietnam WHERE RankNum = 1) tv 
    ON ra.AgencyID = tv.AgencyID
ORDER BY ra.AgencyName ASC;
