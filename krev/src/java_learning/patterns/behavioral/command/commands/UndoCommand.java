package java_learning.patterns.behavioral.command.commands;

import java_learning.patterns.behavioral.command.History;

public class UndoCommand extends AbstractCommand {
    public UndoCommand(CommandNames name, History history) {
        super(name, history);
    }

    @Override
    public boolean subExecute() {
        ICommand command = history.popCommand();
        if (command != null) {
            command.undo();
        }
        return true;
    }

    @Override
    public void undo() {
        //do nothing!
    }
}
