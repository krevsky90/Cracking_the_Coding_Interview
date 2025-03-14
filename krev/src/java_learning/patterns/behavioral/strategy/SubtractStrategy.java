package java_learning.patterns.behavioral.strategy;

public class SubtractStrategy implements Strategy {
    @Override
    public int execute(int a, int b) {
        return a - b;
    }
}
