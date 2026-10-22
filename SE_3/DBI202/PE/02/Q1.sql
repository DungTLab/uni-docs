CREATE TABLE Publisher (
	Publisher_ID INT,
	[Name] NVARCHAR(20),
	[Address] NVARCHAR(100),

	PRIMARY KEY (publisher_ID)
)

CREATE TABLE Book (
	Authors NVARCHAR(100),
	Price INT,
	Title NVARCHAR(20),
	Book_ID INT,

	PRIMARY KEY (Book_ID)
)

CREATE TABLE Reader (
	Phone NVARCHAR(10),
	Email NVARCHAR(20),
	Reader_ID INT,
	Reader_Name NVARCHAR(20),

	PRIMARY KEY (Reader_ID)
)

CREATE TABLE Borrowed_by (
	Reserve_Date DATETIME,
	Return_Date DATETIME,
	Note NVARCHAR(50),
	Book_ID INT,
	Reader_ID INT,

	PRIMARY KEY (Book_ID, Reader_ID),
	FOREIGN KEY (Book_ID) REFERENCES Book(Book_ID),
	FOREIGN KEY (Reader_ID) REFERENCES Reader(Reader_ID)
)