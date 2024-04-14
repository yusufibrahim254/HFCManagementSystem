CREATE TABLE IF NOT EXISTS HFCTrainer (
    TrainerID       SERIAL PRIMARY KEY,
    FirstName       varchar(20),
    LastName        varchar(20),
    Email           varchar(50),
    userPassword    varchar(50),
    Specialization  varchar(50),
    Certification   varchar(50),
    Phone           varchar(10)
);

CREATE TABLE IF NOT EXISTS HFCMember (
    MemberID        SERIAL PRIMARY KEY,
    FirstName       varchar(20),
    LastName        varchar(20),
    Email           varchar(50),
    userPassword    varchar(50) NOT NULL,
    Address         varchar(100),
    City            varchar(100),
    Province        varchar(100), 
    PostalCode      varchar(6),
    Phone           varchar(10),
    DOB             DATE,
    FitnessGoal     varchar(100),
    WeightGoal      DECIMAL(10,2), 
    TimeGoal        varchar(50),
    JoinDate        DATE,
	TrainerID       INT, 
    FOREIGN KEY (TrainerID) REFERENCES HFCTrainer(TrainerID) 
);

CREATE TABLE IF NOT EXISTS TrainerAvailability (
    AvailabilityID  SERIAL PRIMARY KEY,
    TrainerID       INT,
    StartTime       TIME,
    EndTime         TIME,
    Weekday         VARCHAR(10),
    FOREIGN KEY (TrainerID) REFERENCES HFCTrainer(TrainerID)
);

CREATE TABLE IF NOT EXISTS MemberAvailability (
    AvailabilityID  SERIAL PRIMARY KEY,
    MemberID        INT,
    StartTime       TIME,
    EndTime         TIME,
    Weekday         VARCHAR(10),
    FOREIGN KEY (MemberID) REFERENCES HFCMember(MemberID)
);

CREATE TABLE IF NOT EXISTS ExerciseRoutine (
    RoutineID       SERIAL PRIMARY KEY,
    MemberID        INT,
    ExerciseType    VARCHAR(100),
    Sets            INT,
    Reps            INT,
    FOREIGN KEY (MemberID) REFERENCES HFCMember(MemberID)
);

CREATE TABLE IF NOT EXISTS Achievements (
    AchievementID   SERIAL PRIMARY KEY,
    MemberID        INT,
    Achievement    VARCHAR(100),
    FOREIGN KEY (MemberID) REFERENCES HFCMember(MemberID)
);


CREATE TABLE IF NOT EXISTS HFCAdminStaff (
	StaffID         SERIAL PRIMARY KEY,
    FirstName       varchar(20),
    LastName        varchar(20),
    Email           varchar(50),
    userPassword    varchar(50),
    Phone           varchar(10)
);

CREATE TABLE IF NOT EXISTS Room (
    RoomID          INT PRIMARY KEY,
    RoomName        varchar(20),
    Capacity        INT, 
    Description     varchar(100)
);

CREATE TABLE IF NOT EXISTS Equipment (
    EquipmentID     INT PRIMARY KEY, 
    EquipmentName   varchar(50),
    Description     varchar(100),
    NextMaintenanceDate DATE,
    LastMaintenanceDate DATE, 
    Status          varchar(50)
);

CREATE TABLE IF NOT EXISTS Booking (
    BillingID       INT PRIMARY KEY,
    BillingDate     DATE, 
    Amount          DECIMAL(10,2) DEFAULT 75.50,
    Description     varchar(100),
    RoomID          INT,
    MemberID        INT,
    FOREIGN KEY (RoomID) REFERENCES Room(RoomID),
    FOREIGN KEY (MemberID) REFERENCES HFCMember(MemberID)
);

CREATE TABLE IF NOT EXISTS Sessions (
	SessionID		SERIAL PRIMARY KEY, 
	MemberID		INT, 
	TrainerID		INT, 
	StartTime		TIME, 
	EndTime			TIME, 
	Purpose			varchar(100),
	FOREIGN KEY (MemberID) REFERENCES HFCMember(MemberID),
    FOREIGN KEY (TrainerID) REFERENCES HFCTrainer(TrainerID)
);
-- Example data for HFCMember
INSERT INTO HFCMember (FirstName, LastName, Email, userPassword, Address, City, Province, PostalCode, Phone, DOB, FitnessGoal, WeightGoal, TimeGoal, JoinDate)
VALUES ('John', 'Doe', 'john@example.com', 'password123', '123 Main St', 'Toronto', 'Ontario', 'M1M1M1', '123567890', '1990-01-01', 'Lose weight', 75.5, '30 minutes/day', '2023-01-01'),
       ('Alice', 'Smith', 'alice@example.com', 'password456', '456 Oak St', 'Vancouver', 'British Columbia', 'V6V6V6', '987653210', '1985-05-15', 'Gain muscle', 85.0, '1 hour/day', '2023-02-15'),
       ('Emily', 'Johnson', 'emily@example.com', 'password789', '789 Elm St', 'Montreal', 'Quebec', 'H2H2H2', '555667777', '1992-09-30', 'Improve flexibility', 65.0, '45 minutes/day', '2023-03-20'),
       ('Michael', 'Williams', 'michael@example.com', 'passwordabc', '321 Cedar St', 'Calgary', 'Alberta', 'T3T3T3', '333445555', '1988-07-10', 'Build strength', 90.0, '1.5 hours/day', '2023-04-10'),
       ('Sophia', 'Brown', 'sophia@example.com', 'passworddef', '987 Pine St', 'Ottawa', 'Ontario', 'K1K1K1', '222334444', '1995-12-25', 'Increase endurance', 70.0, '1 hour/day', '2023-05-01'),
       ('James', 'Garcia', 'james@example.com', 'passwordghi', '654 Maple St', 'Edmonton', 'Alberta', 'T5T5T5', '888990000', '1991-04-05', 'Maintain weight', 80.0, '45 minutes/day', '2023-06-15'),
       ('Emma', 'Martinez', 'emma@example.com', 'passwordjkl', '147 Birch St', 'Quebec City', 'Quebec', 'G1G1G1', '111223333', '1994-08-20', 'Cardiovascular health', 75.0, '30 minutes/day', '2023-07-01'),
       ('Oliver', 'Davis', 'oliver@example.com', 'passwordmno', '789 Spruce St', 'Winnipeg', 'Manitoba', 'R2R2R2', '777889999', '1987-11-15', 'Improve flexibility', 70.0, '1 hour/day', '2023-08-10'),
       ('Ava', 'Rodriguez', 'ava@example.com', 'passwordpqr', '963 Oak St', 'Halifax', 'Nova Scotia', 'B3B3B3', '444556666', '1993-06-30', 'Build strength', 85.0, '1.5 hours/day', '2023-09-01'),
       ('William', 'Jones', 'william@example.com', 'passwordstu', '852 Cedar St', 'Victoria', 'British Columbia', 'V8V8V8', '666777888', '1996-02-10', 'Lose weight', 70.0, '45 minutes/day', '2023-10-15');

-- Example data for HFCTrainer
INSERT INTO HFCTrainer (FirstName, LastName, Email, userPassword, Specialization, Certification, Phone)
VALUES ('Michael', 'Johnson', 'michael@example.com', 'trainerpass1', 'Weight loss', 'Personal Trainer Certification', '111222333'),
       ('Jessica', 'Miller', 'jessica@example.com', 'trainerpass2', 'Strength training', 'Fitness Instructor Certification', '444556666'),
       ('Sophia', 'Williams', 'sophia@example.com', 'trainerpass3', 'Flexibility training', 'Yoga Instructor Certification', '778889999'),
       ('William', 'Brown', 'william@example.com', 'trainerpass4', 'Endurance training', 'Athletic Trainer Certification', '888990000'),
       ('Emma', 'Garcia', 'emma@example.com', 'trainerpass5', 'Functional training', 'CrossFit Trainer Certification', '123567890'),
       ('Oliver', 'Davis', 'oliver@example.com', 'trainerpass6', 'Muscle building', 'Bodybuilding Certification', '234568901'),
       ('Ava', 'Martinez', 'ava@example.com', 'trainerpass7', 'Cardio training', 'Aerobics Instructor Certification', '345789012'),
       ('James', 'Rodriguez', 'james@example.com', 'trainerpass8', 'HIIT training', 'High-Intensity Interval Training Certification', '456790123'),
       ('Emily', 'Jones', 'emily@example.com', 'trainerpass9', 'Pilates training', 'Pilates Instructor Certification', '567890234'),
       ('John', 'Smith', 'john@example.com', 'trainerpass10', 'Sport-specific training', 'Sports Trainer Certification', '678902345');

-- Example data for HFCAdminStaff
INSERT INTO HFCAdminStaff (FirstName, LastName, Email, userPassword, Phone)
VALUES ('David', 'Wilson', 'david@example.com', 'adminpass1', '123456789'),
       ('Emily', 'Brown', 'emily@example.com', 'adminpass2', '234567890'),
       ('Sophia', 'Johnson', 'sophia@example.com', 'adminpass3', '345678901'),
       ('William', 'Garcia', 'william@example.com', 'adminpass4', '456789013'),
       ('Emma', 'Davis', 'emma@example.com', 'adminpass5', '567901234'),
       ('Oliver', 'Martinez', 'oliver@example.com', 'adminpass6', '678012345'),
       ('Ava', 'Smith', 'ava@example.com', 'adminpass7', '789012356'),
       ('James', 'Rodriguez', 'james@example.com', 'adminpass8', '801234567'),
       ('Sophia', 'Miller', 'sophia@example.com', 'adminpass9', '901245678'),
       ('William', 'Wilson', 'william@example.com', 'adminpass10', '986543210');