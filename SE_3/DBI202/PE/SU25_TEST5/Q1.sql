CREATE TABLE Employee (
	jobTitle TEXT,
	empCode VARCHAR(20),
	FName NVARCHAR(30),
	LName NVARCHAR(30),

	PRIMARY KEY (empCode)
)

CREATE TABLE Project (
	title text,
	projectCode VARCHAR(30),

	PRIMARY KEY (projectCode)
)

CREATE TABLE [type] (
	[type] NVARCHAR(50),
	projectCode VARCHAR(30),

	PRIMARY KEY (projectCode),
	FOREIGN KEY (projectCode) REFERENCES Project(projectCode)
)

CREATE TABLE Tasks (
	empCode VARCHAR(20),
	taskNumber INT,
	projectCode VARCHAR(30),
	description NVARCHAR(100),

	PRIMARY KEY (taskNumber, projectCode),
	FOREIGN KEY (empCode) REFERENCES Employee(empCode),
	FOREIGN KEY (projectCode) REFERENCES Project(projectCode)
)

