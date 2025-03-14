package java_learning.patterns.behavioral.state.player;

public class Button {
    private IButton action;

    public Button(IButton action) {
        this.action = action;
    }
    void onPress() {
        action.onPress();
    }
}
