// MethodOverloading.java
public class MethodOverloading {

    // Method to add two integers
    public int add(int a, int b) {
        return a + b;
    }

    // Method to add two doubles (overloaded)
    public double add(double a, double b) {
        return a + b;
    }

    // Method to add three integers (overloaded)
    public int add(int a, int b, int c) {
        return a + b + c;
    }

    public static void main(String[] args) {
        MethodOverloading calculator = new MethodOverloading();

        // Call the add method with two integers
        int sum1 = calculator.add(5, 10);
        System.out.println("Sum of two integers (5, 10): " + sum1);

        // Call the add method with two doubles
        double sum2 = calculator.add(5.5, 10.2);
        System.out.println("Sum of two doubles (5.5, 10.2): " + sum2);

        // Call the add method with three integers
        int sum3 = calculator.add(1, 2, 3);
        System.out.println("Sum of three integers (1, 2, 3): " + sum3);
    }
}