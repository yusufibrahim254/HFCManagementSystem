-- Sample data for Room table
INSERT INTO Room (RoomID, RoomName, Capacity, Description)
VALUES
(101, 'Studio A', 20, 'Spacious studio with mirrors'),
(102, 'Studio B', 15, 'Small studio for private sessions');

-- Sample data for Equipment table
INSERT INTO Equipment (EquipmentID, EquipmentName, Description, NextMaintenanceDate, LastMaintenanceDate, Status)
VALUES
(1, 'Treadmill', 'readmill', '2024-05-15', '2024-04-01', 'Functional'),
(2, 'Dumbbells', '20 lb Dumbbell Set', '2024-06-01', '2024-03-15', 'Functional');

-- Sample data for Booking table
INSERT INTO Booking (BillingID, BillingDate, Amount, Description, RoomID, MemberID)
VALUES
(1, '2024-04-10', 75.50, 'Yoga Class', 101, 1),
(2, '2024-04-11', 75.50, 'Cardio Workout', 102, 2);

--Sample Data for HFCMember, HFCTrainer, HFCAdminStaff provided in databaseSetup.sql File
