SELECT c.CustomerID, c.FullName, r.ReservationTime, rt.TableID, tbs.[Location]
FROM Customers c
JOIN Reservations r
ON c.CustomerID = r.CustomerID
JOIN ReservationTables rt
ON r.ReservationID = rt.ReservationID
JOIN [Tables] tbs
ON rt.TableID = tbs.TableID
WHERE YEAR(r.ReservationTime) = 2025 AND tbs.[Location] LIKE 'Khu A%'