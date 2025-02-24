package java_learning.patterns.creational.abstract_factory;

public class WindowsButton implements Button {
    @Override
    public void render() {
        System.out.println("render Button on Windows");
    }

    @Override
    public void click() {
        System.out.println("click Button on Windows");
    }
}
