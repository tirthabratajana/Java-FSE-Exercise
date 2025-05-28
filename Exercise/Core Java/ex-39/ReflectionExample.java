// ReflectionExample.java
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

class MyReflectableClass {
    public void publicMethod(String message) {
        System.out.println("Public Method called with: " + message);
    }

    private void privateMethod(int number) {
        System.out.println("Private Method called with: " + number);
    }

    public static void staticMethod(String param1, int param2) {
        System.out.println("Static Method called with: " + param1 + ", " + param2);
    }
}

public class ReflectionExample {
    public static void main(String[] args) {
        String className = "MyReflectableClass";

        try {
            // 1. Load the class dynamically
            Class<?> clazz = Class.forName(className);
            System.out.println("Class loaded: " + clazz.getName());

            // Create an instance of the class (for non-static methods)
            Object obj = clazz.getDeclaredConstructor().newInstance();

            // 2. Print method names and parameters
            System.out.println("\n--- Declared Methods ---");
            Method[] methods = clazz.getDeclaredMethods(); // Gets all methods, public, private, protected
            for (Method method : methods) {
                System.out.println("Method Name: " + method.getName() + ", Return Type: " + method.getReturnType().getName() + ", Parameters: " + Arrays.toString(method.getParameterTypes()));
            }

            // 3. Dynamically invoke a public method
            System.out.println("\n--- Invoking Methods ---");
            Method publicMethod = clazz.getDeclaredMethod("publicMethod", String.class);
            publicMethod.invoke(obj, "Hello from Reflection!");

            // Dynamically invoke a private method
            Method privateMethod = clazz.getDeclaredMethod("privateMethod", int.class);
            privateMethod.setAccessible(true); // Allow access to private method
            privateMethod.invoke(obj, 123);

            // Dynamically invoke a static method
            Method staticMethod = clazz.getDeclaredMethod("staticMethod", String.class, int.class);
            staticMethod.invoke(null, "Dynamic Call", 456); // null for static methods because no instance needed

        } catch (ClassNotFoundException e) {
            System.err.println("Class not found: " + className);
        } catch (NoSuchMethodException e) {
            System.err.println("Method not found: " + e.getMessage());
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            System.err.println("Error invoking method: " + e.getMessage());
            e.printStackTrace();
        }
    }
}