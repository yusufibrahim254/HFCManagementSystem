package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HFCTrainer extends JFrame {
    private static int trainerID;
    private static String firstName;
    private static String lastName;
    private static String email;
    private static String specialization;
    private static String certification;
    private static int phone;

    public HFCTrainer() {
        setTitle("Trainer Page");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);

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

                        }
                    });

                    viewMembersButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {

                        }
                    });

                    availabilityTool.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {

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
        String updateQuery = "UPDATE HFCTrainer SET firstName = ?, lastName = ?, email = ?, specialization = ?, certification = ?, phone = ? WHERE email = ?";
        try (PreparedStatement pstmt = Main.conn.prepareStatement(updateQuery)) {
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
}
                    //LOG IN
                        //Start Training Session
                            //With?
                        //menubar -- Trainer Profile -- Log Out
                            //View profile
                            //add routines
                            //add achievements
                            //view health statistics
                         //record session -- staff takes this and creates billing

                        //View Your Members

                        //Adjust your Availability



