package java_learning.patterns.creational.abstract_factory;

public class WindowsCheckbox implements Checkbox {
    @Override
    public void render() {
        System.out.println("render Checkbox on Windows");
    }

    @Override
    public void check() {
        System.out.println("check Checkbox on Windows");
    }
}
