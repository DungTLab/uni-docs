WITH Avg2024 AS (
    SELECT 
        c.CustomerID,
        c.FullName,
        ROUND(AVG(CASE
            WHEN o.Status = 'Completed'
            AND YEAR(o.OrderTime) = 2024
            THEN o.TotalAmount * 1.0
        END),2) AS AvgTotalOrderValue2024
    FROM Customers c
    LEFT JOIN Orders o
        ON c.CustomerID = o.CustomerID
    GROUP BY c.CustomerID, c.FullName
),

Avg2025 AS (
    SELECT 
        c.CustomerID,
        c.FullName,
        ROUND(AVG(CASE
            WHEN o.Status = 'Completed'
            AND YEAR(o.OrderTime) = 2025
            THEN o.TotalAmount * 1.0
        END),2) AS AvgTotalOrderValue2025
    FROM Customers c
    LEFT JOIN Orders o
        ON c.CustomerID = o.CustomerID
    GROUP BY c.CustomerID, c.FullName
)

SELECT 
    a.CustomerID,
    a.FullName,
    CAST(a.AvgTotalOrderValue2024 AS DECIMAL(10,2)),
    CAST(b.AvgTotalOrderValue2025 AS DECIMAL(10,2)),

    CONCAT(
        CAST(ROUND(
            (b.AvgTotalOrderValue2025 - a.AvgTotalOrderValue2024)
            / a.AvgTotalOrderValue2024 * 100
        ,2) AS DECIMAL(10,2)),
        ' %'
    ) AS PercentageChange

FROM Avg2024 a
JOIN Avg2025 b
    ON a.CustomerID = b.CustomerID
WHERE a.AvgTotalOrderValue2024 IS NOT NULL
AND b.AvgTotalOrderValue2025 IS NOT NULL
ORDER BY a.CustomerID;