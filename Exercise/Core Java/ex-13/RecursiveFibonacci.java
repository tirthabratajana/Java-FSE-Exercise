// RecursiveFibonacci.java
import java.util.Scanner;

public class RecursiveFibonacci {

    // Recursive method to calculate the nth Fibonacci number
    public long fibonacci(int n) {
        if (n <= 1) {
            return n;
        } else {
            return fibonacci(n - 1) + fibonacci(n - 2);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RecursiveFibonacci fibCalculator = new RecursiveFibonacci();

        System.out.print("Enter a positive integer n to find the nth Fibonacci number: ");
        int n = scanner.nextInt();

        if (n < 0) {
            System.out.println("Please enter a non-negative integer.");
        } else {
            long result = fibCalculator.fibonacci(n);
            System.out.println("The " + n + "th Fibonacci number is: " + result);
        }

        scanner.close();
    }
}