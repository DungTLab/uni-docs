CREATE FUNCTION f_tk(@mechanicID DECIMAL(18,0))
RETURNS TABLE
AS
RETURN
(
    SELECT M.mechanicID,
           M.mechanicName,
           SUM(SM.hours) AS sumHours
    FROM Mechanic M
    JOIN ServiceMehanic SM ON M.mechanicID = SM.mechanicID
    WHERE M.mechanicID = @mechanicID
    GROUP BY M.mechanicID, M.mechanicName
);