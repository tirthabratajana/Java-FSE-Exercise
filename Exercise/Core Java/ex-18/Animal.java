// Animal.java
public class Animal {
    public void makeSound() {
        System.out.println("Animal makes a sound.");
    }
}

// Dog.java (This can be in a separate file or in the same file if Animal is not public)
// For simplicity, we'll put both in the same file.
class Dog extends Animal {
    @Override
    public void makeSound() {
        System.out.println("Dog barks: Woof! Woof!");
    }

    public static void main(String[] args) {
        // Instantiate Animal class
        Animal myAnimal = new Animal();
        System.out.print("My animal: ");
        myAnimal.makeSound(); // Calls Animal's makeSound()

        // Instantiate Dog class
        Dog myDog = new Dog();
        System.out.print("My dog: ");
        myDog.makeSound(); // Calls Dog's overridden makeSound()

        // Polymorphism: Animal reference holding a Dog object
        Animal anotherAnimal = new Dog();
        System.out.print("Another animal (which is a dog): ");
        anotherAnimal.makeSound(); // Still calls Dog's makeSound() due to polymorphism
    }
}