CREATE DATABASE Q1;
GO
USE Q1;
GO

-- 1. Program (Strong Entity)
CREATE TABLE Program (
    Name VARCHAR(50) NOT NULL,
    program_id VARCHAR(10) NOT NULL PRIMARY KEY,
    CreditPoints INT NOT NULL,
    YearCommenced INT NOT NULL
);

-- 2. Student (1:N with Program)
CREATE TABLE Student (
    GivenNames VARCHAR(30) NOT NULL,
    Surname VARCHAR(30) NOT NULL,
    Student_ID VARCHAR(10) NOT NULL PRIMARY KEY,
    Date_of_Birth DATE NOT NULL,
    YearEnrolled INT NOT NULL,
    program_id VARCHAR(10) NOT NULL,
    
    FOREIGN KEY (program_id) REFERENCES Program(program_id)
);

-- 3. Course (Weak Entity)
CREATE TABLE Course (
    Name VARCHAR(50) NOT NULL,
    course_id VARCHAR(10) NOT NULL,
    CreditPoints DECIMAL(5,2) NOT NULL,
    YearCommenced INT NOT NULL,
    
    program_id VARCHAR(10) NOT NULL,
    StudyYear INT NOT NULL,
    Semester INT NOT NULL,
    
    PRIMARY KEY (program_id, course_id),
    
    FOREIGN KEY (program_id) REFERENCES Program(program_id)
);

-- 4. Attempts (M:N relationship)
CREATE TABLE Attempts (
    Student_ID VARCHAR(10) NOT NULL,
    program_id VARCHAR(10) NOT NULL,
    course_id VARCHAR(10) NOT NULL,
    
    AttemptYear INT NOT NULL,
    Semester INT NOT NULL,
    Mark DECIMAL(5,2) NOT NULL,
    Grade VARCHAR(5) NOT NULL,

    PRIMARY KEY (Student_ID, program_id, course_id, AttemptYear, Semester),
    
    FOREIGN KEY (Student_ID) REFERENCES Student(Student_ID),
    FOREIGN KEY (program_id, course_id) 
        REFERENCES Course(program_id, course_id)
);
