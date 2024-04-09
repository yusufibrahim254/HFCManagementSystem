package org.example;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    private static Connection conn;



    public static void main(String[] args) {
// JDBC & Database credentials
        String url = "jdbc:postgresql://localhost:5432/HFC-Management-System";
        String user = "postgres";
        String password = "Yusuf1234";
        try { // Load PostgreSQL JDBC Driver
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(url, user, password);
            if (conn != null) {
                System.out.println("Connected to PostgreSQL successfully!");
                Date date = Date.valueOf("2024-09-08");
                //getAllStudents();
                //addStudent("Yusuf", "Ibrahim", "yusufibrahim3@cmail.carleton.ca", date); // Create operation
                //         updateStudentEmail(3, "JimsNewEmail@gmail.com"); // Update operation
               // deleteStudent(6); // Delete operation

            } else {
                System.out.println("Failed to establish connection.");
            } // Close the connection (in a real scenario, do this in a finally
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        Homepage gui = new Homepage();
        gui.setVisible(true);
    }
}