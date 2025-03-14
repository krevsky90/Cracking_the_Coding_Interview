package java_learning.patterns.behavioral.strategy;

public class MultiplyStrategy implements Strategy {
    @Override
    public int execute(int a, int b) {
        return a * b;
    }
}
