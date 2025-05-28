// StringReversal.java
import java.util.Scanner;

public class StringReversal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a string: ");
        String originalString = scanner.nextLine();

        // Method 1: Using StringBuilder (recommended for efficiency)
        StringBuilder reversedStringBuilder = new StringBuilder(originalString);
        reversedStringBuilder.reverse();
        String reversedString1 = reversedStringBuilder.toString();
        System.out.println("Reversed string (using StringBuilder): " + reversedString1);

        // Method 2: Using a loop
        String reversedString2 = "";
        for (int i = originalString.length() - 1; i >= 0; i--) {
            reversedString2 += originalString.charAt(i);
        }
        System.out.println("Reversed string (using loop): " + reversedString2);


        scanner.close();
    }
}