package java_learning.patterns.behavioral.command.commands;

import java_learning.patterns.behavioral.command.History;
import java_learning.patterns.behavioral.command.Rectangle;

public class ChangeColorCommand extends AbstractCommand {
    private final Rectangle rectangle;
    private final String newColor;
    private String prevColor;

    public ChangeColorCommand(CommandNames name, History history, Rectangle rectangle, String newColor) {
        super(name, history);
        this.rectangle = rectangle;
        this.newColor = newColor;
    }

    @Override
    public boolean subExecute() {
        //backup
        prevColor = rectangle.getColor();

        rectangle.setColor(newColor);

        return true;
    }

    @Override
    public void undo() {
        rectangle.setColor(prevColor);
    }
}
