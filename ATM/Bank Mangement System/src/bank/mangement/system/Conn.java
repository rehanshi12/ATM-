
package bank.mangement.system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Conn {
    public Connection c;
    public Statement s;

    public Conn() {
        try {
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Establish the connection
            c = DriverManager.getConnection("jdbc:mysql:///bankmanagementsystem", "root", "Rehan@2004");
            
            // Create a statement object
            s = c.createStatement();
            
            System.out.println("Database connection successful.");
            
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC Driver not found: " + e);
        } catch (SQLException e) {
            System.out.println("Error connecting to the database: " + e);
        }
    }

    // Optional: Method to close the connection
    public void close() {
        try {
            if (s != null) {
                s.close();
            }
            if (c != null) {
                c.close();
            }
        } catch (SQLException e) {
            System.out.println("Error closing the connection: " + e);
        }
    }

    public static void main(String[] args) {
        // Create an instance of Conn to establish the connection
        Conn conn = new Conn();
        
        // Optionally, close the connection (if not done elsewhere in your application)
        conn.close();
    }
}
   