DROP DATABASE IF EXISTS university;
CREATE DATABASE university;
use university;

CREATE TABLE IF NOT EXISTS Role
(
    Role_Id  INT PRIMARY KEY AUTO_INCREMENT,
    RoleName VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS Speciality
(
    Speciality_Id   INT PRIMARY KEY AUTO_INCREMENT,
    Speciality_Name VARCHAR(50) UNIQUE NOT NULL,
    Students_Number SMALLINT
);
CREATE TABLE IF NOT EXISTS Course
(
    Course_Id   INT PRIMARY KEY AUTO_INCREMENT,
    Course_Name VARCHAR(50)


);

CREATE TABLE IF NOT EXISTS Speciality_Course
(
    Course_Id     INT,
    Speciality_Id INT,
    FOREIGN KEY (Speciality_Id) references Speciality(speciality_id),
    FOREIGN KEY (Course_Id) references Course(Course_Id)
);

CREATE TABLE IF NOT EXISTS Users
(
    User_Id     INT PRIMARY KEY AUTO_INCREMENT,
    Email       VARCHAR(50)   NOT NULL,
    Password    VARCHAR(50)   NOT NULL,
    First_Name  VARCHAR(1478) NOT NULL,
    Second_Name VARCHAR(700)  NOT NULL,
    Role_Id     INT,
    FOREIGN KEY (Role_Id) REFERENCES Role (Role_Id)

);

CREATE TABLE IF NOT EXISTS Exam_Result
(
    Exam_Result_Id INT PRIMARY KEY AUTO_INCREMENT,
    Date           DATE NOT NULL,
    Grade          SMALLINT  NOT NULL,
    Course_Id      INT       NOT NULL,
    User_Id        INT       NOT NULL,
    FOREIGN KEY (Course_Id) REFERENCES Course (Course_Id),
    FOREIGN KEY (User_Id) REFERENCES Users (User_Id)
);


CREATE TABLE IF NOT EXISTS Result_For_Speciality
(
    Result_For_Speciality_ID INT PRIMARY KEY AUTO_INCREMENT,
    Final_Grade              SMALLINT NOT NULL,
    Speciality_Id            INT      NOT NULL,
    User_Id                  INT      NOT NULL,
    FOREIGN KEY (Speciality_Id) REFERENCES Speciality (Speciality_Id),
    FOREIGN KEY (User_Id) REFERENCES Users (User_Id)
);

# TODO ==============================INSERT===============

INSERT INTO Speciality(speciality_name, students_number)
VALUES ('Computer Since', 30),
       ('Computer engineering', 25),
       ('Human Resources', 25);

INSERT INTO course(Course_Name)
VALUES ('Math'),
       ('Computer Science'),
       ('English'),
       ('Logic'),
       ('Physics');

INSERT INTO Speciality_Course(course_id, speciality_id)
VALUES (1, 1),
       (2, 1),
       (4, 1),
       (2, 2),
       (3, 2),
       (5, 2),
       (4, 3),
       (1, 3),
       (3, 3);




