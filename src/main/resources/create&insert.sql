DROP DATABASE IF EXISTS university;
CREATE DATABASE university;
use university;

CREATE TABLE IF NOT EXISTS Role
(
    Role_Id  INTEGER PRIMARY KEY AUTO_INCREMENT,
    RoleName VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS Speciality
(
    Speciality_Id   INTEGER PRIMARY KEY AUTO_INCREMENT,
    Speciality_Name VARCHAR(50) UNIQUE NOT NULL,
    Students_Number INTEGER,
    Activity        TEXT,
    Background      TEXT,
    Employments     TEXT,
    Exams_Start     DATE,
    Exams_End       DATE


);
CREATE TABLE IF NOT EXISTS Course
(
    Course_Id   INTEGER PRIMARY KEY AUTO_INCREMENT,
    Course_Name  VARCHAR(50) NOT NULL


);

CREATE TABLE IF NOT EXISTS Speciality_Course
(
    Course_Id     INTEGER,
    Speciality_Id INTEGER,
    FOREIGN KEY (Speciality_Id) references Speciality (speciality_id),
    FOREIGN KEY (Course_Id) references Course (Course_Id)
);

CREATE TABLE IF NOT EXISTS Users
(
    User_Id       INTEGER PRIMARY KEY AUTO_INCREMENT,
    Email         VARCHAR(50)   NOT NULL,
    Password      VARCHAR(50)   NOT NULL,
    First_Name    VARCHAR(1478) NOT NULL,
    Second_Name   VARCHAR(700)  NOT NULL,
    Id_Speciality INTEGER,
    Role_Id       INTEGER,
    FOREIGN KEY (Id_Speciality) REFERENCES Speciality (Speciality_Id),
    FOREIGN KEY (Role_Id) REFERENCES Role (Role_Id)

);

CREATE TABLE IF NOT EXISTS Exam_Result
(
    Exam_Result_Id INTEGER PRIMARY KEY AUTO_INCREMENT,
    Date           DATE NOT NULL,
    Grade          INTEGER,
    Id_Course      INTEGER  NOT NULL,
    Id_User        INTEGER  NOT NULL,
    FOREIGN KEY (Id_Course) REFERENCES Course (Course_Id),
    FOREIGN KEY (Id_User) REFERENCES Users (User_Id)
);


CREATE TABLE IF NOT EXISTS Result_For_Speciality
(
    Result_For_Speciality_ID INT PRIMARY KEY AUTO_INCREMENT,
    Final_Grade              SMALLINT NOT NULL,
    Speciality_Id            INTEGER      NOT NULL,
    User_Id                  INTEGER      NOT NULL,
    Confirmed                BOOLEAN  NOT NULL,
    FOREIGN KEY (Speciality_Id) REFERENCES Speciality (Speciality_Id),
    FOREIGN KEY (User_Id) REFERENCES Users (User_Id)
);

# TODO ==============================INSERT===============

INSERT INTO Speciality(speciality_name, students_number, activity, background, employments, exams_start, exams_end)
VALUES ( 'Computer Since', 10
       , 'The introduction of information and communication technologies (ICT) is considered a priority area for innovative development of Ukraine as a fully fledged information society. Active state support for ICT stimulates the modernization of practically all spheres of the state''s life. At the same time, special attention is paid not only to the creation of infrastructure of national, regional and international telecommunication networks, the introduction of electronic document management and e-management systems, but also to the training of highly qualified ICT specialists, whose insufficient number significantly hinders the spread of information technologies, not only in Ukraine, but only in Ukraine. world. That is why the Faculty of Electronics and Information Technology has started training specialists in the field of information and communication technologies.'
       , 'During the study, students gain fundamental theoretical and practical knowledge of information network design and information and communication systems, as well as skills in developing, debugging, maintaining and administering software and hardware for Internet servers, Internet information portals, web interfaces. The training process is conducted taking into account the specifics of corporate standards, application of modern equipment and involvement of specialists of regional offices of international IT companies, such as NetCracker, PortaOne, Cisco. In addition, students have the opportunity to study a number of specialized disciplines, which will allow them to effectively work in the positions of heads of structural IT departments of enterprises and companies and will provide an opportunity to engage in research and teaching activities in the field of ICT.'
       , 'A computer specialist is able to effectively solve the tasks of designing, setting up and maintaining telecommunication network infrastructure from small, enterprise-wide electronic document management systems to regional and national e-goverment systems. ICT professionals can hold the positions of administrators and analysts of information and communication systems and Internet resources, consultants on information and implementation of information systems, software engineers, researchers and university educators. Potential employers should also include mobile carriers, ISPs, IT companies providing IT marketing and software integration services.'
       , '2019-11-23'
       , '2019-11-30'),

       ( 'Electrical  engineering', 8
       , 'Electricity is the basis of the scientific and technological progress of today, as it provides humankind with heat, light and energy, without which any electrical, electronic, computer equipment, equipment of industrial enterprises, transport, etc. is impossible. Professional training of electrical engineers is carried out on the basis of many years of experience in construction and operation of domestic power systems, advanced experience of the world electricity, development of new technologies for electricity generation, transmission and consumption, energy saving requirements, global information management of power consumption, new sources of energy. and increasing attention to the use of energy-saving measures in industry.'
       , 'The specialty is the training of electrical engineers in the calculation, design and operation of electrical equipment. The basis of professional training of power specialists is based on the latest information technologies, special attention is paid to computer-aided design of electrical systems and networks, electrical equipment and machines, study of the creation of modern renewable energy sources, the use of energy efficient technologies. Specialists who have completed this specialty receive the profession of electrical engineer in the field of power supply to industrial enterprises, production and distribution of electricity. They are engaged in the development of design and working documentation of separate units of power systems, the development of computer variants of design and design documentation, the calculation of electrical circuits and the use of computer equipment in the design calculations, the planning of loads and costs of electricity, the management of the electrical equipment of the power plant (substation) control systems for power plants, maintenance of power plants and electrical equipment power stations (substations) of district electric networks.'
       , 'The graduates work in enterprises and organizations of different ownership forms related to the production, transmission, distribution and consumption of electricity, in science institutions, in the positions of chief energy engineer, deputy chief energy engineer in enterprises, specialist in energy management and energy audit, specialist in the use of modern energy-saving technologies, design engineers in design organizations.'
       , '2019-12-01'
       , '2019-12-07'),


       ( 'Cyber ​​Security', 13
       , 'A cyber security specialist develops security systems for various communications networks and electronic databases, tests and refines their own and third-party designs to avoid the risk of leakage of information that constitutes state or commercial secret, confidential information. This profession is relatively young and widespread in the implementation of computer and networking technologies in virtually all organizations, from small commercial firms to security agencies'
       , 'Cyber security training takes into account academic and professional requirements for programming, computer science and information and communication technology professionals. Considerable attention is paid to the study and practical application of information security technologies in the development of database and knowledge management systems, network applications and Internet services, data transmission and encryption protocols. In addition, part of the disciplines introduces students to the methods of certification, examination and special studies (including the use of parallel and quantum computing environments) tools and systems of cybernetic protection of information resources. Separate courses are devoted to methods of detecting, blocking threats and obtaining evidence of unauthorized access to information with an appropriate level of secrecy of corporate, banking and government information resources.'
       , 'Cyber security professionals can hold the following positions: System Administration Technician, Software Development and Testing Specialist, Information Technology Specialist, Computer Software Development Specialist, Security Specialist, etc.'
       , '2019-12-08'
       , '2019-12-15');

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

INSERT INTO Role(RoleName)
VALUES ('USER'),
       ('ADMIN');

INSERT INTO Users(email, password, first_name, second_name, Id_Speciality, role_id)
VALUES ('user0@gmail.com', 'MTExMTExMTE=', 'Edward', 'Wilson', 1, 1),
       ('user1@gmail.com', 'MTExMTExMTE=', 'Eric', 'Jones', 1, 1),
       ('user2@gmail.com', 'MTExMTExMTE=', 'Jesse', 'Taylor', 1, 1),
       ('user3@gmail.com', 'MTExMTExMTE=', 'Aleksey', 'Johnson', 1, 1),
       ('user4@gmail.com', 'MTExMTExMTE=', 'Jack', 'Taylor', 1, 1),
       ('user5@gmail.com', 'MTExMTExMTE=', 'Vitya', 'Wilson', 1, 1),
       ('user6@gmail.com', 'MTExMTExMTE=', 'Jerome', 'Moore', 1, 1),
       ('user7@gmail.com', 'MTExMTExMTE=', 'Andrey', 'Johnson', 1, 1),
       ('user8@gmail.com', 'MTExMTExMTE=', 'Jack', 'Taylor', 1, 1),
       ('user9@gmail.com', 'MTExMTExMTE=', 'Elliot', 'Wilson', 1, 1),
       ('user10@gmail.com', 'MTExMTExMTE=', 'Andrey', 'Smith', 1, 1),
       ('user11@gmail.com', 'MTExMTExMTE=', 'Jack', 'Thomas', 1, 1),
       ('user12@gmail.com', 'MTExMTExMTE=', 'Edward', 'Smith', 1, 1),
       ('user13@gmail.com', 'MTExMTExMTE=', 'Edward', 'Smith', 1, 1),
       ('user14@gmail.com', 'MTExMTExMTE=', 'Arkasha', 'Williams', 1, 1),
       ('user15@gmail.com', 'MTExMTExMTE=', 'Aleksey', 'Johnson', 1, 1),
       ('user16@gmail.com', 'MTExMTExMTE=', 'Elliot', 'Moore', 1, 1),
       ('user17@gmail.com', 'MTExMTExMTE=', 'Jerome', 'Miller', 1, 1),
       ('user18@gmail.com', 'MTExMTExMTE=', 'Edward', 'Thomas', 1, 1),
       ('user19@gmail.com', 'MTExMTExMTE=', 'Eric', 'Jones', 1, 1),
       ('user20@gmail.com', 'MTExMTExMTE=', 'Valera', 'Anderson', 1, 1),
       ('user21@gmail.com', 'MTExMTExMTE=', 'Edward', 'Anderson', 2, 1),
       ('user22@gmail.com', 'MTExMTExMTE=', 'Edward', 'Brown', 2, 1),
       ('user23@gmail.com', 'MTExMTExMTE=', 'Jasper', 'Taylor', 2, 1),
       ('user24@gmail.com', 'MTExMTExMTE=', 'Elliot', 'Brown', 2, 1),
       ('user25@gmail.com', 'MTExMTExMTE=', 'Aleksey', 'Taylor', 2, 1),
       ('user26@gmail.com', 'MTExMTExMTE=', 'Jesse', 'Thomas', 2, 1),
       ('user27@gmail.com', 'MTExMTExMTE=', 'Jerome', 'Johnson', 2, 1),
       ('user28@gmail.com', 'MTExMTExMTE=', 'Edward', 'Johnson', 2, 1),
       ('user29@gmail.com', 'MTExMTExMTE=', 'Aleksey', 'Taylor', 2, 1),
       ('user30@gmail.com', 'MTExMTExMTE=', 'Arkasha', 'Miller', 2, 1),
       ('user31@gmail.com', 'MTExMTExMTE=', 'Edward', 'Smith', 2, 1),
       ('user32@gmail.com', 'MTExMTExMTE=', 'Valera', 'Anderson', 2, 1),
       ('user33@gmail.com', 'MTExMTExMTE=', 'Arkasha', 'Brown', 2, 1),
       ('user34@gmail.com', 'MTExMTExMTE=', 'Vitya', 'Brown', 2, 1),
       ('user35@gmail.com', 'MTExMTExMTE=', 'Edward', 'Anderson', 2, 1),
       ('user36@gmail.com', 'MTExMTExMTE=', 'Andrey', 'Taylor', 2, 1),
       ('user37@gmail.com', 'MTExMTExMTE=', 'Arkasha', 'Smith', 2, 1),
       ('user38@gmail.com', 'MTExMTExMTE=', 'Valera', 'Wilson', 2, 1),
       ('user39@gmail.com', 'MTExMTExMTE=', 'Jesse', 'Smith', 2, 1),
       ('user40@gmail.com', 'MTExMTExMTE=', 'Vitya', 'Davis', 2, 1),
       ('user41@gmail.com', 'MTExMTExMTE=', 'Jack', 'Moore', 2, 1),
       ('user42@gmail.com', 'MTExMTExMTE=', 'Andrey', 'Williams', 2, 1),
       ('user43@gmail.com', 'MTExMTExMTE=', 'Jesse', 'Williams', 3, 1),
       ('user44@gmail.com', 'MTExMTExMTE=', 'Vitya', 'Anderson', 3, 1),
       ('user45@gmail.com', 'MTExMTExMTE=', 'Arkasha', 'Thomas', 3, 1),
       ('user46@gmail.com', 'MTExMTExMTE=', 'Arkasha', 'Wilson', 3, 1),
       ('user47@gmail.com', 'MTExMTExMTE=', 'Andrey', 'Wilson', 3, 1),
       ('user48@gmail.com', 'MTExMTExMTE=', 'Elliot', 'Jones', 3, 1),
       ('user49@gmail.com', 'MTExMTExMTE=', 'Valera', 'Taylor', 3, 1),
       ('user50@gmail.com', 'MTExMTExMTE=', 'Jasper', 'Moore', 3, 1),
       ('user51@gmail.com', 'MTExMTExMTE=', 'Andrey', 'Miller', 3, 1),
       ('user52@gmail.com', 'MTExMTExMTE=', 'Aleksey', 'Johnson', 3, 1),
       ('user53@gmail.com', 'MTExMTExMTE=', 'Jerome', 'Jones', 3, 1),
       ('user54@gmail.com', 'MTExMTExMTE=', 'Jesse', 'Thomas', 3, 1),
       ('user55@gmail.com', 'MTExMTExMTE=', 'Valera', 'Smith', 3, 1),
       ('user56@gmail.com', 'MTExMTExMTE=', 'Aleksey', 'Moore', 3, 1),
       ('user57@gmail.com', 'MTExMTExMTE=', 'Arkasha', 'Wilson', 3, 1),
       ('user58@gmail.com', 'MTExMTExMTE=', 'Eric', 'Thomas', 3, 1),
       ('user59@gmail.com', 'MTExMTExMTE=', 'Jesse', 'Thomas', 3, 1),
       ('user60@gmail.com', 'MTExMTExMTE=', 'Aleksey', 'Anderson', 3, 1),
       ('user61@gmail.com', 'MTExMTExMTE=', 'Elliot', 'Thomas', 3, 1),
       ('user62@gmail.com', 'MTExMTExMTE=', 'Jesse', 'Anderson', 3, 1),
       ('user63@gmail.com', 'MTExMTExMTE=', 'Jasper', 'Miller', 3, 1),
       ('user64@gmail.com', 'MTExMTExMTE=', 'Edward', 'Davis', 3, 1),
       ('admin@gmail.com', 'MTExMTExMTE=', 'Max', 'Kruhovykh', null, 2);

INSERT INTO Exam_Result(date, grade, id_course, id_user)
VALUES('2019-11-25',100,1,1),
      ('2019-11-27',56,2,1),
      ('2019-11-28',90,4,1),
      ('2019-11-25',57,1,2),
      ('2019-11-27',96,2,2),
      ('2019-11-28',64,4,2),
      ('2019-11-25',62,1,3),
      ('2019-11-27',74,2,3),
      ('2019-11-28',57,4,3),
      ('2019-11-25',86,1,4),
      ('2019-11-27',66,2,4),
      ('2019-11-28',76,4,4),
      ('2019-11-25',70,1,5),
      ('2019-11-27',68,2,5),
      ('2019-11-28',79,4,5),
      ('2019-11-25',84,1,6),
      ('2019-11-27',75,2,6),
      ('2019-11-28',51,4,6),
      ('2019-11-25',96,1,7),
      ('2019-11-27',84,2,7),
      ('2019-11-28',91,4,7),
      ('2019-11-25',71,1,8),
      ('2019-11-27',89,2,8),
      ('2019-11-28',69,4,8),
      ('2019-11-25',62,1,9),
      ('2019-11-27',94,2,9),
      ('2019-11-28',55,4,9),
      ('2019-11-25',59,1,10),
      ('2019-11-27',95,2,10),
      ('2019-11-28',73,4,10),
      ('2019-11-25',73,1,11),
      ('2019-11-27',72,2,11),
      ('2019-11-28',72,4,11),
      ('2019-11-25',55,1,12),
      ('2019-11-27',61,2,12),
      ('2019-11-28',97,4,12),
      ('2019-11-25',73,1,13),
      ('2019-11-27',63,2,13),
      ('2019-11-28',73,4,13),
      ('2019-11-25',80,1,14),
      ('2019-11-27',60,2,14),
      ('2019-11-28',88,4,14),
      ('2019-11-25',82,1,15),
      ('2019-11-27',79,2,15),
      ('2019-11-28',87,4,15),
      ('2019-11-25',67,1,16),
      ('2019-11-27',93,2,16),
      ('2019-11-28',64,4,16),
      ('2019-11-25',87,1,17),
      ('2019-11-27',96,2,17),
      ('2019-11-28',57,4,17),
      ('2019-11-25',77,1,18),
      ('2019-11-27',66,2,18),
      ('2019-11-28',96,4,18),
      ('2019-11-25',86,1,19),
      ('2019-11-27',51,2,19),
      ('2019-11-28',58,4,19),
      ('2019-11-25',73,1,20),
      ('2019-11-27',81,2,20),
      ('2019-11-28',68,4,20),
      ('2019-11-25',61,1,21),
      ('2019-11-27',93,2,21),
      ('2019-11-28',51,4,21),
      ('2019-11-25',67,1,22),
      ('2019-11-27',63,2,22),
      ('2019-11-28',69,4,22),
      ('2019-12-2',70,2,23),
      ('2019-12-3',82,3,23),
      ('2019-12-5',79,5,23),
      ('2019-12-2',61,2,24),
      ('2019-12-3',99,3,24),
      ('2019-12-5',51,5,24),
      ('2019-12-2',91,2,25),
      ('2019-12-3',76,3,25),
      ('2019-12-5',94,5,25),
      ('2019-12-2',73,2,26),
      ('2019-12-3',99,3,26),
      ('2019-12-5',70,5,26),
      ('2019-12-2',77,2,27),
      ('2019-12-3',63,3,27),
      ('2019-12-5',93,5,27),
      ('2019-12-2',62,2,28),
      ('2019-12-3',52,3,28),
      ('2019-12-5',93,5,28),
      ('2019-12-2',92,2,29),
      ('2019-12-3',82,3,29),
      ('2019-12-5',85,5,29),
      ('2019-12-2',89,2,30),
      ('2019-12-3',63,3,30),
      ('2019-12-5',79,5,30),
      ('2019-12-2',71,2,31),
      ('2019-12-3',75,3,31),
      ('2019-12-5',82,5,31),
      ('2019-12-2',83,2,32),
      ('2019-12-3',96,3,32),
      ('2019-12-5',94,5,32),
      ('2019-12-2',99,2,33),
      ('2019-12-3',72,3,33),
      ('2019-12-5',82,5,33),
      ('2019-12-2',95,2,34),
      ('2019-12-3',58,3,34),
      ('2019-12-5',63,5,34),
      ('2019-12-2',92,2,35),
      ('2019-12-3',75,3,35),
      ('2019-12-5',100,5,35),
      ('2019-12-2',51,2,36),
      ('2019-12-3',90,3,36),
      ('2019-12-5',79,5,36),
      ('2019-12-2',78,2,37),
      ('2019-12-3',70,3,37),
      ('2019-12-5',73,5,37),
      ('2019-12-2',59,2,38),
      ('2019-12-3',68,3,38),
      ('2019-12-5',82,5,38),
      ('2019-12-2',93,2,39),
      ('2019-12-3',57,3,39),
      ('2019-12-5',75,5,39),
      ('2019-12-2',58,2,40),
      ('2019-12-3',83,3,40),
      ('2019-12-5',59,5,40),
      ('2019-12-2',98,2,41),
      ('2019-12-3',95,3,41),
      ('2019-12-5',83,5,41),
      ('2019-12-2',64,2,42),
      ('2019-12-3',97,3,42),
      ('2019-12-5',65,5,42),
      ('2019-12-2',64,2,43),
      ('2019-12-3',68,3,43),
      ('2019-12-5',54,5,43),
      ('2019-12-2',78,2,44),
      ('2019-12-3',92,3,44),
      ('2019-12-5',78,5,44),
      ('2019-11-10',78,1,45),
      ('2019-11-11',78,3,45),
      ('2019-11-12',81,4,45),
      ('2019-11-10',69,1,46),
      ('2019-11-11',93,3,46),
      ('2019-11-12',59,4,46),
      ('2019-11-10',94,1,47),
      ('2019-11-11',97,3,47),
      ('2019-11-12',96,4,47),
      ('2019-11-10',81,1,48),
      ('2019-11-11',75,3,48),
      ('2019-11-12',88,4,48),
      ('2019-11-10',90,1,49),
      ('2019-11-11',73,3,49),
      ('2019-11-12',69,4,49),
      ('2019-11-10',57,1,50),
      ('2019-11-11',92,3,50),
      ('2019-11-12',95,4,50),
      ('2019-11-10',52,1,51),
      ('2019-11-11',92,3,51),
      ('2019-11-12',64,4,51),
      ('2019-11-10',67,1,52),
      ('2019-11-11',58,3,52),
      ('2019-11-12',73,4,52),
      ('2019-11-10',69,1,53),
      ('2019-11-11',69,3,53),
      ('2019-11-12',69,4,53),
      ('2019-11-10',87,1,54),
      ('2019-11-11',94,3,54),
      ('2019-11-12',93,4,54),
      ('2019-11-10',68,1,55),
      ('2019-11-11',69,3,55),
      ('2019-11-12',92,4,55),
      ('2019-11-10',69,1,56),
      ('2019-11-11',81,3,56),
      ('2019-11-12',56,4,56),
      ('2019-11-10',70,1,57),
      ('2019-11-11',78,3,57),
      ('2019-11-12',98,4,57),
      ('2019-11-10',84,1,58),
      ('2019-11-11',79,3,58),
      ('2019-11-12',88,4,58),
      ('2019-11-10',96,1,59),
      ('2019-11-11',83,3,59),
      ('2019-11-12',70,4,59),
      ('2019-11-10',74,1,60),
      ('2019-11-11',52,3,60),
      ('2019-11-12',72,4,60),
      ('2019-11-10',76,1,61),
      ('2019-11-11',50,3,61),
      ('2019-11-12',77,4,61),
      ('2019-11-10',51,1,62),
      ('2019-11-11',91,3,62),
      ('2019-11-12',66,4,62),
      ('2019-11-10',88,1,63),
      ('2019-11-11',90,3,63),
      ('2019-11-12',56,4,63),
      ('2019-11-10',80,1,64),
      ('2019-11-11',64,3,64),
      ('2019-11-12',54,4,64),
      ('2019-11-10',64,1,65),
      ('2019-11-11',66,3,65),
      ('2019-11-12',98,4,65),
      ('2019-11-10',78,1,66),
      ('2019-11-11',84,3,66),
      ('2019-11-12',68,4,66);

INSERT INTO Result_For_Speciality(final_grade, speciality_id, user_id, confirmed)
VALUES (80, 1, 1, false),
       (98, 1, 2, false),
       (85, 1, 3, false),
       (85, 1, 4, false),
       (85, 1, 5, false),
       (95, 1, 6, false),
       (99, 1, 7, false),
       (55, 1, 8, false),
       (65, 1, 9, false),
       (81, 1, 10, false),
       (83, 1, 11, false),
       (85, 1, 12, false),
       (85, 1, 13, false),
       (75, 1, 14, false),
       (85, 1, 15, false),
       (65, 1, 16, false),
       (87, 1, 17, false),
       (55, 1, 18, false),
       (65, 1, 19, false),
       (85, 1, 20, false);



