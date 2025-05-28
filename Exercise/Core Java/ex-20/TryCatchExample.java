// TryCatchExample.java
import java.util.InputMismatchException;
import java.util.Scanner;

public class TryCatchExample {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Enter the first integer (numerator): ");
            int numerator = scanner.nextInt();

            System.out.print("Enter the second integer (denominator): ");
            int denominator = scanner.nextInt();

            // Attempt division
            int result = numerator / denominator;
            System.out.println("Result of division: " + result);

        } catch (ArithmeticException e) {
            // Catch division by zero exception
            System.err.println("Error: Cannot divide by zero. Please enter a non-zero denominator.");
            // e.printStackTrace(); // Uncomment for full stack trace for debugging
        } catch (InputMismatchException e) {
            // Catch if user enters non-integer input
            System.err.println("Error: Invalid input. Please enter integers only.");
        } finally {
            // This block always executes, regardless of whether an exception occurred or not
            scanner.close();
            System.out.println("Scanner closed.");
        }
    }
}