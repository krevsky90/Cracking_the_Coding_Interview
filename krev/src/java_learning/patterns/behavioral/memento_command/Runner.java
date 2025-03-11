package java_learning.patterns.behavioral.memento_command;

import java_learning.patterns.behavioral.memento_command.buttons.ChangeColorButton;
import java_learning.patterns.behavioral.memento_command.buttons.ChangeSizeButton;
import java_learning.patterns.behavioral.memento_command.buttons.UndoButton;
import java_learning.patterns.behavioral.memento_command.commands.*;

public class Runner {
    private History history = new History();

    public static void main(String[] args) {
        Runner obj = new Runner();
        obj.test();
    }

    public void test() {
        Rectangle rectangle = new Rectangle(100, 200, "Blue");
        System.out.println(rectangle);
        //
        ChangeColorCommand changeColorCommand1 = new ChangeColorCommand(CommandNames.CHANGE_COLOR, history, rectangle, "Red");
        ChangeSizeCommand changeSizeCommand1 = new ChangeSizeCommand(CommandNames.CHANGE_SIZE, history, rectangle, 150, 150);
        ChangeColorButton changeColorButton = new ChangeColorButton(changeColorCommand1);
        ChangeSizeButton changeSizeButton = new ChangeSizeButton(changeSizeCommand1);

        changeSizeButton.onPress();
        System.out.println(rectangle);
        changeColorButton.onPress();
        System.out.println(rectangle);
        //
        ChangeColorCommand changeColorCommand2 = new ChangeColorCommand(CommandNames.CHANGE_COLOR, history, rectangle, "Yellow");
        ChangeSizeCommand changeSizeCommand2 = new ChangeSizeCommand(CommandNames.CHANGE_SIZE, history, rectangle, 300, 100);
        changeSizeButton.setCommand(changeSizeCommand2);
        changeColorButton.setCommand(changeColorCommand2);

        changeSizeButton.onPress();
        System.out.println(rectangle);
        changeColorButton.onPress();
        System.out.println(rectangle);
        //
        UndoCommand undoCommand = new UndoCommand(CommandNames.UNDO, history);
        UndoButton undoButton = new UndoButton(undoCommand);

        undoButton.onPress();
        System.out.println(rectangle);
        undoButton.onPress();
        System.out.println(rectangle);
    }

    public void undo() {
        ICommand command = history.popCommand();
        command.undo();
    }
}
