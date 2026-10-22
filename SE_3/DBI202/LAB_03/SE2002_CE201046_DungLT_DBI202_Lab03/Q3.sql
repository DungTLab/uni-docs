CREATE DATABASE AirlineDB;
GO
USE AirlineDB;
GO

-- 1. Airplane
CREATE TABLE Airplane (
    RegistrationNumber VARCHAR(20) PRIMARY KEY,
    ModelNumber VARCHAR(50) NOT NULL,
    Capacity INT NOT NULL
);

-- 2. Flight
CREATE TABLE Flight (
    FlightNumber VARCHAR(20) PRIMARY KEY,
    [From] VARCHAR(50) NOT NULL,
    [To] VARCHAR(50) NOT NULL,
    DepartureDate DATE NOT NULL,
    DepartureTime TIME NOT NULL,
    ArrivalDate DATE NOT NULL,
    ArrivalTime TIME NOT NULL,
    
    RegistrationNumber VARCHAR(20) NOT NULL,
    
    FOREIGN KEY (RegistrationNumber)
        REFERENCES Airplane(RegistrationNumber)
);

-- 3. Passenger
CREATE TABLE Passenger (
    EmailAddress VARCHAR(100) PRIMARY KEY,
    GivenNames VARCHAR(50) NOT NULL,
    Surname VARCHAR(50) NOT NULL
);

-- 4. Reservation (Associative Entity)
CREATE TABLE Reservation (
    BookNo VARCHAR(20) PRIMARY KEY,
    Class VARCHAR(20) NOT NULL,
    SeatNo VARCHAR(10) NOT NULL,
    Price DECIMAL(10,2) NOT NULL,
    BookTime TIME NOT NULL,
    BookDate DATE NOT NULL,
    
    EmailAddress VARCHAR(100) NOT NULL,
    FlightNumber VARCHAR(20) NOT NULL,
    
    FOREIGN KEY (EmailAddress)
        REFERENCES Passenger(EmailAddress),
        
    FOREIGN KEY (FlightNumber)
        REFERENCES Flight(FlightNumber)
);