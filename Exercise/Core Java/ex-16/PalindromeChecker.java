// PalindromeChecker.java
import java.util.Scanner;

public class PalindromeChecker {

    public static boolean isPalindrome(String str) {
        // Remove non-alphanumeric characters and convert to lowercase
        String cleanedStr = str.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();

        // Use StringBuilder to reverse the cleaned string
        String reversedStr = new StringBuilder(cleanedStr).reverse().toString();

        // Check if the cleaned string is equal to its reversed version
        return cleanedStr.equals(reversedStr);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a string to check if it's a palindrome: ");
        String inputString = scanner.nextLine();

        if (isPalindrome(inputString)) {
            System.out.println("'" + inputString + "' is a palindrome.");
        } else {
            System.out.println("'" + inputString + "' is not a palindrome.");
        }

        scanner.close();
    }
}