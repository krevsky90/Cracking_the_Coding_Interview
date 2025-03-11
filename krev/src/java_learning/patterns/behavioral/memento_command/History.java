package java_learning.patterns.behavioral.memento_command;

import java_learning.patterns.behavioral.memento_command.commands.ICommand;

import java.util.Stack;

public class History {
    private final Stack<ICommand> history;

    public History() {
        this.history = new Stack<>();
    }

    public void push(ICommand command) {
        history.push(command);
    }

    public ICommand popCommand() {
        return !history.isEmpty() ? history.pop() : null;
    }
}
