CREATE TABLE Product (
	ProductID INT,
	Price DECIMAL(12,2),
	Productname NVARCHAR(150),

	PRIMARY KEY(ProductID)
)

CREATE TABLE Category (
	CategoryID INT,
	[Name] NVARCHAR(100),
	[DESCRIPTION] NVARCHAR(200),
	
	ProductID INT,

	PRIMARY KEY (CategoryID, ProductID),
	FOREIGN KEY (ProductID) REFERENCES Product(ProductID)
)

CREATE TABLE Supplier (
	SupplierID VARCHAR(30),
	SupplierName NVARCHAR(100),
	[Level] INT,

	PRIMARY KEY (SupplierID)
)

CREATE TABLE hasSupplier(
	ProductID INT,
	SupplierID VARCHAR(30),

	PRIMARY KEY (ProductID, SupplierID),
	FOREIGN KEY (ProductID) REFERENCES Product(ProductID),
	FOREIGN KEY (SupplierID) REFERENCES Supplier(SupplierID)
)

