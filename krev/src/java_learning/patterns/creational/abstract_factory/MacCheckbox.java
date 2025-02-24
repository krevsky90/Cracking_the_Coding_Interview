package java_learning.patterns.creational.abstract_factory;

public class MacCheckbox implements Checkbox {
    @Override
    public void render() {
        System.out.println("render Checkbox on Mac");
    }

    @Override
    public void check() {
        System.out.println("check Checkbox on Mac");
    }
}