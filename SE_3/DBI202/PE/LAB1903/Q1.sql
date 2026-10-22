CREATE TABLE Venue (
	V_Name NVARCHAR(100),
	V_Id INT,
	V_Address NVARCHAR(200),
	V_Contact NVARCHAR(200),
	V_Owner VARCHAR(50),

	PRIMARY KEY (V_Id)
)

CREATE TABLE [Event] (
	E_Title NVARCHAR(100),
	E_Type VARCHAR(50),
	E_Id INT,
	E_DateTime DATETIME,
	V_Id INT,

	FOREIGN KEY (V_Id) REFERENCES Venue(V_Id),
	PRIMARY KEY (E_Id)
)

CREATE TABLE Attendee (
	Att_Id INT,
	Att_Name NVARCHAR(50),
	Att_Contact NVARCHAR(200),
	Att_Status INT,
	Att_Preferences NVARCHAR (100),

	PRIMARY KEY (Att_Id)
)

CREATE TABLE Vendor (
	Ven_Id INT,
	Vend_Name NVARCHAR(50),
	Vend_Contact NVARCHAR(200),
	Vend_Fee DECIMAL(10,3),
	Vend_Details NVARCHAR(200),

	PRIMARY KEY (Ven_Id)
)

CREATE TABLE Services (
	Ven_Id INT,
	E_Id INT,

	FOREIGN KEY (Ven_Id) REFERENCES Vendor(Ven_Id),
	FOREIGN KEY (E_Id) REFERENCES Event(E_Id),
	PRIMARY KEY (E_Id, Ven_Id)
)

CREATE TABLE  Attends (
	Att_Id INT,
	E_Id INT,

	FOREIGN KEY (E_Id) REFERENCES Event(E_Id),
	FOREIGN KEY (Att_Id) REFERENCES Attendee(Att_Id),
	PRIMARY KEY (E_Id, Att_Id)
)