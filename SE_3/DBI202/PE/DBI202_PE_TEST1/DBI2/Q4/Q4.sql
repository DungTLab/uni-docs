SELECT C.custName, Ca.model, ST.serviceTicketID, ST.dateReturned
FROM ServiceTicket ST
JOIN Customer C ON ST.custID = C.custID
JOIN Cars Ca ON ST.carID = Ca.carID
WHERE ST.dateReturned BETWEEN '2021-01-01' AND '2021-03-31';
