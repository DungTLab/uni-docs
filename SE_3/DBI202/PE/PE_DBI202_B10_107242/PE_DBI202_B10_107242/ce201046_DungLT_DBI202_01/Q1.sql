CREATE TABLE Departments (
	DeptID VARCHAR(20),
	[name] NVARCHAR(200),
	office NVARCHAR(100),

	PRIMARY KEY (DeptID)
)

CREATE TABLE Employees (
	EmpCode VARCHAR(20),
	[Name] NVARCHAR(50),
	BirthDate DATE,
	DeptID VARCHAR(20),

	PRIMARY KEY (EmpCode),
	FOREIGN KEY (DeptID) REFERENCES Departments(DeptID) 
)

CREATE TABLE Dependants (
	Number INT,
	Name NVARCHAR(50),
	BirthDate DATE,
	[Role] NVARCHAR(30),
	EmpCode VARCHAR (20),

	PRIMARY KEY (Number),
	FOREIGN KEY (EmpCode) REFERENCES Employees(EmpCode) 
)