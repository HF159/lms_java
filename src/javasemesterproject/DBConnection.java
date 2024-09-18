package javasemesterproject;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnection {
    public Connection c;
    public Statement s;

    public DBConnection() {
        try {
            // Register JDBC Driver
            Class.forName("com.mysql.jdbc.Driver");
            
            // Establish connection
            c = DriverManager.getConnection("jdbc:mysql://0.0.0.0:3306/ELearningSystem", "root", "");
            
            // Check if connection is valid with a 5-second timeout
            if (c.isValid(5)) {
                System.out.println("Connection is valid and successful.");
            } else {
                System.out.println("Connection is not valid.");
            }
            
            // Create a statement object
            s = c.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Error during connection: " + e.getMessage());
        }
    }

    // Ping method to verify the connection again if needed
    public boolean pingDB() {
        try {
            // Ping the DB connection with a 5-second timeout
            return c != null && c.isValid(5);
        } catch (SQLException e) {
            System.err.println("Error pinging the database: " + e.getMessage());
            return false;
        }
    }

    public void Close() {
        try {
            if (c != null) {
                c.close();
                System.out.println("Connection closed.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
