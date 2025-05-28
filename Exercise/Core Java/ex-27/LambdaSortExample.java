// LambdaSortExample.java
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LambdaSortExample {
    public static void main(String[] args) {
        List<String> names = new ArrayList<>();
        names.add("Alice");
        names.add("Charlie");
        names.add("Bob");
        names.add("David");
        names.add("Eve");

        System.out.println("Original list: " + names);

        // Sort the list using Collections.sort() with a lambda expression
        // The lambda (s1, s2) -> s1.compareTo(s2) provides the custom comparison logic.
        // It's a concise way to implement the Comparator functional interface.
        Collections.sort(names, (s1, s2) -> s1.compareTo(s2));

        System.out.println("Sorted list (ascending): " + names);

        // Example: Sort in descending order using another lambda
        Collections.sort(names, (s1, s2) -> s2.compareTo(s1));
        System.out.println("Sorted list (descending): " + names);
    }
}