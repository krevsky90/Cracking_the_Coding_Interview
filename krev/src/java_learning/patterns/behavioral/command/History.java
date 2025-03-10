package java_learning.patterns.behavioral.command;

import java_learning.patterns.behavioral.command.commands.ICommand;

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
