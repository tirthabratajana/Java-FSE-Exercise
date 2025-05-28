// AgeChecker.java
import java.util.Scanner;

public class AgeChecker {

    public static void checkAge(int age) throws InvalidAgeException {
        if (age < 18) {
            throw new InvalidAgeException("Age must be 18 or older to proceed.");
        } else {
            System.out.println("Age " + age + " is valid. Welcome!");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your age: ");
        try {
            int age = scanner.nextInt();
            checkAge(age);
        } catch (InvalidAgeException e) {
            System.err.println("Caught an exception: " + e.getMessage());
        } catch (java.util.InputMismatchException e) {
            System.err.println("Invalid input. Please enter a numerical age.");
        } finally {
            scanner.close();
        }
    }
}