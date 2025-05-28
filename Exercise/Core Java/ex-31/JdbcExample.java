package ex;// JdbcExample.java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcExample {

    // --- Configuration for SQLite ---
    static final String JDBC_DRIVER = "org.sqlite.JDBC";
    static final String DB_URL = "jdbc:sqlite:test.db"; // Path to your SQLite database file

    // --- Configuration for MySQL (Uncomment and adjust if using MySQL) ---
    /*
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/your_database_name";
    static final String USER = "your_username";
    static final String PASS = "your_password";
    */

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // 1. Load the JDBC driver
            Class.forName(JDBC_DRIVER);
            System.out.println("Connecting to database...");

            // 2. Create a connection
            // For SQLite:
            conn = DriverManager.getConnection(DB_URL);
            // For MySQL:
            // conn = DriverManager.getConnection(DB_URL, USER, PASS);


            System.out.println("Creating statement...");
            stmt = conn.createStatement();

            // 3. Execute a SELECT query
            String sql = "SELECT id, name, age FROM students";
            rs = stmt.executeQuery(sql);

            // 4. Process the results
            System.out.println("\n--- Students Data ---");
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");

                System.out.println("ID: " + id + ", Name: " + name + ", Age: " + age);
            }

        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            // 5. Close resources in finally block
            try {
                if (rs != null) rs.close();
            } catch (SQLException se2) {
                // Do nothing
            }
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se2) {
                // Do nothing
            }
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
            System.out.println("\nDatabase connection closed.");
        }
    }
}