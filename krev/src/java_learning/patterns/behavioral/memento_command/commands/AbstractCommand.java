package java_learning.patterns.behavioral.memento_command.commands;

import java_learning.patterns.behavioral.memento_command.History;
import java_learning.patterns.behavioral.memento_command.RectangleSnapshot;

//CareTaker (in Memento pattern) - requests originator to create snapshot
public abstract class AbstractCommand implements ICommand {
    private final CommandNames name;
    protected final History history;
    protected RectangleSnapshot backup;

    public AbstractCommand(CommandNames name, History history) {
        this.name = name;
        this.history = history;
    }

    //small optimization - to write common part of all execute methods in abstract class
    public void execute() {
        if (subExecute()) {
            saveCommandToHistory();
        }
    }

    public abstract boolean subExecute();

    //since snapshot is stored in one place => we can restore it by common method of Abstract class
    @Override
    public void undo() {
        backup.restore();
    }

    private void saveCommandToHistory() {
        if (CommandNames.UNDO != name) {
            history.push(this);
        }
    }
}
