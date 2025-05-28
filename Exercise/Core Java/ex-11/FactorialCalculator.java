// FactorialCalculator.java
import java.util.Scanner;

public class FactorialCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a non-negative integer: ");
        int number = scanner.nextInt();
        long factorial = 1; // Use long to handle larger factorials
        if (number < 0) {
            System.out.println("Factorial is not defined for negative numbers.");
        } else if (number == 0) {
            System.out.println("The factorial of 0 is 1.");
        } else {
            for (long i = 1; i <= number; i++) {
                factorial *= i;
            }
            System.out.println("The factorial of " + number + " is " + factorial + ".");
        }

        scanner.close();
    }
}