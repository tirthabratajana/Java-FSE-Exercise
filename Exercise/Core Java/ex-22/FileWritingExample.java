// FileWritingExample.java
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileWritingExample {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String fileName = "output.txt";

        System.out.print("Enter a string to write to " + fileName + ": ");
        String userInput = scanner.nextLine();

        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(userInput);
            System.out.println("Successfully wrote \"" + userInput + "\" to " + fileName + ".");
        } catch (IOException e) {
            System.err.println("An error occurred while writing to the file: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}