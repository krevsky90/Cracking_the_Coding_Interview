package java_learning.patterns.behavioral.memento_command.buttons;

import java_learning.patterns.behavioral.memento_command.commands.ICommand;

//invoker
public class UndoButton {
    private ICommand command;

    public UndoButton(ICommand command) {
        this.command = command;
    }

    public void setCommand(ICommand command) {
        this.command = command;
    }

    public void onPress() {
        command.execute();
    }
}
