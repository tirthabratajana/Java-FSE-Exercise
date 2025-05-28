// StreamFilterExample.java
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamFilterExample {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        System.out.println("Original list: " + numbers);

        // Use Stream API to filter even numbers
        List<Integer> evenNumbers = numbers.stream()  // Create a stream from the list
                                           .filter(n -> n % 2 == 0) // Filter elements where n is even
                                           .collect(Collectors.toList()); // Collect the filtered elements into a new List

        System.out.println("Even numbers (using Stream API): " + evenNumbers);

        // Another example: Filter and multiply by 2, then print
        System.out.println("\nEven numbers multiplied by 2:");
        numbers.stream()
               .filter(n -> n % 2 == 0)
               .map(n -> n * 2) // Transform the even numbers
               .forEach(System.out::println); // Print each transformed number
    }
}