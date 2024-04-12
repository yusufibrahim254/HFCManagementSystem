package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
                // Create a new frame for sign-up
                JFrame signUpFrame = new JFrame("Sign Up");
                signUpFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Dispose the frame on close
                signUpFrame.setSize(500, 700);
                signUpFrame.setLocationRelativeTo(null);
                JPanel mainPanel = new JPanel();
                mainPanel.setLayout(new GridLayout(28, 1));

                // Create text fields for first name and last name
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

                // Create labels for text fields
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

                // Create a button to confirm sign-up
                JButton confirmButton = new JButton("Confirm");
                signUpFrame.add(confirmButton, BorderLayout.SOUTH);

                // Action listener for the confirm button
                confirmButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        if (firstNameField.getText().isEmpty() || lastNameField.getText().isEmpty() || emailField.getText().isEmpty() || passwordField.getText().isEmpty() || addressField.getText().isEmpty() || cityField.getText().isEmpty() || provinceField.getText().isEmpty() || postalCodeField.getText().isEmpty() || phoneField.getText().isEmpty() || dobField.getText().isEmpty() || fitnessGoalField.getText().isEmpty() || weightGoalField.getText().isEmpty()) {

                            JOptionPane.showMessageDialog(null, "Please fill in all required fields.", "Error", JOptionPane.ERROR_MESSAGE);
                        }

                        // Retrieve the text from the text fields
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

                        if (logInMember(email, password)){
                            logInFrame.dispose();
                            JFrame memberPage = new JFrame("Member Page");
                            memberPage.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Dispose the frame on close
                            memberPage.setSize(500, 400);
                            memberPage.setLocationRelativeTo(null);
                            JPanel dashboard = new JPanel();
                            dashboard.setLayout(new GridLayout(28, 1));
                            updateMemberDetails(email);

                            JLabel memberIDLabel = new JLabel("Member ID: " + memberID);
                            JLabel firstNameLabel = new JLabel("First Name: " + firstName);
                            JLabel lastNameLabel = new JLabel("Last Name: " + lastName);
                            JLabel emailLabel = new JLabel("Email: " + email);
                            JLabel addressLabel = new JLabel("Address: " + address);
                            JLabel cityLabel = new JLabel("City: " + city);
                            JLabel provinceLabel = new JLabel("Province: " + province);
                            JLabel postalCodeLabel = new JLabel("Postal Code: " + postalCode);
                            JLabel phoneLabel = new JLabel("Phone: " + phone);
                            JLabel dobLabel = new JLabel("Date of Birth: " + DOB);
                            JLabel fitnessGoalLabel = new JLabel("Fitness Goal: " + fitnessGoal);
                            JLabel weightGoalLabel = new JLabel("Weight Goal: " + weightGoal);
                            JLabel timeGoalLabel = new JLabel("Time Goal: " + timeGoal);
                            JLabel joinDateLabel = new JLabel("Join Date: " + joinDate);

                            dashboard.add(memberIDLabel);
                            dashboard.add(firstNameLabel);
                            dashboard.add(lastNameLabel);
                            dashboard.add(emailLabel);
                            dashboard.add(addressLabel);
                            dashboard.add(cityLabel);
                            dashboard.add(provinceLabel);
                            dashboard.add(postalCodeLabel);
                            dashboard.add(phoneLabel);
                            dashboard.add(dobLabel);
                            dashboard.add(fitnessGoalLabel);
                            dashboard.add(weightGoalLabel);
                            dashboard.add(timeGoalLabel);
                            dashboard.add(joinDateLabel);

                            memberPage.add(dashboard);
                            memberPage.setVisible(true);
                        } else {
                            System.out.println("DHIPUSjHOIUHDSIOUHSOIUHDSIOUHSDIOUHSDIOHDSIOUHDSOIHUDS");
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

    public void updateMemberDetails(String email){
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

                // Display or use the retrieved member details as needed
            } else {
                System.out.println("Member with email " + email + " not found.");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

}
