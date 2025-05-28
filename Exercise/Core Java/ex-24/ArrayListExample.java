// ArrayListExample.java
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ArrayListExample {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> studentNames = new ArrayList<>();

        System.out.println("Enter student names (type 'done' to finish):");

        while (true) {
            System.out.print("Enter name: ");
            String name = scanner.nextLine();

            if (name.equalsIgnoreCase("done")) {
                break;
            }
            studentNames.add(name);
        }

        System.out.println("\n--- List of Student Names ---");
        if (studentNames.isEmpty()) {
            System.out.println("No names were entered.");
        } else {
            for (int i = 0; i < studentNames.size(); i++) {
                System.out.println((i + 1) + ". " + studentNames.get(i));
            }
        }

        scanner.close();
    }
}