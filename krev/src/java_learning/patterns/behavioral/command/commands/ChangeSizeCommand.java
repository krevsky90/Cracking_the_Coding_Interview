package java_learning.patterns.behavioral.command.commands;

import java_learning.patterns.behavioral.command.History;
import java_learning.patterns.behavioral.command.Rectangle;

public class ChangeSizeCommand extends AbstractCommand {
    private final Rectangle rectangle;
    private final int newWidth;
    private final int newHeight;
    private int prevWidth;
    private int prevHeight;

    public ChangeSizeCommand(CommandNames name, History history, Rectangle rectangle, int newWidth, int newHeight) {
        super(name, history);
        this.rectangle = rectangle;
        this.newWidth = newWidth;
        this.newHeight = newHeight;
    }

    @Override
    public boolean subExecute() {
        //backup
        prevWidth = rectangle.getWidth();
        prevHeight = rectangle.getHeight();

        rectangle.setHeight(newHeight);
        rectangle.setWidth(newWidth);

        return true;
    }

    @Override
    public void undo() {
        rectangle.setWidth(prevWidth);
        rectangle.setHeight(prevHeight);
    }
}
