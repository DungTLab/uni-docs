CREATE TABLE tblAirport (
	Airportcode NCHAR(10),
	[NAME] NVARCHAR(20),
	City NVARCHAR(50),
	[State] NVARCHAR(50),

	PRIMARY KEY (Airportcode)
)

CREATE TABLE tblAirPlance (
	AirplanceID NCHAR(10),
	AriplanceName NVARCHAR(20),
	TotalSeat INT,
	Company NVARCHAR(50),

	PRIMARY KEY (AirplanID)
)

CREATE TABLE CanLand (
	TimeLand DATETIME,
	Airportcode NCHAR(10),
	AirPlanceID NCHAR(10),

	FOREIGN KEY (Airportcode) REFERENCES tblAirport(Airportcode),
	FOREIGN KEY (AirPlanceID) REFERENCES tblAirPlance(AirPlanceID),
	PRIMARY KEY (Airportcode, AirPlanceID)
)