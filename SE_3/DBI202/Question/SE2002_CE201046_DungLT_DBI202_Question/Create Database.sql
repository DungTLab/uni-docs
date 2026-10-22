CREATE DATABASE QLSV;
GO

USE QLSV;
GO


CREATE TABLE LOP (
    Malop VARCHAR(10) PRIMARY KEY,
    Tenlop NVARCHAR(50),
    Sosv INT
);
GO


CREATE TABLE SINHVIEN (
    Masv VARCHAR(10) PRIMARY KEY,
    Malop VARCHAR(10),
    Hodem NVARCHAR(50),
    Ten NVARCHAR(50),
    Ngaysinh DATE,
    Diachi NVARCHAR(100),
    
    FOREIGN KEY (Malop) REFERENCES LOP(Malop)
);
GO


INSERT INTO LOP (Malop, Tenlop, Sosv) VALUES
('L01', N'Công nghệ thông tin 1', 35),
('L02', N'Khoa học máy tính 1', 25);
GO



INSERT INTO SINHVIEN (Masv, Malop, Hodem, Ten, Ngaysinh, Diachi) VALUES
('SV01', 'L01', N'Nguyễn Văn', N'Hải', '1996-05-15', N'Ninh Kiều, Cần Thơ'),
('SV02', 'L02', N'Trần Thị', N'Bình', '1997-10-20', N'Hà Nội');
GO