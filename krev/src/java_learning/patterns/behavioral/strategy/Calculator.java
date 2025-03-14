package java_learning.patterns.behavioral.strategy;

public class Calculator {
    private Strategy strategy;

    public Calculator(Strategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public int doOperation(int a, int b) {
        return strategy.execute(a, b);
    }

}
