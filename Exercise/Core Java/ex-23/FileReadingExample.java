// FileReadingExample.java
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileReadingExample {
    public static void main(String[] args) {
        String fileName = "output.txt"; // Assuming output.txt was created by FileWritingExample

        System.out.println("Reading content from " + fileName + ":");
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println("An error occurred while reading the file: " + e.getMessage());
            System.err.println("Please ensure '" + fileName + "' exists in the same directory.");
        }
    }
}