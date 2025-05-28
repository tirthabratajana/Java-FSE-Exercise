// MyClass.java
public class MyClass {
    public int add(int a, int b) {
        int sum = a + b;
        return sum;
    }

    public void greet(String name) {
        System.out.println("Hello, " + name + "!");
    }

    public static void main(String[] args) {
        MyClass obj = new MyClass();
        int result = obj.add(5, 7);
        System.out.println("Sum: " + result);
        obj.greet("World");
    }
}