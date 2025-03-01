package java_learning.patterns.structural.composite;

/**
 * leaf - business entity
 */
public class Ball implements ICalcPrice {
    @Override
    public int calcPrice() {
        return 10;
    }
}
