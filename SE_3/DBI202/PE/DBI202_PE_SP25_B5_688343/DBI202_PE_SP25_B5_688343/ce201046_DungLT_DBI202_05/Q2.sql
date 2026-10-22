SELECT mi.MenuItemID, mi.[Name], mi.[Description], mi.Price, mi.Category
FROM MenuItems mi
WHERE mi.Category = 'Main Course' AND mi.Price BETWEEN 25000 AND 50000;