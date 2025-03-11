package java_learning.patterns.behavioral.memento_command.commands;

import java_learning.patterns.behavioral.memento_command.History;
import java_learning.patterns.behavioral.memento_command.Rectangle;

public class ChangeColorCommand extends AbstractCommand {
    private final Rectangle rectangle;
    private final String newColor;

    public ChangeColorCommand(CommandNames name, History history, Rectangle rectangle, String newColor) {
        super(name, history);
        this.rectangle = rectangle;
        this.newColor = newColor;
    }

    @Override
    public boolean subExecute() {
        //backup
        saveBackup();

        rectangle.setColor(newColor);

        return true;
    }

    //add method for backup
    private void saveBackup() {
        backup = rectangle.save();
    }
}
