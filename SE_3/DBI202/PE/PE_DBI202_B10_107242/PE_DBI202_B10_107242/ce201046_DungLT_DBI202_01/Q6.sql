SELECT e.*
FROM [Events] e 
JOIN workFor w ON e.eventID = w.staffID
GROUP BY e.eventID, e.name, e.StartTime, e.EndTime, e.locationID
HAVING COUNT(w.staffID) >=1

