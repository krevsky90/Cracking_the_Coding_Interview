package java_learning.patterns.structural.composite;

/**
 * leaf - business entity
 */
public class Table implements ICalcPrice {
    @Override
    public int calcPrice() {
        return 25;
    }
}
