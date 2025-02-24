package java_learning.patterns.creational.abstract_factory;

public class Application {
    private final Button button;
    private final Checkbox checkbox;
    private final Popup popup;

    public Application(GUIAbstractFactory factory) {
        this.button = factory.createButton();
        this.checkbox = factory.createCheckbox();
        this.popup = factory.createPopup();
    }

    public void render() {
        button.render();
        checkbox.render();
        popup.render();
    }

    public Button getButton() {
        return button;
    }

    public Checkbox getCheckbox() {
        return checkbox;
    }

    public Popup getPopup() {
        return popup;
    }
}
