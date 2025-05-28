// HashMapExample.java
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class HashMapExample {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<Integer, String> studentMap = new HashMap<>();

        System.out.println("--- Add Student IDs and Names ---");
        while (true) {
            System.out.print("Enter Student ID (e.g., 101) or 0 to finish adding: ");
            int id = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (id == 0) {
                break;
            }

            System.out.print("Enter Student Name for ID " + id + ": ");
            String name = scanner.nextLine();
            studentMap.put(id, name);
            System.out.println("Added: ID " + id + " -> " + name);
        }

        System.out.println("\n--- Retrieve Student Name by ID ---");
        System.out.print("Enter Student ID to retrieve name (or -1 to exit): ");
        while (scanner.hasNextInt()) {
            int searchId = scanner.nextInt();
            if (searchId == -1) {
                break;
            }
            scanner.nextLine(); // Consume newline

            if (studentMap.containsKey(searchId)) {
                String studentName = studentMap.get(searchId);
                System.out.println("Student ID " + searchId + " corresponds to: " + studentName);
            } else {
                System.out.println("No student found with ID: " + searchId);
            }
            System.out.print("Enter Student ID to retrieve name (or -1 to exit): ");
        }

        scanner.close();
    }
}