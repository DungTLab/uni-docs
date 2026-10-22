-- CACH 1
UPDATE Staffs
SET phone = Stuff(Phone, 1, 3, '999')
WHERE phone LIKE '555%';

-- Cach 2
-- UPDATE Staffs
-- SET phone = REPLACE(phone, '555', '999')
-- WHERE phone LIKE '555%';
