SELECT 
    tbs.TableID, 
    tbs.Capacity, 
    tbs.[Location], 
    res.CustomerID, 
    res.FullName
FROM Tables tbs
-- Dùng LEFT JOIN với một bảng tạm (Subquery) chỉ chứa các đơn đặt của tháng 9/2025
LEFT JOIN (
    SELECT 
        rt.TableID, 
        c.CustomerID, 
        c.FullName
    FROM ReservationTables rt
    JOIN Reservations r ON rt.ReservationID = r.ReservationID
    JOIN Customers c ON r.CustomerID = c.CustomerID
    WHERE MONTH(r.ReservationTime) = 9 AND YEAR(r.ReservationTime) = 2025
) res ON tbs.TableID = res.TableID
WHERE 
    (tbs.[Location] LIKE 'Khu A%' 
    OR tbs.[Location] LIKE 'Khu B%' 
    OR tbs.[Location] LIKE 'Khu C%')
ORDER BY 
    tbs.Capacity DESC,
    tbs.[Location] ASC;