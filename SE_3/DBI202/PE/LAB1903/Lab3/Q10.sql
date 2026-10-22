DELETE FROM Customer
WHERE Id NOT IN (SELECT o.CustomerId
				FROM [Order] o
				WHERE o.CustomerId IS NOT NULL)