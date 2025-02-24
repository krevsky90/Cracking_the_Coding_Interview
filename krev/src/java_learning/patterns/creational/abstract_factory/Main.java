package java_learning.patterns.creational.abstract_factory;

public class Main {
    public static void main(String[] args) {
        GUIAbstractFactory factory = new WindowsFactory();
        Application application = new Application(factory);
        application.render();

        System.out.println("--------");
        application.getButton().click();
        application.getCheckbox().check();
        application.getPopup().press();
    }
}
