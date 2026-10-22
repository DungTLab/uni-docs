-- 1. Create Database (Change name to match your exam paper)
CREATE DATABASE PRJ301_PE_Template;
GO
USE PRJ301_PE_Template;
GO

-- 2. Drop tables in correct order if they exist
IF OBJECT_ID('dbo.Product', 'U') IS NOT NULL DROP TABLE dbo.Product;
IF OBJECT_ID('dbo.Category', 'U') IS NOT NULL DROP TABLE dbo.Category;
IF OBJECT_ID('dbo.users', 'U') IS NOT NULL DROP TABLE dbo.users;
GO

-- 3. Create users table (matches your WebPRJ users table name)
CREATE TABLE users (
    id INT IDENTITY(1,1) PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(256) NOT NULL, -- Stores MD5 password hashes
    displayName NVARCHAR(100) NOT NULL,
    role VARCHAR(20) NOT NULL DEFAULT 'USER', -- ADMIN or USER
    status BIT NOT NULL DEFAULT 1 -- 1: Active, 0: Blocked
);
GO

-- 4. Create Category table (1-side)
CREATE TABLE Category (
    id INT IDENTITY(1,1) PRIMARY KEY,
    name NVARCHAR(100) NOT NULL UNIQUE
);
GO

-- 5. Create Product table (n-side)
CREATE TABLE Product (
    id INT IDENTITY(1,1) PRIMARY KEY,
    name NVARCHAR(150) NOT NULL,
    price DECIMAL(18,2) NOT NULL,
    quantity INT NOT NULL,
    description NVARCHAR(MAX),
    image VARCHAR(255),
    categoryId INT,
    status BIT NOT NULL DEFAULT 1, -- 1: Available, 0: Out of Stock
    createdDate DATE NOT NULL DEFAULT GETDATE(),
    CONSTRAINT FK_Product_Category FOREIGN KEY (categoryId) REFERENCES Category(id) ON DELETE SET NULL
);
GO

-- 6. Insert Mock Data

-- Accounts (Passwords below are pre-hashed for 'admin123' and 'user123' using MD5)
-- 'admin123' -> 0192023a7bbd73250516f069df18b500
-- 'user123'  -> 6ad14ba9986e66167c111e685b4934bc
INSERT INTO users (username, password, displayName, role, status) VALUES 
('admin', '0192023a7bbd73250516f069df18b500', N'Administrator', 'ADMIN', 1),
('user', '6ad14ba9986e66167c111e685b4934bc', N'Thành Viên Thử Nghiệm', 'USER', 1);
GO

-- Categories
INSERT INTO Category (name) VALUES 
(N'Điện Thoại & Máy Tính Bảng'),
(N'Laptop & Máy Tính Bàn'),
(N'Phụ Kiện Công Nghệ'),
(N'Thiết Bị Âm Thanh');
GO

-- Products
INSERT INTO Product (name, price, quantity, description, image, categoryId, status, createdDate) VALUES 
(N'iPhone 15 Pro Max 256GB', 29990000.00, 15, N'Điện thoại flagship mới nhất của Apple với khung viền Titanium.', 'https://images.unsplash.com/photo-1695048133142-1a20484d2569?w=500', 1, 1, '2026-01-10'),
(N'Samsung Galaxy S24 Ultra', 26990000.00, 10, N'Flagship từ Samsung nổi bật với tính năng Galaxy AI thông minh.', 'https://images.unsplash.com/photo-1610945265064-0e34e5519bbf?w=500', 1, 1, '2026-02-15'),
(N'MacBook Pro 14 M3', 39990000.00, 8, N'Laptop đồ họa chuyên nghiệp của Apple, chip M3 mạnh mẽ.', 'https://images.unsplash.com/photo-1517336714731-489689fd1ca8?w=500', 2, 1, '2026-03-01'),
(N'Dell XPS 13 Plus', 34500000.00, 5, N'Laptop Windows siêu mỏng nhẹ thời trang, màn hình OLED sắc nét.', 'https://images.unsplash.com/photo-1593642632823-8f785ba67e45?w=500', 2, 1, '2026-03-12'),
(N'Bàn Phím Cơ Keychron K2 V2', 1850000.00, 50, N'Bàn phím cơ không dây Bluetooth bán chạy nhất cho lập trình viên.', 'https://images.unsplash.com/photo-1618384887929-16ec33fab9ef?w=500', 3, 1, '2026-04-05'),
(N'Chuột Logitech MX Master 3S', 2490000.00, 30, N'Chuột công thái học cao cấp hỗ trợ cuộn siêu nhanh và êm ái.', 'https://images.unsplash.com/photo-1615663245857-ac93bb7c39e7?w=500', 3, 1, '2026-04-20'),
(N'Tai Nghe Sony WH-1000XM5', 6890000.00, 12, N'Tai nghe chụp tai chống ồn chủ động đỉnh cao, âm thanh trung thực.', 'https://images.unsplash.com/photo-1505740420928-5e560c06d30e?w=500', 4, 1, '2026-05-02'),
(N'Loa Bluetooth Marshall Acton III', 7200000.00, 0, N'Loa decor mang phong cách cổ điển âm thanh mạnh mẽ sống động.', 'https://images.unsplash.com/photo-1545454675-3531b543be5d?w=500', 4, 0, '2026-05-15');
GO
