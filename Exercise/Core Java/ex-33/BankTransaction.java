// BankTransaction.java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BankTransaction {

    // --- Configuration for SQLite ---
    private static final String JDBC_DRIVER = "org.sqlite.JDBC";
    private static final String DB_URL = "jdbc:sqlite:test.db"; // Use the same test.db or bank.db

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
            throw new SQLException("JDBC Driver not found.", e);
        }
        // For SQLite:
        return DriverManager.getConnection(DB_URL);
        // For MySQL:
        // return DriverManager.getConnection(DB_URL, USER, PASS);
    }

    public void transferMoney(int fromAccountId, int toAccountId, double amount) {
        Connection conn = null;
        PreparedStatement debitStmt = null;
        PreparedStatement creditStmt = null;

        try {
            conn = getConnection();
            conn.setAutoCommit(false); // Start transaction

            // 1. Debit from sender's account
            String debitSql = "UPDATE accounts SET balance = balance - ? WHERE id = ?";
            debitStmt = conn.prepareStatement(debitSql);
            debitStmt.setDouble(1, amount);
            debitStmt.setInt(2, fromAccountId);
            int debitRows = debitStmt.executeUpdate();

            if (debitRows == 0) {
                System.out.println("Error: Sender account " + fromAccountId + " not found or insufficient funds.");
                conn.rollback(); // Rollback if sender not found
                System.out.println("Transaction rolled back.");
                return;
            }

            // Simulate an error or delay (uncomment to test rollback)
            // if (fromAccountId == 1) throw new SQLException("Simulated error during credit.");

            // 2. Credit to receiver's account
            String creditSql = "UPDATE accounts SET balance = balance + ? WHERE id = ?";
            creditStmt = conn.prepareStatement(creditSql);
            creditStmt.setDouble(1, amount);
            creditStmt.setInt(2, toAccountId);
            int creditRows = creditStmt.executeUpdate();

            if (creditRows == 0) {
                System.out.println("Error: Receiver account " + toAccountId + " not found.");
                conn.rollback(); // Rollback if receiver not found
                System.out.println("Transaction rolled back.");
                return;
            }

            conn.commit(); // Commit if both operations succeed
            System.out.println("Successfully transferred " + amount + " from account " + fromAccountId + " to " + toAccountId + ".");

        } catch (SQLException e) {
            System.err.println("Transaction failed: " + e.getMessage());
            try {
                if (conn != null) {
                    conn.rollback(); // Rollback on any SQL error
                    System.out.println("Transaction rolled back due to error.");
                }
            } catch (SQLException rbEx) {
                System.err.println("Error during rollback: " + rbEx.getMessage());
            }
            e.printStackTrace();
        } finally {
            try {
                if (debitStmt != null) debitStmt.close();
                if (creditStmt != null) creditStmt.close();
                if (conn != null) conn.close(); // Close connection
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        BankTransaction bank = new BankTransaction();

        // Example 1: Successful transfer
        System.out.println("Attempting transfer from Alice (ID 1) to Bob (ID 2) of 200.00");
        bank.transferMoney(1, 2, 200.00);

        // Example 2: Transfer with a non-existent receiver (should rollback)
        System.out.println("\nAttempting transfer from Bob (ID 2) to NonExistent (ID 99) of 50.00");
        bank.transferMoney(2, 99, 50.00);

        // Example 3: Transfer from a non-existent sender (should rollback)
        System.out.println("\nAttempting transfer from NonExistent (ID 3) to Alice (ID 1) of 100.00");
        bank.transferMoney(3, 1, 100.00);

        // After running, check your database balances to verify transactions.
        // You can use a SELECT query like: SELECT * FROM accounts;
    }
}