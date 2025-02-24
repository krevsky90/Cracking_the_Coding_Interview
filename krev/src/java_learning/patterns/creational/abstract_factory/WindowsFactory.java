package java_learning.patterns.creational.abstract_factory;

public class WindowsFactory implements GUIAbstractFactory {
    @Override
    public Button createButton() {
        return new WindowsButton();
    }

    @Override
    public Popup createPopup() {
        return new WindowsPopup();
    }

    @Override
    public Checkbox createCheckbox() {
        return new WindowsCheckbox();
    }
}
