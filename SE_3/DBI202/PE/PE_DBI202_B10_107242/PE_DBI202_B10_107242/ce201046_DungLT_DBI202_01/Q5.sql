SELECT e.name, l.Name, s.name
FROM [Events] e
JOIN Locations l ON e.locationID = l.locationID
JOIN workFor w ON e.eventID = w.eventID
JOIN Staffs s ON w.staffID = s.staffID