package java_learning.patterns.behavioral.memento_command.commands;

import java_learning.patterns.behavioral.memento_command.History;
import java_learning.patterns.behavioral.memento_command.Rectangle;

public class ChangeSizeCommand extends AbstractCommand {
    private final Rectangle rectangle;
    private final int newWidth;
    private final int newHeight;

    public ChangeSizeCommand(CommandNames name, History history, Rectangle rectangle, int newWidth, int newHeight) {
        super(name, history);
        this.rectangle = rectangle;
        this.newWidth = newWidth;
        this.newHeight = newHeight;
    }

    @Override
    public boolean subExecute() {
        //backup
        saveBackup();

        rectangle.setHeight(newHeight);
        rectangle.setWidth(newWidth);

        return true;
    }

    //add method for backup
    private void saveBackup() {
        backup = rectangle.save();
    }
}
