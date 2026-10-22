SELECT TOP 1 M.mechanicName, SUM(SM.hours) AS sumHours
FROM ServiceMehanic SM
JOIN Mechanic M ON SM.mechanicID = M.mechanicID
JOIN ServiceTicket ST ON SM.serviceTicketID = ST.serviceTicketID
WHERE YEAR(ST.dateReturned) = 2021
GROUP BY M.mechanicName
ORDER BY sumHours DESC;