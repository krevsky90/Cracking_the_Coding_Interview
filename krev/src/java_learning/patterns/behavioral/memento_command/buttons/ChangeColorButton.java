package java_learning.patterns.behavioral.memento_command.buttons;


import java_learning.patterns.behavioral.memento_command.commands.ICommand;

//Invoker
public class ChangeColorButton {
    private ICommand command;

    public ChangeColorButton(ICommand command) {
        this.command = command;
    }

    public void setCommand(ICommand command) {
        this.command = command;
    }

    public void onPress() {
        command.execute();
    }
}
