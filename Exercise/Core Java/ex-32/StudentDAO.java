// StudentDAO.java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentDAO {

    // --- Configuration for SQLite ---
    private static final String JDBC_DRIVER = "org.sqlite.JDBC";
    private static final String DB_URL = "jdbc:sqlite:test.db"; // Path to your SQLite database file

    // --- Configuration for MySQL (Uncomment and adjust if using MySQL) ---
    /*
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/your_database_name";
    private static final String USER = "your_username";
    private static final String PASS = "your_password";
    */

    public Connection getConnection() throws SQLException {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC Driver not found: " + JDBC_DRIVER);
            e.printStackTrace();
            throw new SQLException("JDBC Driver not found.", e);
        }
        // For SQLite:
        return DriverManager.getConnection(DB_URL);
        // For MySQL:
        // return DriverManager.getConnection(DB_URL, USER, PASS);
    }

    public void insertStudent(String name, int age) {
        String sql = "INSERT INTO students (name, age) VALUES (?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setInt(2, age);
            int rowsAffected = pstmt.executeUpdate();
            System.out.println(rowsAffected + " row(s) inserted: " + name);
        } catch (SQLException e) {
            System.err.println("Error inserting student: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void updateStudentAge(int id, int newAge) {
        String sql = "UPDATE students SET age = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, newAge);
            pstmt.setInt(2, id);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println(rowsAffected + " row(s) updated: Student ID " + id + " new age is " + newAge);
            } else {
                System.out.println("No student found with ID: " + id);
            }
        } catch (SQLException e) {
            System.err.println("Error updating student: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        StudentDAO dao = new StudentDAO();

        // Insert new students
        System.out.println("--- Inserting Students ---");
        dao.insertStudent("Diana", 23);
        dao.insertStudent("Ethan", 19);

        // Update student
        System.out.println("\n--- Updating Student ---");
        dao.updateStudentAge(1, 21); // Assuming ID 1 exists (Alice)
        dao.updateStudentAge(5, 25); // Update Diana (if her ID is 5, check your database)
        dao.updateStudentAge(99, 30); // Try to update a non-existent ID
    }
}