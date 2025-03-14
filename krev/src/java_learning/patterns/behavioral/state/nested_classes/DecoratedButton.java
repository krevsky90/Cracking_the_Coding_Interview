package java_learning.patterns.behavioral.state.nested_classes;

public class DecoratedButton {
    private Button button;
    private Player context;

    public DecoratedButton(Button button, Player context) {
        this.button = button;
        this.context = context;
    }

    public void onPress() {
        System.out.println("Current state: " + context.getState());
        button.onPress();
    }
}
