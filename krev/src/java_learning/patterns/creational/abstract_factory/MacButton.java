package java_learning.patterns.creational.abstract_factory;

public class MacButton implements Button {
    @Override
    public void render() {
        System.out.println("render Button on Mac");
    }

    @Override
    public void click() {
        System.out.println("click Button on Mac");
    }
}