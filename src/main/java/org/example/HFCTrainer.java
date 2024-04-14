package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class HFCTrainer extends Member{
    private static int trainerID;
    private static String firstName;
    private static String lastName;
    private static String email;
    private static String specialization;
    private static String certification;
    private static int phone;

    public HFCTrainer() {
        JFrame trainerPage = new JFrame("Trainer Page");
        trainerPage.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        trainerPage.setSize(500, 400);
        trainerPage.setLocationRelativeTo(null);

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

                if (logInTrainer(email, password)) {
                    loadTrainerDetails(email);
                    logInFrame.dispose();
                    JFrame trainerPage = new JFrame("Trainer Page");
                    trainerPage.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Dispose the frame on close
                    trainerPage.setSize(500, 400);
                    trainerPage.setLocationRelativeTo(null);
                    JPanel dashboard = new JPanel();
                    dashboard.setLayout(new GridLayout(0, 1));

                    JMenuBar menuBar = new JMenuBar();
                    JMenu mainMenu = new JMenu("Options");
                    JMenuItem profile = new JMenuItem("My Profile");
                    JMenuItem update = new JMenuItem("Update Trainer Info");
                    JMenuItem logOut = new JMenuItem("Log Out");
                    JButton startSessionButton = new JButton("Start a Session");
                    JButton viewMembersButton = new JButton("View Members");
                    JButton availabilityTool = new JButton("Set your Availability");

                    mainMenu.add(profile);
                    mainMenu.add(update);
                    mainMenu.add(logOut);


                    dashboard.add(startSessionButton);
                    dashboard.add(viewMembersButton);
                    dashboard.add(availabilityTool);
                    logOut.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            trainerPage.dispose();
                        }
                    });

                    update.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            JFrame updateFrame = new JFrame("Update Trainer Info");
                            updateFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Dispose the frame on close
                            updateFrame.setSize(500, 700);
                            updateFrame.setLocationRelativeTo(null);
                            JPanel mainPanel = new JPanel();
                            mainPanel.setLayout(new GridLayout(28, 1));

                            JTextField firstNameField = new JTextField();
                            JTextField lastNameField = new JTextField();
                            JTextField emailField = new JTextField();
                            JTextField specializationField = new JTextField();
                            JTextField certificationField = new JTextField();
                            JTextField phoneField = new JTextField();


                            JLabel firstNameLabel = new JLabel("First Name:");
                            JLabel lastNameLabel = new JLabel("Last Name:");
                            JLabel emailLabel = new JLabel("Email:");
                            JLabel specializationLabel = new JLabel("Specialization: ");
                            JLabel certificationLabel = new JLabel("Certification: ");
                            JLabel phoneLabel = new JLabel("Phone: ");

                            firstNameField.setText(HFCTrainer.firstName);
                            lastNameField.setText(HFCTrainer.lastName);
                            emailField.setText(HFCTrainer.email);
                            specializationField.setText(HFCTrainer.specialization);
                            certificationField.setText(HFCTrainer.certification);
                            phoneField.setText(Integer.toString(HFCTrainer.phone));


                            mainPanel.add(firstNameLabel);
                            mainPanel.add(firstNameField);
                            mainPanel.add(lastNameLabel);
                            mainPanel.add(lastNameField);
                            mainPanel.add(emailLabel);
                            mainPanel.add(emailField);
                            mainPanel.add(specializationLabel);
                            mainPanel.add(specializationField);
                            mainPanel.add(certificationLabel);
                            mainPanel.add(certificationField);
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
                                    String newSpecialization = specializationField.getText();
                                    String newCertification = certificationField.getText();
                                    int newPhone = Integer.parseInt(phoneField.getText());

                                    boolean success = updateTrainerDetails(newFirstName, newLastName, newEmail, newSpecialization, newCertification, newPhone);
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

                            JLabel trainerIDLabel = new JLabel("TRAINER ID: " + HFCTrainer.trainerID);
                            JLabel firstNameLabel = new JLabel("FIRST NAME: " + HFCTrainer.firstName);
                            JLabel lastNameLabel = new JLabel("LAST NAME: " + HFCTrainer.lastName);
                            JLabel emailLabel = new JLabel("EMAIL: " + HFCTrainer.email);
                            JLabel specializationLabel = new JLabel("SPECIALIZATION: " + HFCTrainer.specialization);
                            JLabel certificationLabel = new JLabel("CERTIFICATION: " + HFCTrainer.certification);
                            JLabel phoneLabel = new JLabel("PHONE: " + HFCTrainer.phone);


                            trainerIDLabel.setBorder(BorderFactory.createEmptyBorder(5, 0, 10, 0));
                            firstNameLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
                            lastNameLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
                            emailLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
                            specializationLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
                            certificationLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
                            phoneLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

                            JButton closeButton = new JButton("Close");

                            closeButton.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    profileFrame.dispose();                                        }
                            });


                            mainPanel.add(trainerIDLabel);
                            mainPanel.add(firstNameLabel);
                            mainPanel.add(lastNameLabel);
                            mainPanel.add(emailLabel);
                            mainPanel.add(specializationLabel);
                            mainPanel.add(certificationLabel);
                            mainPanel.add(phoneLabel);
                            profileFrame.add(closeButton, BorderLayout.SOUTH);
                            profileFrame.add(mainPanel);
                        }
                    });

                    startSessionButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            JFrame sessionFrame = new JFrame("Start Training Session");
                            sessionFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                            sessionFrame.setSize(500, 400);
                            sessionFrame.setLocationRelativeTo(null);

                            JPanel mainPanel = new JPanel();
                            mainPanel.setLayout(new GridLayout(0, 1));

                            String[] members = getAllMembers().toArray(new String[0]);

                            String selectedUser = (String) JOptionPane.showInputDialog(null, "Select User:", "Start Session - With WHO?", JOptionPane.QUESTION_MESSAGE, null, members, members[0]);

                            JButton viewProfileButton = new JButton("View Profile");
                            JButton addRoutinesButton = new JButton("Add Routines");
                            JButton addAchievementsButton = new JButton("Add Achievements");
                            JButton viewHealthStatsButton = new JButton("View Health Statistics");

                            viewProfileButton.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    String[] nameParts = selectedUser.split("\\s+");
                                    String lastName = nameParts[1];

                                    ArrayList<Member> members = setAllMembers();
                                    for (Member member : members) {
                                        if (member.getLastName().equals(lastName)) {
                                            JOptionPane.showMessageDialog(null, "User Information:\n"
                                                    + "First Name: " + member.getFirstName() + "\n"
                                                    + "Last Name: " + member.getLastName() + "\n"
                                                    + "Email: " + member.getEmail() + "\n"
                                                    + "Address: " + member.getAddress() + "\n"
                                                    + "City: " + member.getCity() + "\n"
                                                    + "Province: " + member.getProvince() + "\n"
                                                    + "Postal Code: " + member.getPostalCode() + "\n"
                                                    + "Phone: " + member.getPhone() + "\n"
                                                    + "Date of Birth: " + member.getDateOfBirth() + "\n"
                                                    + "Fitness Goal: " + member.getFitnessGoal() + "\n"
                                                    + "Weight Goal: " + member.getWeightGoal() + "\n"
                                                    + "Time Goal: " + member.getTimeGoal() + "\n"
                                                    + "Join Date: " + member.getJoinDate() + "\n"
                                            );
                                            return;
                                        }
                                    }
                                    JOptionPane.showMessageDialog(null, "User not found.");
                                }
                            });

                            addRoutinesButton.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    // Get the member's ID from the selected user
                                    String[] nameParts = selectedUser.split("\\s+");
                                    String firstName = nameParts[0];
                                    String lastName = nameParts[1];
                                    int memberID = getMemberIDByName(firstName, lastName);

                                    JFrame addRoutineFrame = new JFrame("Add Routine");
                                    addRoutineFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                                    addRoutineFrame.setSize(400, 300);
                                    addRoutineFrame.setLocationRelativeTo(null);

                                    JPanel inputPanel = new JPanel(new GridLayout(4, 2));

                                    JTextField exerciseTypeField = new JTextField();
                                    JTextField setsField = new JTextField();
                                    JTextField repsField = new JTextField();

                                    inputPanel.add(new JLabel("Exercise Type:"));
                                    inputPanel.add(exerciseTypeField);
                                    inputPanel.add(new JLabel("Sets:"));
                                    inputPanel.add(setsField);
                                    inputPanel.add(new JLabel("Reps:"));
                                    inputPanel.add(repsField);

                                    JButton confirmButton = new JButton("Add Routine");
                                    confirmButton.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            String exerciseType = exerciseTypeField.getText();
                                            int sets = Integer.parseInt(setsField.getText());
                                            int reps = Integer.parseInt(repsField.getText());

                                            if (exerciseType.isEmpty()) {
                                                JOptionPane.showMessageDialog(addRoutineFrame, "Please enter the exercise type.");
                                                return;
                                            }

                                            if (insertRoutine(memberID, exerciseType, sets, reps)) {
                                                JOptionPane.showMessageDialog(addRoutineFrame, "Routine added successfully.");
                                            } else {
                                                JOptionPane.showMessageDialog(addRoutineFrame, "Failed to add routine. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                                            }
                                            addRoutineFrame.dispose();
                                        }
                                    });

                                    addRoutineFrame.add(inputPanel, BorderLayout.CENTER);
                                    addRoutineFrame.add(confirmButton, BorderLayout.SOUTH);

                                    addRoutineFrame.setVisible(true);
                                }
                            });

                            addAchievementsButton.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    // Get the member's ID from the selected user
                                    String[] nameParts = selectedUser.split("\\s+");
                                    String firstName = nameParts[0];
                                    String lastName = nameParts[1];
                                    int memberID = getMemberIDByName(firstName, lastName);

                                    // Create a dialog for adding achievements
                                    JFrame addAchievementFrame = new JFrame("Add Achievement");
                                    addAchievementFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                                    addAchievementFrame.setSize(400, 200);
                                    addAchievementFrame.setLocationRelativeTo(null);

                                    // Panel to hold input fields
                                    JPanel inputPanel = new JPanel(new GridLayout(2, 2));

                                    JTextField achievementField = new JTextField();

                                    inputPanel.add(new JLabel("Achievement:"));
                                    inputPanel.add(achievementField);

                                    // Button to confirm achievement addition
                                    JButton confirmButton = new JButton("Add Achievement");
                                    confirmButton.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            // Get user input
                                            String achievement = achievementField.getText();

                                            // Validate input
                                            if (achievement.isEmpty()) {
                                                JOptionPane.showMessageDialog(addAchievementFrame, "Please enter the achievement.");
                                                return;
                                            }

                                            // Insert achievement into database
                                            if (insertAchievement(memberID, achievement)) {
                                                JOptionPane.showMessageDialog(addAchievementFrame, "Achievement added successfully.");
                                                // Optionally update any GUI elements to reflect the new achievement
                                            } else {
                                                JOptionPane.showMessageDialog(addAchievementFrame, "Failed to add achievement. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                                            }

                                            // Close the dialog
                                            addAchievementFrame.dispose();
                                        }
                                    });

                                    // Add components to the frame
                                    addAchievementFrame.add(inputPanel, BorderLayout.CENTER);
                                    addAchievementFrame.add(confirmButton, BorderLayout.SOUTH);

                                    // Make the frame visible
                                    addAchievementFrame.setVisible(true);
                                }
                            });

                            viewHealthStatsButton.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    String weightInput = JOptionPane.showInputDialog(null, "Enter the user's current weight (in kilograms):");
                                    if (weightInput == null) {
                                        return; // If cancel is clicked, return without further action
                                    }
                                    double weight = Double.parseDouble(weightInput);

                                    String heightInput = JOptionPane.showInputDialog(null, "Enter the user's current height (in meters):");
                                    if (heightInput == null) {
                                        return; // If cancel is clicked, return without further action
                                    }
                                    double height = Double.parseDouble(heightInput);

                                    double bmi = weight / (height * height);
                                        String message = "Data:\n"
                                                + "First Name: " + firstName + "\n"
                                                + "Last Name: " + lastName + "\n"
                                                + "Weight: " + weight + " kg\n"
                                                + "Height: " + height + " meters\n"
                                                + "BMI: " + bmi + "\n";
                                        JOptionPane.showMessageDialog(null, message, "Entered Data", JOptionPane.INFORMATION_MESSAGE);
                                    }
                            });

                            mainPanel.add(viewProfileButton);
                            mainPanel.add(addRoutinesButton);
                            mainPanel.add(addAchievementsButton);
                            mainPanel.add(viewHealthStatsButton);

                            sessionFrame.add(mainPanel);
                            sessionFrame.setVisible(true);
                        }
                    });

                    viewMembersButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            ArrayList<Member> members = setAllMembers();

                            if (members.isEmpty()) {
                                JOptionPane.showMessageDialog(null, "No members found.", "No Members", JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                StringBuilder memberInfo = new StringBuilder();
                                for (Member member : members) {
                                    memberInfo.append("Member ID: ").append(member.getMemberID()).append("\n");
                                    memberInfo.append("Name: ").append(member.getFirstName()).append(" ").append(member.getLastName()).append("\n");
                                    memberInfo.append("Email: ").append(member.getEmail()).append("\n");
                                    memberInfo.append("Fitness Goal: ").append(member.getFitnessGoal()).append("\n\n");
                                }
                                JOptionPane.showMessageDialog(null, memberInfo.toString(), "Members Information", JOptionPane.INFORMATION_MESSAGE);
                            }
                        }
                    });

                    availabilityTool.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            JFrame availabilityFrame = new JFrame("Set Availability");
                            availabilityFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                            availabilityFrame.setSize(500, 400);
                            availabilityFrame.setLocationRelativeTo(null);

                            JPanel panel = new JPanel(new GridLayout(4, 2));

                            JLabel weekdayLabel = new JLabel("Select weekday:");
                            JComboBox<HFCMember.Weekday> weekdayDropdown = new JComboBox<>(HFCMember.Weekday.values());

                            JButton setAvailabilityButton = new JButton("Set Availability");
                            JButton cancelButton = new JButton("Cancel");

                            setAvailabilityButton.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    HFCMember.Weekday selectedWeekday = (HFCMember.Weekday) weekdayDropdown.getSelectedItem();

                                    setAvailability(selectedWeekday.toString());
                                    availabilityFrame.dispose();
                                }
                            });
                            cancelButton.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    availabilityFrame.dispose();
                                }
                            });

                            panel.add(weekdayLabel);
                            panel.add(weekdayDropdown);
                            panel.add(setAvailabilityButton);
                            panel.add(cancelButton);

                            availabilityFrame.add(panel);
                            availabilityFrame.setVisible(true);
                        }
                    });
                    trainerPage.setJMenuBar(menuBar);
                    menuBar.add(mainMenu);
                    trainerPage.add(dashboard);
                    trainerPage.setVisible(true);
                }
            }
        });
    }
    public static boolean logInTrainer(String email, String password) {
        String SQL = "SELECT email, userPassword FROM HFCTrainer WHERE email = ?";
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
        }
    }
    public static void loadTrainerDetails(String email){
        String SQL = "SELECT * FROM HFCTrainer WHERE email = ?";
        try (PreparedStatement pstmt = Main.conn.prepareStatement(SQL)) {
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int trainerID = rs.getInt("trainerID");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String userEmail = rs.getString("email");
                String specialization = rs.getString("specialization");
                String certification = rs.getString("Certification");
                int phone = rs.getInt("phone");

                HFCTrainer.trainerID = trainerID;
                HFCTrainer.firstName = firstName;
                HFCTrainer.lastName = lastName;
                HFCTrainer.email = userEmail;
                HFCTrainer.specialization = specialization;
                HFCTrainer.certification = certification;
                HFCTrainer.phone = phone;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    public static boolean updateTrainerDetails(String newFirstName, String newLastName, String newEmail, String newSpecialization, String newCertification, int newPhone) {
        String SQL = "UPDATE HFCTrainer SET firstName = ?, lastName = ?, email = ?, specialization = ?, certification = ?, phone = ? WHERE email = ?";
        try (PreparedStatement pstmt = Main.conn.prepareStatement(SQL)) {
            pstmt.setString(1, newFirstName);
            pstmt.setString(2, newLastName);
            pstmt.setString(3, newEmail);
            pstmt.setString(4, newSpecialization);
            pstmt.setString(5, newCertification);
            pstmt.setInt(6, newPhone);
            pstmt.setString(7, HFCTrainer.email);
            pstmt.executeUpdate();
            loadTrainerDetails(newEmail);
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }
    public static ArrayList<String> getAllMembers() {
        ArrayList<String> userList = new ArrayList<>();
        String SQL = "SELECT FirstName, LastName FROM HFCMember";
        try (PreparedStatement pstmt = Main.conn.prepareStatement(SQL);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                String firstName = rs.getString("FirstName");
                String lastName = rs.getString("LastName");
                String fullName = firstName + " " + lastName;
                userList.add(fullName);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return userList;
    }

    public ArrayList<Member> setAllMembers() {
        ArrayList<Member> memberList = new ArrayList<>();
        String SQL = "SELECT * FROM HFCMember ";
        try (PreparedStatement pstmt = Main.conn.prepareStatement(SQL);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Member member = new Member();
                member.setMemberID(rs.getInt("MemberID"));
                member.setFirstName(rs.getString("FirstName"));
                member.setLastName(rs.getString("LastName"));
                member.setEmail(rs.getString("Email"));
                member.setAddress(rs.getString("Address"));
                member.setCity(rs.getString("City"));
                member.setProvince(rs.getString("Province"));
                member.setPostalCode(rs.getString("PostalCode"));
                member.setPhone(rs.getString("Phone"));
                member.setDateOfBirth(rs.getDate("DOB"));
                member.setFitnessGoal(rs.getString("FitnessGoal"));
                member.setWeightGoal(rs.getDouble("WeightGoal"));
                member.setTimeGoal(rs.getString("TimeGoal"));
                member.setJoinDate(rs.getDate("JoinDate"));
                member.setTrainerID(rs.getInt("TrainerID"));
                memberList.add(member);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return memberList;
    }
    private boolean insertRoutine(int memberID, String exerciseType, int sets, int reps) {
        String SQL = "INSERT INTO ExerciseRoutine (MemberID, ExerciseType, Sets, Reps) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = Main.conn.prepareStatement(SQL)) {
            pstmt.setInt(1, memberID);
            pstmt.setString(2, exerciseType);
            pstmt.setInt(3, sets);
            pstmt.setInt(4, reps);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
    public int getMemberIDByName(String firstName, String lastName) {
        String query = "SELECT MemberID FROM HFCMember WHERE FirstName = ? AND LastName = ?";
        try (PreparedStatement pstmt = Main.conn.prepareStatement(query)) {
            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("MemberID");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return -1;
    }

    public static boolean insertAchievement(int memberID, String achievement) {
        String SQL = "INSERT INTO Achievements (MemberID, Achievement) VALUES (?, ?)";
        try (PreparedStatement pstmt = Main.conn.prepareStatement(SQL)) {
            pstmt.setInt(1, memberID);
            pstmt.setString(2, achievement);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }
    public static double getWeightGoalByMemberID(int memberID) {
        double weightGoal = 0.0;
        String SQL = "SELECT WeightGoal FROM HFCMember WHERE MemberID = ?";
        try (PreparedStatement pstmt = Main.conn.prepareStatement(SQL)) {
            pstmt.setInt(1, memberID);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                weightGoal = rs.getDouble("WeightGoal");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return weightGoal;
    }
    public enum Weekday {
        MONDAY,
        TUESDAY,
        WEDNESDAY,
        THURSDAY,
        FRIDAY,
        SATURDAY,
        SUNDAY
    }
    public static void setAvailability(String weekday) {
        String SQL = "INSERT INTO TrainerAvailability (TrainerID, Weekday) VALUES (?, ?)";
        try (PreparedStatement pstmt = Main.conn.prepareStatement(SQL)) {
            pstmt.setInt(1, HFCTrainer.trainerID);
            pstmt.setString(2, weekday);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}