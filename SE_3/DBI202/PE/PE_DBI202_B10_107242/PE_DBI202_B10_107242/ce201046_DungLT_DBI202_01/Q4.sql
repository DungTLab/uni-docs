SELECT e.eventID, COUNT(w.staffID) AS 'NumberStaff'
FROM [Events] e
JOIN workFor w ON e.eventID = w.eventID
GROUP BY e.eventID
HAVING COUNT(w.staffID) >=2
