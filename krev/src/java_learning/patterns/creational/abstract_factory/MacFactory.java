package java_learning.patterns.creational.abstract_factory;

public class MacFactory implements GUIAbstractFactory {
    @Override
    public Button createButton() {
        return new MacButton();
    }

    @Override
    public Popup createPopup() {
        return new MacPopup();
    }

    @Override
    public Checkbox createCheckbox() {
        return new MacCheckbox();
    }
}
