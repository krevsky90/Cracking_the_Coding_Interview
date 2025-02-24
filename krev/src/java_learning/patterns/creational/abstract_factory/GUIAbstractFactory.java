package java_learning.patterns.creational.abstract_factory;

public interface GUIAbstractFactory {
    Button createButton();
    Popup createPopup();
    Checkbox createCheckbox();

}
