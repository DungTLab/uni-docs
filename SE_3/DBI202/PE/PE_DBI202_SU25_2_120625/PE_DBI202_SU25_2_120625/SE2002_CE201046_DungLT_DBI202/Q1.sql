CREATE TABLE Account (
    Email VARCHAR(50) PRIMARY KEY,
    Password VARCHAR(50),
    Status INT
);

CREATE TABLE Cosplayer (
    Email VARCHAR(50) PRIMARY KEY,
    [description] VARCHAR(50),

    FOREIGN KEY (Email) REFERENCES Account(Email)
);


CREATE TABLE Customer (
    Email VARCHAR(50) PRIMARY KEY,
    createdAt DATETIME,

    FOREIGN KEY (Email) REFERENCES Account(Email)
);

CREATE TABLE Feedback (
    ID INT IDENTITY(1,1) PRIMARY KEY,
    Content VARCHAR(50),
    CustomerEmail VARCHAR(50), 
    
    FOREIGN KEY (CustomerEmail) REFERENCES Customer(Email)
);