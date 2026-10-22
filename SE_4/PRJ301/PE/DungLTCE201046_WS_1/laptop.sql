USE master;
GO

-- Create database if not exists
IF NOT EXISTS (SELECT * FROM sys.databases WHERE name = 'PRJ301_Laptop')
BEGIN
    CREATE DATABASE PRJ301_Laptop;
END
GO

USE PRJ301_Laptop;
GO

-- Drop table if exists
IF OBJECT_ID('Laptop', 'U') IS NOT NULL
BEGIN
    DROP TABLE Laptop;
END
GO

-- Create table Laptop
CREATE TABLE Laptop (
    id INT IDENTITY(1,1) PRIMARY KEY,
    brand NVARCHAR(255) NOT NULL,
    model NVARCHAR(255) NOT NULL,
    release_year INT NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    quantity INT NOT NULL
);
GO

-- Insert initial data matching the workshop specifications exactly
SET IDENTITY_INSERT Laptop ON;

INSERT INTO Laptop (id, brand, model, release_year, price, quantity) VALUES
(1, 'Dell', 'XPS 15', 2024, 1599.00, 3),
(2, 'Apple', 'MacBook Pro 14', 2024, 1999.00, 10),
(3, 'Lenovo', 'ThinkPad X1 Carbon', 2024, 1499.00, 2),
(4, 'ASUS', 'ROG Zephyrus G14', 2024, 1799.00, 7);

SET IDENTITY_INSERT Laptop OFF;
GO
