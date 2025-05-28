// OperatorPrecedence.java
public class OperatorPrecedence {
    public static void main(String[] args) {
        int result1 = 10 + 5 * 2;
        // Explanation: Multiplication (*) has higher precedence than addition (+).
        // So, 5 * 2 is evaluated first (10), then 10 + 10 is evaluated.
        System.out.println("Expression: 10 + 5 * 2");
        System.out.println("Result: " + result1); // Expected: 20
        System.out.println("Explanation: Multiplication (5 * 2) is performed first, then addition.\n");

        int result2 = (10 + 5) * 2;
        // Explanation: Parentheses () override default precedence.
        // So, 10 + 5 is evaluated first (15), then 15 * 2 is evaluated.
        System.out.println("Expression: (10 + 5) * 2");
        System.out.println("Result: " + result2); // Expected: 30
        System.out.println("Explanation: Parentheses force addition (10 + 5) to be performed first, then multiplication.\n");

        int result3 = 20 / 4 - 2 + 3 * 2;
        // Explanation: Division (/) and Multiplication (*) have higher precedence than Subtraction (-) and Addition (+).
        // They are evaluated from left to right.
        // 20 / 4 = 5
        // 3 * 2 = 6
        // Then: 5 - 2 + 6
        // 5 - 2 = 3
        // 3 + 6 = 9
        System.out.println("Expression: 20 / 4 - 2 + 3 * 2");
        System.out.println("Result: " + result3); // Expected: 9
        System.out.println("Explanation: Division (20 / 4) and Multiplication (3 * 2) are performed first (left to right), then subtraction and addition (left to right).\n");

        boolean result4 = 5 > 3 && 8 < 10;
        // Explanation: Logical AND (&&) has higher precedence than Logical OR (||).
        // Both conditions are evaluated: 5 > 3 is true, 8 < 10 is true.
        // true && true is true.
        System.out.println("Expression: 5 > 3 && 8 < 10");
        System.out.println("Result: " + result4); // Expected: true
        System.out.println("Explanation: Relational operators (>, <) are evaluated, then the logical AND (&&).\n");
    }
}