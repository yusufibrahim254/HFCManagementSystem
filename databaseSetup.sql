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
