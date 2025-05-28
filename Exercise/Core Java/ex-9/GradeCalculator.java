// GradeCalculator.java
import java.util.Scanner;

public class GradeCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter marks (out of 100): ");
        int marks = scanner.nextInt();

        char grade;

        if (marks >= 90 && marks <= 100) {
            grade = 'A';
        } else if (marks >= 80 && marks <= 89) {
            grade = 'B';
        } else if (marks >= 70 && marks <= 79) {
            grade = 'C';
        } else if (marks >= 60 && marks <= 69) {
            grade = 'D';
        } else if (marks >= 0 && marks < 60) {
            grade = 'F';
        } else {
            grade = 'I'; // 'I' for Invalid marks
            System.out.println("Invalid marks entered. Please enter marks between 0 and 100.");
        }

        if (grade != 'I') {
            System.out.println("Assigned Grade: " + grade);
        }

        scanner.close();
    }
}