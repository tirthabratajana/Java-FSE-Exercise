// RecordExample.java

// 1. Define a record named Person
// Records automatically provide:
// - A canonical constructor (all-args constructor)
// - Accessor methods for each component (e.g., name(), age())
// - equals(), hashCode(), and toString() implementations
public record Person(String name, int age) {
    // You can add custom constructors, methods, or even compact constructor here if needed
}

public class RecordExample {
    public static void main(String[] args) {
        // 2. Create instances of the Person record
        Person person1 = new Person("Alice", 30);
        Person person2 = new Person("Bob", 25);
        Person person3 = new Person("Charlie", 35);
        Person person4 = new Person("David", 22);

        // Print instances (toString() is automatically generated)
        System.out.println("Person 1: " + person1);
        System.out.println("Person 2: " + person2);

        // Accessing components using generated accessor methods
        System.out.println(person1.name() + " is " + person1.age() + " years old.");
        System.out.println(person2.name() + " is " + person2.age() + " years old.");

        // Records are immutable (no setters)
        // person1.age = 31; // This would be a compile-time error

        // 3. Use records in a List and filter based on age using Streams
        List<Person> people = new java.util.ArrayList<>();
        people.add(person1);
        people.add(person2);
        people.add(person3);
        people.add(person4);
        people.add(new Person("Eve", 19));
        people.add(new Person("Frank", 40));

        System.out.println("\nAll people: " + people);

        // Filter people older than 25 using Streams
        List<Person> olderPeople = people.stream()
                                         .filter(p -> p.age() > 25)
                                         .collect(java.util.stream.Collectors.toList());

        System.out.println("People older than 25: " + olderPeople);
    }
}