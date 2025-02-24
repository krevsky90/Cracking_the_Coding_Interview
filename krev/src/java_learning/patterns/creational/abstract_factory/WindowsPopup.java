package java_learning.patterns.creational.abstract_factory;

public class WindowsPopup implements Popup {
    @Override
    public void render() {
        System.out.println("render Popup on Windows");
    }

    @Override
    public void press() {
        System.out.println("Press OK on Popup for Windows");
    }
}
