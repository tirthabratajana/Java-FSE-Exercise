// TypeCastingExample.java
public class TypeCastingExample {
    public static void main(String[] args) {
        // Double to int (narrowing conversion - explicit cast required)
        double myDouble = 10.99;
        int myInt = (int) myDouble; // Casting double to int
        System.out.println("Original double: " + myDouble);
        System.out.println("Casted to int: " + myInt); // Output will be 10 (decimal part truncated)

        // Int to double (widening conversion - implicit cast often occurs, but explicit is good practice)
        int anotherInt = 25;
        double anotherDouble = (double) anotherInt; // Casting int to double
        System.out.println("Original int: " + anotherInt);
        System.out.println("Casted to double: " + anotherDouble); // Output will be 25.0
    }
}