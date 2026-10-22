CREATE PROCEDURE proc_serviceTicket_part
    @serviceTicketID INT,
    @numberOfParts INT OUTPUT
AS
BEGIN
    SELECT @numberOfParts = COUNT(DISTINCT partID)
    FROM PartsUsed
    WHERE serviceTicketID = @serviceTicketID;
END;