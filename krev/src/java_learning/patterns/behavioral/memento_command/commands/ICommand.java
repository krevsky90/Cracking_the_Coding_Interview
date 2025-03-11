package java_learning.patterns.behavioral.memento_command.commands;

public interface ICommand {
    void execute();

    void undo();
}
