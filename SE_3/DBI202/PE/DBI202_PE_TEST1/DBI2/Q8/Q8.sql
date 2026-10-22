CREATE PROCEDURE proc_salesPerson_invoice
    @salesID DECIMAL(18,0),
    @numberOfInvoices INT OUTPUT
AS
BEGIN
    SELECT @numberOfInvoices = COUNT(DISTINCT invoiceID)
    FROM SalesInvoice
    WHERE salesID = @salesID;
END;