package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HFCAdminStaff {
    private static int staffID;
    private static String firstName;
    private static String lastName;
    private static String email;
    private static int phone;
    public HFCAdminStaff(){
        JFrame staffPage = new JFrame("Admin Staff Page");
        staffPage.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        staffPage.setSize(500, 400);
        staffPage.setLocationRelativeTo(null);

        JFrame logInFrame = new JFrame("Log In");
        logInFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Dispose the frame on close
        logInFrame.setSize(500, 400);
        logInFrame.setLocationRelativeTo(null);
        logInFrame.setVisible(true);
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(5, 1));

        JTextField emailField = new JTextField();
        JTextField passwordField = new JTextField();

        JLabel emailLabel = new JLabel("Email:");
        JLabel passwordLabel = new JLabel("Password:");

        logInFrame.add(mainPanel);

        mainPanel.add(emailLabel);
        mainPanel.add(emailField);

        mainPanel.add(passwordLabel);
        mainPanel.add(passwordField);

        JButton confirmButton = new JButton("Confirm");
        logInFrame.add(confirmButton, BorderLayout.SOUTH);

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText();
                String password = passwordField.getText();

                if (logInStaff(email, password)) {
                    loadStaffDetails(email);
                    logInFrame.dispose();
                    JFrame staffPage = new JFrame("Admin Staff Page");
                    staffPage.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Dispose the frame on close
                    staffPage.setSize(500, 400);
                    staffPage.setLocationRelativeTo(null);
                    JPanel dashboard = new JPanel();
                    dashboard.setLayout(new GridLayout(0, 1));

                    JMenuBar menuBar = new JMenuBar();
                    JMenu mainMenu = new JMenu("Options");
                    JMenuItem profile = new JMenuItem("My Profile");
                    JMenuItem update = new JMenuItem("Update Personal Info");
                    JMenuItem logOut = new JMenuItem("Log Out");
                    JButton addTrainer = new JButton("Add a new Trainer");
                    JButton roomsButton = new JButton("Rooms");
                    JButton equipmentButton = new JButton("Equipment");
                    JButton membersButton = new JButton("Members");

                    mainMenu.add(profile);
                    mainMenu.add(update);
                    mainMenu.add(logOut);

                    dashboard.add(addTrainer);
                    dashboard.add(roomsButton);
                    dashboard.add(equipmentButton);
                    dashboard.add(membersButton);
                    logOut.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {

                            staffPage.dispose();
                        }
                    });

                    update.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            JFrame updateFrame = new JFrame("Update Personal Info");
                            updateFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Dispose the frame on close
                            updateFrame.setSize(500, 700);
                            updateFrame.setLocationRelativeTo(null);
                            JPanel mainPanel = new JPanel();
                            mainPanel.setLayout(new GridLayout(28, 1));

                            JTextField firstNameField = new JTextField();
                            JTextField lastNameField = new JTextField();
                            JTextField emailField = new JTextField();
                            JTextField phoneField = new JTextField();


                            JLabel firstNameLabel = new JLabel("First Name:");
                            JLabel lastNameLabel = new JLabel("Last Name:");
                            JLabel emailLabel = new JLabel("Email:");
                            JLabel phoneLabel = new JLabel("Phone: ");

                            firstNameField.setText(HFCAdminStaff.firstName);
                            lastNameField.setText(HFCAdminStaff.lastName);
                            emailField.setText(HFCAdminStaff.email);
                            phoneField.setText(Integer.toString(HFCAdminStaff.phone));


                            mainPanel.add(firstNameLabel);
                            mainPanel.add(firstNameField);
                            mainPanel.add(lastNameLabel);
                            mainPanel.add(lastNameField);
                            mainPanel.add(emailLabel);
                            mainPanel.add(emailField);
                            mainPanel.add(phoneLabel);
                            mainPanel.add(phoneField);

                            JButton confirmButton = new JButton("Confirm");
                            updateFrame.add(confirmButton, BorderLayout.SOUTH);

                            confirmButton.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    String newFirstName = firstNameField.getText();
                                    String newLastName = lastNameField.getText();
                                    String newEmail = emailField.getText();
                                    int newPhone = Integer.parseInt(phoneField.getText());

                                    boolean success = updateStaffDetails(newFirstName, newLastName, newEmail, newPhone);
                                    if (success) {
                                        JOptionPane.showMessageDialog(updateFrame, "Information updated successfully.");

                                    } else {
                                        JOptionPane.showMessageDialog(updateFrame, "Failed to update information. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                                    }
                                    updateFrame.dispose();

                                }
                            });

                            updateFrame.add(mainPanel);
                            updateFrame.setVisible(true);
                        }
                    });
                    profile.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            JFrame profileFrame = new JFrame("Profile Page");
                            profileFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Dispose the frame on close
                            profileFrame.setSize(500, 400);
                            profileFrame.setLocationRelativeTo(null);
                            JPanel mainPanel = new JPanel();
                            mainPanel.setLayout(new GridLayout(0, 1));
                            profileFrame.setVisible(true);

                            JLabel staffIDLabel = new JLabel("STAFF ID: " + HFCAdminStaff.staffID);
                            JLabel firstNameLabel = new JLabel("FIRST NAME: " + HFCAdminStaff.firstName);
                            JLabel lastNameLabel = new JLabel("LAST NAME: " + HFCAdminStaff.lastName);
                            JLabel emailLabel = new JLabel("EMAIL: " + HFCAdminStaff.email);
                            JLabel phoneLabel = new JLabel("PHONE: " + HFCAdminStaff.phone);


                            staffIDLabel.setBorder(BorderFactory.createEmptyBorder(5, 0, 10, 0));
                            firstNameLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
                            lastNameLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
                            emailLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
                            phoneLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

                            JButton closeButton = new JButton("Close");

                            closeButton.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    profileFrame.dispose();                                        }
                            });


                            mainPanel.add(staffIDLabel);
                            mainPanel.add(firstNameLabel);
                            mainPanel.add(lastNameLabel);
                            mainPanel.add(emailLabel);
                            mainPanel.add(phoneLabel);
                            profileFrame.add(closeButton, BorderLayout.SOUTH);
                            profileFrame.add(mainPanel);
                        }
                    });

                    addTrainer.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String firstName = JOptionPane.showInputDialog(null, "Enter trainer's first name:");
                            String lastName = JOptionPane.showInputDialog(null, "Enter trainer's last name:");
                            String email = JOptionPane.showInputDialog(null, "Enter trainer's email:");
                            String password = JOptionPane.showInputDialog(null, "Enter trainer's password:");
                            String specialization = JOptionPane.showInputDialog(null, "Enter trainer's specialization:");
                            String certification = JOptionPane.showInputDialog(null, "Enter trainer's certification:");
                            String phone = JOptionPane.showInputDialog(null, "Enter trainer's phone number:");

                            if (firstName != null && lastName != null && email != null && password != null && specialization != null && certification != null && phone != null) {
                                if (addTrainer(firstName, lastName, email, password, specialization, certification, phone)) {
                                    JOptionPane.showMessageDialog(null, "Trainer added successfully.");
                                } else {
                                    JOptionPane.showMessageDialog(null, "Failed to add trainer. Please try again.");
                                }
                            }
                        }
                    });

                    roomsButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            JFrame frame = new JFrame("Room Management");
                            frame.setSize(500, 400);
                            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                            frame.setLocationRelativeTo(null);

                            JPanel mainPanel = new JPanel();
                            mainPanel.setLayout(new GridLayout(5, 1));

                            JButton addRoomButton = new JButton("Add Room");
                            JButton removeRoomButton = new JButton("Remove Room");
                            JButton editRoomButton = new JButton("Edit Room");
                            JButton assignRoomButton = new JButton("Assign Room to Booking");
                            JButton viewRoomsButton = new JButton("View Rooms");

                            addRoomButton.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    JOptionPane.showMessageDialog(null, "Add Room functionality to be implemented in GUI but available in COMMANDLINE");
                                }
                            });

                            removeRoomButton.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    JOptionPane.showMessageDialog(null, "Remove Room functionality to be implemented but available in COMMANDLINE");
                                }
                            });

                            editRoomButton.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    JOptionPane.showMessageDialog(null, "Edit Room functionality to be implemented but available in COMMANDLINE");
                                }
                            });

                            assignRoomButton.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    JOptionPane.showMessageDialog(null, "Assign Room to Booking functionality to be implemented but available in COMMANDLINE");
                                }
                            });

                            viewRoomsButton.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    JOptionPane.showMessageDialog(null, "View Rooms functionality to be implemented but available in COMMANDLINE");
                                }
                            });

                            mainPanel.add(addRoomButton);
                            mainPanel.add(removeRoomButton);
                            mainPanel.add(editRoomButton);
                            mainPanel.add(assignRoomButton);
                            mainPanel.add(viewRoomsButton);

                            frame.add(mainPanel);
                            frame.setVisible(true);
                        }
                    });
                    equipmentButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            JOptionPane.showMessageDialog(null, "Equipment functionality to be implemented in GUI but available in COMMANDLINE");
                        }
                    });
                    membersButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            JOptionPane.showMessageDialog(null, "Members functionality to be implemented in GUI but available in COMMANDLINE");

                        }
                    });
                    staffPage.setJMenuBar(menuBar);
                    menuBar.add(mainMenu);
                    staffPage.add(dashboard);
                    staffPage.setVisible(true);
                }
            }
        });
    }
    public static boolean logInStaff(String email, String password){
        String SQL = "SELECT email, userPassword FROM HFCAdminStaff WHERE email = ?";
        try (PreparedStatement pstmt = Main.conn.prepareStatement(SQL)) {
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String storedEmail = rs.getString("email");
                String storedPassword = rs.getString("userPassword");

                return storedEmail.equals(email) && storedPassword.equals(password);

            } else {
                return false;
            }
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }    }
    public static void loadStaffDetails(String email){
        String SQL = "SELECT * FROM HFCAdminStaff WHERE email = ?";
        try (PreparedStatement pstmt = Main.conn.prepareStatement(SQL)) {
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int staffID = rs.getInt("staffID");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String userEmail = rs.getString("email");
                int phone = rs.getInt("phone");

                HFCAdminStaff.staffID = staffID;
                HFCAdminStaff.firstName = firstName;
                HFCAdminStaff.lastName = lastName;
                HFCAdminStaff.email = userEmail;
                HFCAdminStaff.phone = phone;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static boolean updateStaffDetails(String newFirstName, String newLastName, String newEmail, int newPhone){
        String SQL = "UPDATE HFCAdminStaff SET firstName = ?, lastName = ?, email = ?, phone = ? WHERE email = ?";
        try (PreparedStatement pstmt = Main.conn.prepareStatement(SQL)) {
            pstmt.setString(1, newFirstName);
            pstmt.setString(2, newLastName);
            pstmt.setString(3, newEmail);;
            pstmt.setInt(4, newPhone);
            pstmt.setString(5, HFCAdminStaff.email);
            pstmt.executeUpdate();
            loadStaffDetails(newEmail);
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }
    public static boolean addTrainer(String firstName, String lastName, String email, String password, String specialization, String certification, String phone) {
        String SQL = "INSERT INTO HFCTrainer (FirstName, LastName, Email, userPassword, Specialization, Certification, Phone) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = Main.conn.prepareStatement(SQL)) {
            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.setString(3, email);
            pstmt.setString(4, password);
            pstmt.setString(5, specialization);
            pstmt.setString(6, certification);
            pstmt.setString(7, phone);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }
    public static boolean addRoom(String roomName, int capacity, String description){
        String SQL = "INSERT INTO Rooms (RoomName, Capacity, Description) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = Main.conn.prepareStatement(SQL)) {
            pstmt.setString(1, roomName);
            pstmt.setInt(2, capacity);
            pstmt.setString(3, description);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }
    public static boolean removeRoom(String roomName) {
        String SQL = "DELETE FROM Rooms WHERE RoomName = ?";
        try (PreparedStatement pstmt = Main.conn.prepareStatement(SQL)) {
            pstmt.setString(1, roomName);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    public static boolean editRoom(String roomName, int newCapacity, String newDescription) {
        String SQL = "UPDATE Rooms SET Capacity = ?, Description = ? WHERE RoomName = ?";
        try (PreparedStatement pstmt = Main.conn.prepareStatement(SQL)) {
            pstmt.setInt(1, newCapacity);
            pstmt.setString(2, newDescription);
            pstmt.setString(3, roomName);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }
    public static boolean assignRoomToBooking(int bookingID, String roomName) {
        String SQL = "UPDATE Bookings SET RoomName = ? WHERE BookingID = ?";
        try (PreparedStatement pstmt = Main.conn.prepareStatement(SQL)) {
            pstmt.setString(1, roomName);
            pstmt.setInt(2, bookingID);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }
    public static void viewAllRooms() {
        String SQL = "SELECT * FROM Rooms";
        try (PreparedStatement pstmt = Main.conn.prepareStatement(SQL)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String roomName = rs.getString("RoomName");
                int capacity = rs.getInt("Capacity");
                String description = rs.getString("Description");
                System.out.println("Room Name: " + roomName + ", Capacity: " + capacity + ", Description: " + description);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    public static boolean addEquipment(String equipmentName, String description, boolean isFunctional) {
        String SQL = "INSERT INTO Equipment (EquipmentName, Description, IsFunctional) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = Main.conn.prepareStatement(SQL)) {
            pstmt.setString(1, equipmentName);
            pstmt.setString(2, description);
            pstmt.setBoolean(3, isFunctional);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }
    public static boolean removeEquipment(String equipmentName) {
        String SQL = "DELETE FROM Equipment WHERE EquipmentName = ?";
        try (PreparedStatement pstmt = Main.conn.prepareStatement(SQL)) {
            pstmt.setString(1, equipmentName);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }
    public static void viewAllEquipment() {
        String SQL = "SELECT * FROM Equipment";
        try (PreparedStatement pstmt = Main.conn.prepareStatement(SQL)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String equipmentName = rs.getString("EquipmentName");
                String description = rs.getString("Description");
                boolean isFunctional = rs.getBoolean("IsFunctional");
                System.out.println("Equipment Name: " + equipmentName + ", Description: " + description + ", Functional: " + isFunctional);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    public static boolean updateMaintenance(String equipmentName, boolean isFunctional) {
        String SQL = "UPDATE Equipment SET IsFunctional = ? WHERE EquipmentName = ?";
        try (PreparedStatement pstmt = Main.conn.prepareStatement(SQL)) {
            pstmt.setBoolean(1, isFunctional);
            pstmt.setString(2, equipmentName);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }
    public static boolean editBilling(int memberId, double newAmount) {
        String SQL = "UPDATE Billing SET Amount = ? WHERE MemberID = ?";
        try (PreparedStatement pstmt = Main.conn.prepareStatement(SQL)) {
            pstmt.setDouble(1, newAmount);
            pstmt.setInt(2, memberId);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    public static void viewAllMembers() {
        String SQL = "SELECT * FROM HFCMember";
        try (PreparedStatement pstmt = Main.conn.prepareStatement(SQL)) {
            ResultSet rs = pstmt.executeQuery();
            System.out.println("All Members:");
            System.out.println("-------------");
            while (rs.next()) {
                int memberId = rs.getInt("MemberID");
                String firstName = rs.getString("FirstName");
                String lastName = rs.getString("LastName");
                String email = rs.getString("Email");
                int phone = rs.getInt("Phone");

                System.out.println("Member ID: " + memberId);
                System.out.println("Name: " + firstName + " " + lastName);
                System.out.println("Email: " + email);
                System.out.println("Phone: " + phone);
                System.out.println("-------------");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
