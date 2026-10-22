CREATE PROCEDURE MonthlySalesSummary
    @year INT 
AS
BEGIN
    WITH AllMonths AS (
        SELECT v.MonthValue
            FROM (VALUES
            (1),(2),(3),(4),(5),(6),
            (7),(8),(9),(10),(11),(12)
            ) v(MonthValue)
    )
    SELECT 
        CONCAT(am.MonthValue, '/', @year) AS [Month], 
        COUNT(DISTINCT o.OrderID) AS 'NumberOfOrders', 
        ISNULL(SUM(o.TotalAmount), 0) AS 'TotalRevenue' 
    FROM AllMonths am
    LEFT JOIN Orders o 
        ON am.MonthValue = MONTH(o.OrderTime) 
        AND YEAR(o.OrderTime) = @year
        AND o.Status = 'Completed' 
    GROUP BY am.MonthValue
    ORDER BY am.MonthValue ASC; 
END;
GO 