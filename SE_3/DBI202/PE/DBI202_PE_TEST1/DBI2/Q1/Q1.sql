-- ==============================
-- Create table: Customers
-- ==============================
CREATE TABLE Customers (
    custID VARCHAR(50) PRIMARY KEY,
    custName NVARCHAR(200),
    custAddress NVARCHAR(200)
);

-- ==============================
-- Create table: Rooms
-- ==============================
CREATE TABLE Rooms (
    roomID VARCHAR(50) PRIMARY KEY,
    roomName NVARCHAR(200),
    roomPrice MONEY
);

-- ==============================
-- Create table: Services
-- ==============================
CREATE TABLE Services (
    serviceID VARCHAR(50) PRIMARY KEY,
    serviceName NVARCHAR(200),
    servicePrice MONEY
);

-- ==============================
-- Create table: Contracts
-- ==============================
CREATE TABLE Contracts (
    contractID VARCHAR(50) PRIMARY KEY,
    custID VARCHAR(50),
    roomID VARCHAR(50),
    serviceID VARCHAR(50),
    quantity INT,
    CONSTRAINT FK_Contracts_Customers FOREIGN KEY (custID)
        REFERENCES Customers(custID),
    CONSTRAINT FK_Contracts_Rooms FOREIGN KEY (roomID)
        REFERENCES Rooms(roomID),
    CONSTRAINT FK_Contracts_Services FOREIGN KEY (serviceID)
        REFERENCES Services(serviceID)
);
