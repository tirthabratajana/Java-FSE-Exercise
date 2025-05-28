// PatternMatchingSwitch.java

public class PatternMatchingSwitch {

    public static void describeObject(Object obj) {
        String description = switch (obj) {
            case Integer i -> "This is an Integer with value: " + i;
            case String s -> "This is a String with length: " + s.length();
            case Double d -> "This is a Double with value: " + d;
            case null -> "This object is null."; // Java 17+ for null handling in switch
            default -> "This is an object of unknown type: " + obj.getClass().getName();
        };
        System.out.println(description);
    }

    public static void main(String[] args) {
        System.out.println("Demonstrating Pattern Matching for Switch (Java 21):");

        describeObject(100);
        describeObject("Hello Java 21!");
        describeObject(3.14159);
        describeObject(true); // Boolean
        describeObject(new java.util.ArrayList<>()); // ArrayList
        describeObject(null);
    }
}