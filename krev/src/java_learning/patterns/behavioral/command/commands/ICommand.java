package java_learning.patterns.behavioral.command.commands;

public interface ICommand {
    void execute();

    void undo();
}
