package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class HFCMember extends JFrame {
    private static int memberID;
    private static String firstName;
    private static String lastName;
    private static String email;
    private static String userPassword;
    private static String address;
    private static String city;
    private static String province;
    private static String postalCode;
    private static int phone;
    private static Date DOB;
    private static String fitnessGoal;
    private static double weightGoal;
    private static String timeGoal;
    private static Date joinDate;
    private static StringBuilder workoutRoutine;

    public HFCMember() {
        setTitle("Member Page");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);

        JLabel signInOptionsLabel = new JLabel("Would you like to Sign Up or Log In?");

        JButton signUpButton = new JButton(" SIGN UP");
        JButton logInButton = new JButton("LOG IN");


        setLayout(new GridLayout(3, 2));

        add(signInOptionsLabel);
        add(signUpButton);
        add(logInButton);

        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame signUpFrame = new JFrame("Sign Up");
                signUpFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Dispose the frame on close
                signUpFrame.setSize(500, 700);
                signUpFrame.setLocationRelativeTo(null);
                JPanel mainPanel = new JPanel();
                mainPanel.setLayout(new GridLayout(28, 1));

                JTextField firstNameField = new JTextField();
                JTextField lastNameField = new JTextField();
                JTextField emailField = new JTextField();
                JTextField passwordField = new JTextField();
                JTextField addressField = new JTextField();
                JTextField cityField = new JTextField();
                JTextField provinceField = new JTextField();
                JTextField postalCodeField = new JTextField();
                JTextField phoneField = new JTextField();
                JTextField dobField = new JTextField();
                JTextField fitnessGoalField = new JTextField();
                JTextField weightGoalField = new JTextField();
                JTextField timeGoalField = new JTextField();
                JTextField enrollmentDateField = new JTextField();

                JLabel firstNameLabel = new JLabel("First Name:");
                JLabel lastNameLabel = new JLabel("Last Name:");
                JLabel emailLabel = new JLabel("Email:");
                JLabel passwordLabel = new JLabel("Password:");
                JLabel addressLabel = new JLabel("Home Address:");
                JLabel cityLabel = new JLabel("City:");
                JLabel provinceLabel = new JLabel("Province:");
                JLabel postalCodeLabel = new JLabel("Postal Code:");
                JLabel phoneLabel = new JLabel("Phone Number:");
                JLabel dobLabel = new JLabel("Date Of Birth (Year-Month-Day):");
                JLabel fitnessGoalLabel = new JLabel("Fitness Goal:");
                JLabel weightGoalLabel = new JLabel("Weight Goal:");
                JLabel timeGoalLabel = new JLabel("Time Goal:");
                JLabel enrollmentDateLabel = new JLabel("Enrollment Date:");

                signUpFrame.add(mainPanel);

                mainPanel.add(firstNameLabel);
                mainPanel.add(firstNameField);

                mainPanel.add(lastNameLabel);
                mainPanel.add(lastNameField);

                mainPanel.add(emailLabel);
                mainPanel.add(emailField);

                mainPanel.add(passwordLabel);
                mainPanel.add(passwordField);

                mainPanel.add(addressLabel);
                mainPanel.add(addressField);

                mainPanel.add(cityLabel);
                mainPanel.add(cityField);

                mainPanel.add(provinceLabel);
                mainPanel.add(provinceField);

                mainPanel.add(postalCodeLabel);
                mainPanel.add(postalCodeField);

                mainPanel.add(phoneLabel);
                mainPanel.add(phoneField);

                mainPanel.add(dobLabel);
                mainPanel.add(dobField);

                mainPanel.add(fitnessGoalLabel);
                mainPanel.add(fitnessGoalField);

                mainPanel.add(weightGoalLabel);
                mainPanel.add(weightGoalField);

                mainPanel.add(timeGoalLabel);
                mainPanel.add(timeGoalField);

                mainPanel.add(enrollmentDateLabel);
                mainPanel.add(enrollmentDateField);

                JButton confirmButton = new JButton("Confirm");
                signUpFrame.add(confirmButton, BorderLayout.SOUTH);

                confirmButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        if (firstNameField.getText().isEmpty() || lastNameField.getText().isEmpty() || emailField.getText().isEmpty() || passwordField.getText().isEmpty() || addressField.getText().isEmpty() || cityField.getText().isEmpty() || provinceField.getText().isEmpty() || postalCodeField.getText().isEmpty() || phoneField.getText().isEmpty() || dobField.getText().isEmpty() || fitnessGoalField.getText().isEmpty() || weightGoalField.getText().isEmpty()) {

                            JOptionPane.showMessageDialog(null, "Please fill in all required fields.", "Error", JOptionPane.ERROR_MESSAGE);
                        }

                        String firstName = firstNameField.getText();
                        String lastName = lastNameField.getText();
                        String email = emailField.getText();
                        String userPassword = passwordField.getText();
                        String address = addressField.getText();
                        String city = cityField.getText();
                        String province = provinceField.getText();
                        String postalCode = postalCodeField.getText();
                        int phone = Integer.parseInt(phoneField.getText());
                        Date dob = new Date(2001, 1, 1);
                        String fitnessGoal = fitnessGoalField.getText();
                        double weightGoal = Double.parseDouble(weightGoalField.getText());
                        String timeGoal = timeGoalField.getText();
                        Date joinDate = new Date(2024, 4, 9);

                        boolean success = registerMember(firstName, lastName, email, userPassword, address, city, province, postalCode, phone, dob, fitnessGoal, weightGoal, timeGoal, joinDate);


                        if (!success) {
                            JOptionPane.showMessageDialog(signUpFrame, "Registration failed. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(signUpFrame, "Registration successful! You may now Log In.", "Success", JOptionPane.INFORMATION_MESSAGE);
                        }
                        signUpFrame.dispose();
                    }
                });

                signUpFrame.setVisible(true);
            }
        });

        logInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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

                        if (logInMember(email, password)) {
                            loadMemberDetails(email);
                            logInFrame.dispose();
                            JFrame memberPage = new JFrame("Member Page");
                            memberPage.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Dispose the frame on close
                            memberPage.setSize(500, 400);
                            memberPage.setLocationRelativeTo(null);
                            JPanel dashboard = new JPanel();
                            dashboard.setLayout(new GridLayout(0, 1));

                            JMenuBar menuBar = new JMenuBar();
                            JMenu mainMenu = new JMenu("Options");
                            JMenuItem profile = new JMenuItem("My Profile");
                            JMenuItem update = new JMenuItem("Update Membership Info");
                            JMenuItem logOut = new JMenuItem("Log Out");
                            JButton exerciseRoutine = new JButton("View Exercise Routines");
                            JButton fitnessAchievements = new JButton("View Fitness Achievements");
                            JButton healthStatistics = new JButton("View Health Statistics");
                            JButton bookSession = new JButton("Book a new Session");
                            JButton viewSession = new JButton("View Sessions");
                            JButton payBill = new JButton("Pay Bill");
                            JButton availabilityTool = new JButton("Set your Availability");

                            mainMenu.add(profile);
                            mainMenu.add(update);
                            mainMenu.add(logOut);


                            dashboard.add(exerciseRoutine);
                            dashboard.add(fitnessAchievements);
                            dashboard.add(healthStatistics);
                            dashboard.add(bookSession);
                            dashboard.add(viewSession);
                            dashboard.add(availabilityTool);
                            dashboard.add(payBill);
                            logOut.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    memberPage.dispose();
                                }
                            });

                            update.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    JFrame updateFrame = new JFrame("Update Membership Info");
                                    updateFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Dispose the frame on close
                                    updateFrame.setSize(500, 700);
                                    updateFrame.setLocationRelativeTo(null);
                                    JPanel mainPanel = new JPanel();
                                    mainPanel.setLayout(new GridLayout(28, 1));

                                    JTextField firstNameField = new JTextField();
                                    JTextField lastNameField = new JTextField();
                                    JTextField emailField = new JTextField();
                                    JTextField addressField = new JTextField();
                                    JTextField cityField = new JTextField();
                                    JTextField provinceField = new JTextField();
                                    JTextField postalCodeField = new JTextField();
                                    JTextField phoneField = new JTextField();
                                    JTextField fitnessGoalField = new JTextField();
                                    JTextField weightGoalField = new JTextField();
                                    JTextField timeGoalField = new JTextField();

                                    JLabel firstNameLabel = new JLabel("First Name:");
                                    JLabel lastNameLabel = new JLabel("Last Name:");
                                    JLabel emailLabel = new JLabel("Email:");
                                    JLabel addressLabel = new JLabel("Address: ");
                                    JLabel cityLabel = new JLabel("City: ");
                                    JLabel provinceLabel = new JLabel("Province: ");
                                    JLabel postalCodeLabel = new JLabel("Postal Code: ");
                                    JLabel phoneLabel = new JLabel("Phone: ");
                                    JLabel fitnessGoalLabel = new JLabel("Fitness Goal: ");
                                    JLabel weightGoalLabel = new JLabel("Weight Goal: ");
                                    JLabel timeGoalLabel = new JLabel("Time Goal: ");

                                    firstNameField.setText(HFCMember.firstName);
                                    lastNameField.setText(HFCMember.lastName);
                                    emailField.setText(HFCMember.email);
                                    addressField.setText(HFCMember.address);
                                    cityField.setText(HFCMember.city);
                                    provinceField.setText(HFCMember.province);
                                    postalCodeField.setText(HFCMember.postalCode);
                                    phoneField.setText(Integer.toString(HFCMember.phone));
                                    fitnessGoalField.setText(HFCMember.fitnessGoal);
                                    weightGoalField.setText(Double.toString(HFCMember.weightGoal));
                                    timeGoalField.setText(HFCMember.timeGoal);

                                    mainPanel.add(firstNameLabel);
                                    mainPanel.add(firstNameField);
                                    mainPanel.add(lastNameLabel);
                                    mainPanel.add(lastNameField);
                                    mainPanel.add(emailLabel);
                                    mainPanel.add(emailField);
                                    mainPanel.add(addressLabel);
                                    mainPanel.add(addressField);
                                    mainPanel.add(cityLabel);
                                    mainPanel.add(cityField);
                                    mainPanel.add(provinceLabel);
                                    mainPanel.add(provinceField);
                                    mainPanel.add(postalCodeLabel);
                                    mainPanel.add(postalCodeField);
                                    mainPanel.add(phoneLabel);
                                    mainPanel.add(phoneField);
                                    mainPanel.add(fitnessGoalLabel);
                                    mainPanel.add(fitnessGoalField);
                                    mainPanel.add(weightGoalLabel);
                                    mainPanel.add(weightGoalField);
                                    mainPanel.add(timeGoalLabel);
                                    mainPanel.add(timeGoalField);

                                    JButton confirmButton = new JButton("Confirm");
                                    updateFrame.add(confirmButton, BorderLayout.SOUTH);

                                    confirmButton.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            // Get the updated values from the text fields
                                            String newFirstName = firstNameField.getText();
                                            String newLastName = lastNameField.getText();
                                            String newEmail = emailField.getText();
                                            String newAddress = addressField.getText();
                                            String newCity = cityField.getText();
                                            String newProvince = provinceField.getText();
                                            String newPostalCode = postalCodeField.getText();
                                            int newPhone = Integer.parseInt(phoneField.getText());
                                            String newFitnessGoal = fitnessGoalField.getText();
                                            double newWeightGoal = Double.parseDouble(weightGoalField.getText());
                                            String newTimeGoal = timeGoalField.getText();

                                            boolean success = updateMemberDetails(newFirstName, newLastName, newEmail, newAddress, newCity, newProvince, newPostalCode, newPhone, newFitnessGoal, newWeightGoal, newTimeGoal);
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

                                    JLabel memberIDLabel = new JLabel("MEMBER ID: " + memberID);
                                    JLabel firstNameLabel = new JLabel("FIRST NAME: " + firstName);
                                    JLabel lastNameLabel = new JLabel("LAST NAME: " + lastName);
                                    JLabel emailLabel = new JLabel("EMAIL: " + email);
                                    JLabel addressLabel = new JLabel("ADDRESS: " + address);
                                    JLabel cityLabel = new JLabel("CITY: " + city);
                                    JLabel provinceLabel = new JLabel("PROVINCE: " + province);
                                    JLabel postalCodeLabel = new JLabel("POSTAL CODE: " + postalCode);
                                    JLabel phoneLabel = new JLabel("PHONE: " + phone);
                                    JLabel dobLabel = new JLabel("DATE OF BIRTH: " + DOB);
                                    JLabel fitnessGoalLabel = new JLabel("FITNESS GOAL: " + fitnessGoal);
                                    JLabel weightGoalLabel = new JLabel("WEIGHT GOAL: " + weightGoal);
                                    JLabel timeGoalLabel = new JLabel("TIME GOAL: " + timeGoal);
                                    JLabel joinDateLabel = new JLabel("JOIN DATE: " + joinDate);

                                    memberIDLabel.setBorder(BorderFactory.createEmptyBorder(5, 0, 10, 0));
                                    firstNameLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
                                    lastNameLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
                                    emailLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
                                    addressLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
                                    cityLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
                                    provinceLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
                                    postalCodeLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
                                    phoneLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
                                    dobLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
                                    fitnessGoalLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
                                    weightGoalLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
                                    timeGoalLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
                                    joinDateLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

                                    JButton closeButton = new JButton("Close");

                                    closeButton.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            profileFrame.dispose();                                        }
                                    });


                                    mainPanel.add(memberIDLabel);
                                    mainPanel.add(firstNameLabel);
                                    mainPanel.add(lastNameLabel);
                                    mainPanel.add(emailLabel);
                                    mainPanel.add(addressLabel);
                                    mainPanel.add(cityLabel);
                                    mainPanel.add(provinceLabel);
                                    mainPanel.add(postalCodeLabel);
                                    mainPanel.add(phoneLabel);
                                    mainPanel.add(dobLabel);
                                    mainPanel.add(fitnessGoalLabel);
                                    mainPanel.add(weightGoalLabel);
                                    mainPanel.add(timeGoalLabel);
                                    mainPanel.add(joinDateLabel);
                                    profileFrame.add(closeButton, BorderLayout.SOUTH);
                                    profileFrame.add(mainPanel);
                                }
                            });

                            exerciseRoutine.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    JFrame exerciseFrame = new JFrame("Exercise Routines");
                                    exerciseFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                                    exerciseFrame.setSize(500, 400);
                                    exerciseFrame.setLocationRelativeTo(null);
                                    JPanel mainPanel = new JPanel();
                                    mainPanel.setLayout(new GridLayout(0, 1));

                                    JButton routineButton = new JButton("Current Routines");
                                    JButton createRoutineButton = new JButton("Create New Routine");
                                    JButton editRoutineButton = new JButton("Remove a Routine");
                                    JButton backButton = new JButton("Back");

                                    routineButton.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            JFrame routinesFrame = new JFrame("Current Routines");
                                            routinesFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                                            routinesFrame.setSize(500, 400);
                                            routinesFrame.setLocationRelativeTo(null);

                                            JPanel routinesPanel = new JPanel(new BorderLayout());
                                            JPanel restPanel = new JPanel(new BorderLayout());

                                            String routineDetails = displayWorkoutRoutine();

                                            JTextArea routinesTextArea = new JTextArea(routineDetails);
                                            routinesTextArea.setEditable(false); // Make it non-editable

                                            JScrollPane scrollPane = new JScrollPane(routinesTextArea);
                                            routinesPanel.add(scrollPane, BorderLayout.CENTER);

                                            JButton backButton = new JButton("Back");

                                            backButton.addActionListener(new ActionListener() {
                                                @Override
                                                public void actionPerformed(ActionEvent e) {
                                                    routinesFrame.dispose();
                                                }
                                            });
                                            restPanel.add(backButton, BorderLayout.SOUTH);
                                            routinesFrame.add(routinesPanel, BorderLayout.CENTER);
                                            routinesFrame.add(restPanel, BorderLayout.SOUTH);
                                            routinesFrame.setVisible(true);
                                        }
                                    });

                                    createRoutineButton.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            JFrame createRoutineFrame = new JFrame("Create Workout Routine");
                                            createRoutineFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Dispose the frame on close
                                            createRoutineFrame.setSize(400, 200);
                                            createRoutineFrame.setLocationRelativeTo(null); // Center the frame on the screen

                                            JPanel createRoutinePanel = new JPanel(new GridLayout(0, 2)); // Use a GridLayout with 2 columns

                                            String[] exerciseOptions = {"Push-ups", "Squats", "Lunges", "Planks", "Deadlifts"};
                                            JComboBox<String> exerciseDropdown = new JComboBox<>(exerciseOptions);
                                            JLabel exerciseLabel = new JLabel("Exercise Routine:");
                                            createRoutinePanel.add(exerciseLabel);
                                            createRoutinePanel.add(exerciseDropdown);

                                            String[] setsOptions = {"1", "2", "3", "4", "5"};
                                            JComboBox<String> setsDropdown = new JComboBox<>(setsOptions);
                                            JLabel setsLabel = new JLabel("Sets:");
                                            createRoutinePanel.add(setsLabel);
                                            createRoutinePanel.add(setsDropdown);

                                            String[] repsOptions = {"5", "10", "15", "20", "25"};
                                            JComboBox<String> repsDropdown = new JComboBox<>(repsOptions);
                                            JLabel repsLabel = new JLabel("Reps:");
                                            createRoutinePanel.add(repsLabel);
                                            createRoutinePanel.add(repsDropdown);

                                            JButton confirmButton = new JButton("Confirm");
                                            JButton backButton = new JButton("Back");

                                            createRoutinePanel.add(backButton);
                                            createRoutinePanel.add(confirmButton);

                                            confirmButton.addActionListener(new ActionListener() {
                                                @Override
                                                public void actionPerformed(ActionEvent e) {
                                                    String exercise = (String) exerciseDropdown.getSelectedItem();
                                                    int sets = Integer.parseInt((String) setsDropdown.getSelectedItem());
                                                    int reps = Integer.parseInt((String) repsDropdown.getSelectedItem());

                                                    createWorkoutRoutine(exercise, sets, reps);

                                                    createRoutineFrame.dispose();
                                                }
                                            });
                                            backButton.addActionListener(new ActionListener() {
                                                @Override
                                                public void actionPerformed(ActionEvent e) {
                                                    createRoutineFrame.dispose();
                                                }
                                            });

                                            createRoutineFrame.add(createRoutinePanel);

                                            createRoutineFrame.setVisible(true);
                                        }
                                    });

                                    editRoutineButton.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            String input = JOptionPane.showInputDialog(null, "Enter RoutineID to remove:");
                                            if (input != null && !input.isEmpty()){
                                                int routineID = Integer.parseInt(input);
                                                removeWorkoutRoutine(routineID);
                                            } else {
                                                JOptionPane.showMessageDialog(null, "No input provided. Please enter a RoutineID.");

                                            }

                                        }
                                    });

                                    backButton.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            exerciseFrame.dispose();
                                        }
                                    });

                                    mainPanel.add(routineButton);
                                    mainPanel.add(createRoutineButton);
                                    mainPanel.add(editRoutineButton);
                                    mainPanel.add(backButton);

                                    exerciseFrame.add(mainPanel);
                                    exerciseFrame.setVisible(true);
                                }
                            });

                            fitnessAchievements.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    String achievements = getAchievements();
                                    if (!achievements.isEmpty()) {
                                        JFrame achievementFrame = new JFrame("Achievements");
                                        achievementFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                                        achievementFrame.setSize(500, 400);
                                        achievementFrame.setLocationRelativeTo(null);

                                        JPanel mainPanel = new JPanel(new BorderLayout());
                                        JTextArea achievementsTextArea = new JTextArea(achievements);
                                        achievementsTextArea.setEditable(false);
                                        JScrollPane scrollPane = new JScrollPane(achievementsTextArea);

                                        mainPanel.add(scrollPane, BorderLayout.CENTER);
                                        achievementFrame.add(mainPanel, BorderLayout.CENTER);
                                        achievementFrame.setVisible(true);

                                        JPanel buttonPanel = new JPanel(new BorderLayout());
                                        JButton closeButton = new JButton("Close");
                                        closeButton.addActionListener(new ActionListener() {
                                            @Override
                                            public void actionPerformed(ActionEvent e) {
                                                achievementFrame.dispose();
                                            }
                                        });
                                        buttonPanel.add(closeButton);
                                        achievementFrame.add(buttonPanel, BorderLayout.SOUTH);
                                    } else {
                                        JOptionPane.showMessageDialog(null, "No achievements found.");
                                    }
                                }
                            });

                            healthStatistics.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    // Add action for viewing health statistics
                                }
                            });

                            bookSession.addActionListener(new ActionListener(){
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    // Add action for viewing health statistics
                                }
                            });
                            viewSession.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {

                                }
                            });

                            availabilityTool.addActionListener(new ActionListener(){
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    // Add action for viewing health statistics
                                }
                            });
                            payBill.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {

                                }
                            });

                            memberPage.setJMenuBar(menuBar);
                            menuBar.add(mainMenu);
                            memberPage.add(dashboard);
                            memberPage.setVisible(true);
                        }
                        else {
                            JOptionPane.showMessageDialog(new JFrame("ERROR"), "Registration failed. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                        }

                    }
                });
            }
        });
    }

    //    public static void registerTest(String firstname, String lastname) {
//        String insert = "INSERT INTO test (firstname, lastname) VALUES(?,?)";
//        try (PreparedStatement pstmt = Main.conn.prepareStatement(insert)) {
//            pstmt.setString(1, firstname);
//            pstmt.setString(2,lastname);
//            pstmt.executeUpdate(); // Execute the SQL statement
//
//
//        } catch (SQLException e){
//            System.out.println(e);
//        }
//    }
    public static boolean registerMember(String firstName, String lastName, String email, String userPassword, String address, String city, String province, String postalCode, int phone, Date dob, String fitnessGoal, double weightGoal, String timeGoal, Date joinDate) {
        String insert = "INSERT INTO HFCMember (firstName, lastName, email, userPassword, address, city, province, postalCode, phone, dob, fitnessGoal, weightGoal, timeGoal, joinDate) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try (PreparedStatement pstmt = Main.conn.prepareStatement(insert)) {
            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.setString(3, email);
            pstmt.setString(4, userPassword);
            pstmt.setString(5, address);
            pstmt.setString(6, city);
            pstmt.setString(7, province);
            pstmt.setString(8, postalCode);
            pstmt.setInt(9, phone);
            pstmt.setDate(10, dob);
            pstmt.setString(11, fitnessGoal);
            pstmt.setDouble(12, weightGoal);
            pstmt.setString(13, timeGoal);
            pstmt.setDate(14, joinDate);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    public static boolean logInMember(String email, String password) {
        String SQL = "SELECT email, userPassword FROM HFCMember WHERE email = ?";
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

    public static void loadMemberDetails(String email){
        String SQL = "SELECT * FROM HFCMember WHERE email = ?";
        try (PreparedStatement pstmt = Main.conn.prepareStatement(SQL)) {
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int memberID = rs.getInt("memberID");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String userEmail = rs.getString("email");
                String userPassword = rs.getString("userPassword");
                String address = rs.getString("address");
                String city = rs.getString("city");
                String province = rs.getString("province");
                String postalCode = rs.getString("postalCode");
                int phone = rs.getInt("phone");
                Date dob = rs.getDate("dob");
                String fitnessGoal = rs.getString("fitnessGoal");
                double weightGoal = rs.getDouble("weightGoal");
                String timeGoal = rs.getString("timeGoal");
                Date joinDate = rs.getDate("joinDate");

                HFCMember.memberID = memberID;
                HFCMember.firstName = firstName;
                HFCMember.lastName = lastName;
                HFCMember.email = userEmail;
                HFCMember.userPassword = userPassword;
                HFCMember.address = address;
                HFCMember.city = city;
                HFCMember.province = province;
                HFCMember.postalCode = postalCode;
                HFCMember.phone = phone;
                HFCMember.DOB = dob;
                HFCMember.fitnessGoal = fitnessGoal;
                HFCMember.weightGoal = weightGoal;
                HFCMember.timeGoal = timeGoal;
                HFCMember.joinDate = joinDate;

            } else {
                System.out.println("Member with email " + email + " not found.");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static boolean updateMemberDetails(String newFirstName, String newLastName, String newEmail, String newAddress, String newCity, String newProvince, String newPostalCode, int newPhone, String newFitnessGoal, double newWeightGoal, String newTimeGoal) {
        String updateQuery = "UPDATE HFCMember SET firstName = ?, lastName = ?, email = ?, address = ?, city = ?, province = ?, postalCode = ?, phone = ?, fitnessGoal = ?, weightGoal = ?, timeGoal = ? WHERE email = ?";
        try (PreparedStatement pstmt = Main.conn.prepareStatement(updateQuery)) {
            pstmt.setString(1, newFirstName);
            pstmt.setString(2, newLastName);
            pstmt.setString(3, newEmail);
            pstmt.setString(4, newAddress);
            pstmt.setString(5, newCity);
            pstmt.setString(6, newProvince);
            pstmt.setString(7, newPostalCode);
            pstmt.setInt(8, newPhone);
            pstmt.setString(9, newFitnessGoal);
            pstmt.setDouble(10, newWeightGoal);
            pstmt.setString(11, newTimeGoal);
            pstmt.setString(12, HFCMember.email);
            pstmt.executeUpdate();
            loadMemberDetails(newEmail);
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    public static void createWorkoutRoutine(String workoutType, int sets, int reps){
        String SQL = "INSERT INTO ExerciseRoutine (MemberID, ExerciseType, Sets, Reps) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = Main.conn.prepareStatement(SQL)) {
            pstmt.setInt(1, HFCMember.memberID);
            pstmt.setString(2, workoutType);
            pstmt.setInt(3, sets);
            pstmt.setInt(4, reps);
            pstmt.executeUpdate();
        } catch (SQLException e){
            System.out.println(e);
        }
    }

    public static String displayWorkoutRoutine() {
        workoutRoutine = new StringBuilder();
        String SQL = "SELECT * FROM ExerciseRoutine WHERE memberID = ?";
        try (PreparedStatement pstmt = Main.conn.prepareStatement(SQL)) {
            pstmt.setInt(1, HFCMember.memberID);
            ResultSet rs = pstmt.executeQuery();

            int[] columnWidths = {20, 20, 25, 15};

            workoutRoutine.append(String.format("%-" + columnWidths[0] + "s %-" + columnWidths[1] + "s %-" + columnWidths[2] + "s %-" + columnWidths[3] + "s\n", "RoutineID", "Type", "Sets", "Reps"));

            while (rs.next()) {
                int routineID = rs.getInt("RoutineID");
                String exerciseType = rs.getString("ExerciseType");
                int sets = rs.getInt("Sets");
                int reps = rs.getInt("Reps");
                workoutRoutine.append(String.format("%-" + columnWidths[0] + "d %-" + columnWidths[1] + "s %-" + columnWidths[2] + "d %-" + columnWidths[3] + "d\n", routineID, exerciseType, sets, reps));
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return workoutRoutine.toString();
    }

    public static void removeWorkoutRoutine(int routineID){
        String SQL = "DELETE FROM ExerciseRoutine WHERE RoutineID = ?";
        try (PreparedStatement pstmt = Main.conn.prepareStatement(SQL)) {
            pstmt.setInt(1, routineID);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error removing workout routine: " + e.getMessage());
        }
    }

    public static String getAchievements() {
        String SQL = "SELECT Achievement FROM Achievements WHERE MemberID = ?";
        try (PreparedStatement pstmt = Main.conn.prepareStatement(SQL)) {
            pstmt.setInt(1, HFCMember.memberID);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getString("Achievement");
            } else {
                return "";
            }
        } catch (SQLException e) {
            System.out.println(e);
            return "";
        }
    }
}
