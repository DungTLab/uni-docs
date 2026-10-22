WITH AVG2024 AS(
    SELECT 
        c.CustomerID,
        c.FullName,
        AVG(CASE 
            WHEN o.Status = 'Completed'
             AND YEAR(o.OrderTime) = 2024
            THEN o.TotalAmount
        END) AS AvgAmount2024,
        AVG(CASE 
            WHEN o.Status = 'Completed'
             AND YEAR(o.OrderTime) = 2025
            THEN o.TotalAmount
        END) AS AvgAmount2025
    FROM Customers c
    LEFT JOIN Orders o ON o.CustomerID = c.CustomerID
    GROUP BY c.CustomerID,c.FullName)
    SELECT 
        CustomerID, 
        FullName,
         CAST(AvgAmount2024 AS DECIMAL(18,2)) AS AvgTotalOrderValue2024,
         CAST(AvgAmount2025 AS DECIMAL(18,2)) AS AvgTotalOrderValue2025,
        CONCAT(
          CAST(((AvgAmount2025-AvgAmount2024)/AvgAmount2024)*100 AS DECIMAL(10,2)),
         '%')
    AS PercentageChange
    
    FROM AVG2024
    WHERE AvgAmount2024 IS NOT NULL
    AND AvgAmount2025 IS NOT NULL