SELECT TOP 1 l.Name
FROM Locations l 
JOIN [Events] e ON l.locationID = e.locationID
GROUP BY l.name
ORDER BY COUNT(e.eventID) DESC;