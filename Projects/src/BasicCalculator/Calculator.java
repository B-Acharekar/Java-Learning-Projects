package BasicCalculator;

public class Calculator {
    public static double calculate(double num1, double num2, String operator) {
        switch (operator) {
            case "+": return num1 + num2;
            case "-": return num1 - num2;
            case "*": return num1 * num2;
            case "/":
                if (num2 != 0) return num1 / num2;
                else throw new ArithmeticException("Cannot divide by zero");
            default:
                throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }
}
