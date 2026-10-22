CREATE TABLE Customers (
	CustID INT,
	City VARCHAR(100),
	Cname VARCHAR(100),

	PRIMARY KEY (CustID)
)

CREATE TABLE Orders (
	OrderID INT,
	Odate DATE,
	CustID INT,
	
	PRIMARY KEY (OrderID),
	FOREIGN KEY (CustID) REFERENCES Customers(CustID)
)

CREATE TABLE Items (
	ItemID INT,
	Unit_Price DECIMAL(10),

	PRIMARY KEY (ItemID)
)

CREATE TABLE Order_Item (
	Qty INT,
	OrderID INT,
	ItemID INT,

	FOREIGN KEY (OrderID) REFERENCES Orders(OrderID),
	FOREIGN KEY (ItemID) REFERENCES Items(ItemID),
	PRIMARY KEY (OrderID, ItemID)
)