// ArraySumAverage.java
import java.util.Scanner;

public class ArraySumAverage {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of elements for the array: ");
        int size = scanner.nextInt();

        if (size <= 0) {
            System.out.println("Number of elements must be positive.");
            scanner.close();
            return;
        }

        int[] numbers = new int[size];
        int sum = 0;

        System.out.println("Enter " + size + " integers:");
        for (int i = 0; i < size; i++) {
            System.out.print("Element " + (i + 1) + ": ");
            numbers[i] = scanner.nextInt();
            sum += numbers[i];
        }

        double average = (double) sum / size;

        System.out.println("\nArray elements:");
        for (int i = 0; i < size; i++) {
            System.out.print(numbers[i] + " ");
        }
        System.out.println();

        System.out.println("Sum of elements: " + sum);
        System.out.println("Average of elements: " + average);

        scanner.close();
    }
}