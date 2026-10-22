SELECT l.locationID, COUNT(e.eventID) AS 'TotalEvents'
FROM Locations l
JOIN [Events] e ON l.locationID = e.locationID
GROUP BY l.locationID