package java_learning.patterns.behavioral.command.buttons;

import java_learning.patterns.behavioral.command.commands.ICommand;

//Invoker
public class ChangeSizeButton {
    private ICommand command;

    public ChangeSizeButton(ICommand command) {
        this.command = command;
    }

    public void setCommand(ICommand command) {
        this.command = command;
    }

    public void onPress() {
        command.execute();
    }
}
