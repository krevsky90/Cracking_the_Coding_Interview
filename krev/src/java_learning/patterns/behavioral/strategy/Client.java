package java_learning.patterns.behavioral.strategy;

public class Client {
    public static void main(String[] args) {
        Calculator calculator = new Calculator(new SumStrategy());
        System.out.println(calculator.doOperation(3, 6));
        calculator.setStrategy(new MultiplyStrategy());
        System.out.println(calculator.doOperation(3, 6));
    }
}
