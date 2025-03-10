package java_learning.patterns.behavioral.command.commands;

import java_learning.patterns.behavioral.command.History;

public abstract class AbstractCommand implements ICommand {
    private final CommandNames name;
    protected final History history;

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

    private void saveCommandToHistory() {
        if (CommandNames.UNDO != name) {
            history.push(this);
        }
    }
}
