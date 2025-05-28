// Car.java
public class Car {
    // Attributes (instance variables)
    String make;
    String model;
    int year;

    // Constructor to initialize the attributes
    public Car(String make, String model, int year) {
        this.make = make;
        this.model = model;
        this.year = year;
    }

    // Method to display car information
    public void displayDetails() {
        System.out.println("Car Details:");
        System.out.println("  Make: " + make);
        System.out.println("  Model: " + model);
        System.out.println("  Year: " + year);
    }

    public static void main(String[] args) {
        // Create objects (instances) of the Car class
        Car car1 = new Car("Toyota", "Camry", 2020);
        Car car2 = new Car("Honda", "Civic", 2022);
        Car car3 = new Car("Ford", "Mustang", 2023);

        // Call the displayDetails method for each car object
        System.out.println("--- Car 1 ---");
        car1.displayDetails();

        System.out.println("\n--- Car 2 ---");
        car2.displayDetails();

        System.out.println("\n--- Car 3 ---");
        car3.displayDetails();
    }
}