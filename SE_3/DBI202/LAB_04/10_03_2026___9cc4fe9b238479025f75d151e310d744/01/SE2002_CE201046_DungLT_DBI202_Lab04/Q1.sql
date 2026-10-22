CREATE TABLE Shops
(
    Shop_Name NVARCHAR(20),
    Shop_ID INT,
    Phone VARCHAR(10),

    PRIMARY KEY (Shop_ID)
);


CREATE TABLE Products
(
    Manufaturer NVARCHAR,
    Price INT,
    Product_Name NVARCHAR(20),
    Product_ID INT,

    PRIMARY KEY (Product_ID)
);


CREATE TABLE Customers
(
    Phone VARCHAR(10),
    Email VARCHAR(20),
    Customer_ID INT,
    Customer_Name NVARCHAR(20),

    PRIMARY KEY (Customer_ID)
)

CREATE TABLE Buy
(
    Customer_ID INT,
    Product_ID INT,

    PRIMARY KEY (Customer_ID, Product_ID),
    FOREIGN KEY (Customer_ID) REFERENCES Customers(Customer_ID),
    FOREIGN KEY (Product_ID) REFERENCES Products(Product_ID),

    Buy_Date DATETIME,
    Price INT,
    Note NVARCHAR(50)

)
