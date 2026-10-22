CREATE TABLE Hotels (
	hotelID INT,
	[name] NVARCHAR(100),
	contact NVARCHAR(100),
	[address] NVARCHAR(100),
	street NVARCHAR(50),
	city NVARCHAR(50),

	PRIMARY KEY (hotelID)
)

CREATE TABLE Rooms (
	roomNumber INT,
	[floor] INT,
	building NVARCHAR(200),
	hotelID INT,

	PRIMARY KEY (roomNumber, hotelID),
	FOREIGN KEY (hotelID) REFERENCES Hotels(hotelID)
)

CREATE TABLE Guests (
	questID INT,
	fullname NVARCHAR(50),
	phone VARCHAR(15),
	email VARCHAR(50),

	PRIMARY KEY (questID)
)

CREATE TABLE books (
	bookingID INT,
	roomNumber INT,
	questID INT,
	hotelID INT,

	PRIMARY KEY (bookingID, roomNumber, questID, hotelID),
	FOREIGN KEY (roomNumber,hotelID) REFERENCES Rooms(roomNumber, hotelID),
	FOREIGN KEY (questID) REFERENCES Guests(questID)

)