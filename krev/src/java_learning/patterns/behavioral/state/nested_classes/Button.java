package java_learning.patterns.behavioral.state.nested_classes;

import java_learning.patterns.behavioral.state.player.IButton;

public class Button {
    private IButton action;

    public Button(IButton action) {
        this.action = action;
    }
    void onPress() {
        action.onPress();
    }
}
