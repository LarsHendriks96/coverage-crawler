public class Divider {
    public Divider() {
        // Constructor
    }

    public void divide(int dividend, int divisor) throws ArithmeticException {
        if (divisor == 0) {
            throw new ArithmeticException("Division by zero is not allowed");
        }
        int result = dividend / divisor;
        System.out.println("Result of division: " + result);
    }
}