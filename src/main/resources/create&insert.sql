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
    Students_Number SMALLINT,
    Activity        TEXT,
    Background      TEXT,
    Employments     TEXT


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
    Confirmed                BOOLEAN  NOT NULL,
    FOREIGN KEY (Speciality_Id) REFERENCES Speciality (Speciality_Id),
    FOREIGN KEY (User_Id) REFERENCES Users (User_Id)
);

# TODO ==============================INSERT===============

INSERT INTO Speciality(speciality_name, students_number, activity, background, employments)
VALUES ( 'Computer Since', 30
       , 'The introduction of information and communication technologies (ICT) is considered a priority area for innovative development of Ukraine as a fully fledged information society. Active state support for ICT stimulates the modernization of practically all spheres of the state''s life. At the same time, special attention is paid not only to the creation of infrastructure of national, regional and international telecommunication networks, the introduction of electronic document management and e-management systems, but also to the training of highly qualified ICT specialists, whose insufficient number significantly hinders the spread of information technologies, not only in Ukraine, but only in Ukraine. world. That is why the Faculty of Electronics and Information Technology has started training specialists in the field of information and communication technologies.'
       , 'During the study, students gain fundamental theoretical and practical knowledge of information network design and information and communication systems, as well as skills in developing, debugging, maintaining and administering software and hardware for Internet servers, Internet information portals, web interfaces. The training process is conducted taking into account the specifics of corporate standards, application of modern equipment and involvement of specialists of regional offices of international IT companies, such as NetCracker, PortaOne, Cisco. In addition, students have the opportunity to study a number of specialized disciplines, which will allow them to effectively work in the positions of heads of structural IT departments of enterprises and companies and will provide an opportunity to engage in research and teaching activities in the field of ICT.'
       , 'A computer specialist is able to effectively solve the tasks of designing, setting up and maintaining telecommunication network infrastructure from small, enterprise-wide electronic document management systems to regional and national e-goverment systems. ICT professionals can hold the positions of administrators and analysts of information and communication systems and Internet resources, consultants on information and implementation of information systems, software engineers, researchers and university educators. Potential employers should also include mobile carriers, ISPs, IT companies providing IT marketing and software integration services.'),

       ( 'Electrical  engineering', 25
       , 'Electricity is the basis of the scientific and technological progress of today, as it provides humankind with heat, light and energy, without which any electrical, electronic, computer equipment, equipment of industrial enterprises, transport, etc. is impossible. Professional training of electrical engineers is carried out on the basis of many years of experience in construction and operation of domestic power systems, advanced experience of the world electricity, development of new technologies for electricity generation, transmission and consumption, energy saving requirements, global information management of power consumption, new sources of energy. and increasing attention to the use of energy-saving measures in industry.'
       , 'The specialty is the training of electrical engineers in the calculation, design and operation of electrical equipment. The basis of professional training of power specialists is based on the latest information technologies, special attention is paid to computer-aided design of electrical systems and networks, electrical equipment and machines, study of the creation of modern renewable energy sources, the use of energy efficient technologies. Specialists who have completed this specialty receive the profession of electrical engineer in the field of power supply to industrial enterprises, production and distribution of electricity. They are engaged in the development of design and working documentation of separate units of power systems, the development of computer variants of design and design documentation, the calculation of electrical circuits and the use of computer equipment in the design calculations, the planning of loads and costs of electricity, the management of the electrical equipment of the power plant (substation) control systems for power plants, maintenance of power plants and electrical equipment power stations (substations) of district electric networks.'
       , 'The graduates work in enterprises and organizations of different ownership forms related to the production, transmission, distribution and consumption of electricity, in science institutions, in the positions of chief energy engineer, deputy chief energy engineer in enterprises, specialist in energy management and energy audit, specialist in the use of modern energy-saving technologies, design engineers in design organizations.'),


       ( 'Cyber ​​Security', 25
       , 'A cyber security specialist develops security systems for various communications networks and electronic databases, tests and refines their own and third-party designs to avoid the risk of leakage of information that constitutes state or commercial secret, confidential information. This profession is relatively young and widespread in the implementation of computer and networking technologies in virtually all organizations, from small commercial firms to security agencies'
       , 'Cyber security training takes into account academic and professional requirements for programming, computer science and information and communication technology professionals. Considerable attention is paid to the study and practical application of information security technologies in the development of database and knowledge management systems, network applications and Internet services, data transmission and encryption protocols. In addition, part of the disciplines introduces students to the methods of certification, examination and special studies (including the use of parallel and quantum computing environments) tools and systems of cybernetic protection of information resources. Separate courses are devoted to methods of detecting, blocking threats and obtaining evidence of unauthorized access to information with an appropriate level of secrecy of corporate, banking and government information resources.'
       , 'Cyber security professionals can hold the following positions: System Administration Technician, Software Development and Testing Specialist, Information Technology Specialist, Computer Software Development Specialist, Security Specialist, etc.');


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


INSERT INTO Users(email, password, first_name, second_name, role_id)
VALUES ('user1@gmail.com', 'MTExMTExMTE=', 'Miki', 'Jeyson', 1),
       ('user2@gmail.com', 'MTExMTExMTE=', 'Carter', 'Brown', 1),
       ('user3@gmail.com', 'MTExMTExMTE=', 'Michael', 'Moore', 1),
       ('user4@gmail.com', 'MTExMTExMTE=', 'Luke', 'Williams', 1),
       ('user5@gmail.com', 'MTExMTExMTE=', 'Oliver', 'Thomas', 1),
       ('user6@gmail.com', 'MTExMTExMTE=', 'Henry', 'Thompson', 1),
       ('user7@gmail.com', 'MTExMTExMTE=', 'Matthew', 'Harris', 1),
       ('user8@gmail.com', 'MTExMTExMTE=', 'Avery', 'Wood', 1),
       ('user9@gmail.com', 'MTExMTExMTE=', 'Hannah', 'Lewis', 1),
       ('user10@gmail.com', 'MTExMTExMTE=', 'Chloe', 'Scott', 1),
       ('user11@gmail.com', 'MTExMTExMTE=', 'Emily', 'Cooper', 1),
       ('user12@gmail.com', 'MTExMTExMTE=', 'Aubrey', 'King', 1),
       ('user13@gmail.com', 'MTExMTExMTE=', 'Hannah', 'Green', 1),
       ('user14@gmail.com', 'MTExMTExMTE=', 'Chloe', 'Walker', 1),
       ('user15@gmail.com', 'MTExMTExMTE=', 'Addison', 'Edwards', 1),
       ('user16@gmail.com', 'MTExMTExMTE=', 'Zoey', 'Turner', 1),
       ('user17@gmail.com', 'MTExMTExMTE=', 'Jack', 'Morgan', 1),
       ('user18@gmail.com', 'MTExMTExMTE=', 'Aiden', 'Hill', 1),
       ('user19@gmail.com', 'MTExMTExMTE=', 'Ryan', 'Phillips', 1),
       ('user20@gmail.com', 'MTExMTExMTE=', 'Daniel', 'Anderson', 1),
       ('admin@gmail.com', 'MTExMTExMTE=', 'Max', 'Kruhovykh', 2);


INSERT INTO Result_For_Speciality(final_grade, speciality_id, user_id, confirmed) VALUES
(80,2,1,false),
(98,2,2,false),
(85,2,3,false),
(85,2,4,false),
(85,2,5,false),
(95,2,6,false),
(99,2,7,false),
(55,2,8,false),
(65,2,9,false),
(81,2,10,false),
(83,2,11,false),
(85,2,12,false),
(85,2,13,false),
(75,2,14,false),
(85,2,15,false),
(65,2,16,false),
(87,2,17,false),
(55,2,18,false),
(65,2,19,false),
(85,2,20,false);



