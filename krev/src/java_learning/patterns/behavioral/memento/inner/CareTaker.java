package java_learning.patterns.behavioral.memento.inner;

import java.util.Stack;

public class CareTaker {
    private Data originator;
    private Stack<Data.DataSnapshot> history;

    public CareTaker() {
        this.history = new Stack<>();
    }

    public void run() {
        originator = new Data(3, 6, "color");

        System.out.println(originator);
        history.add(originator.save());

        //change data
        originator.setS("new color");
        originator.setX(30);

        System.out.println(originator);
        history.add(originator.save());

        //change data
        originator.setS("new 2 color");
        originator.setY(60);

        System.out.println(originator);
        history.add(originator.save());


    }

    public void undo() {
        if (!history.isEmpty()) {
            Data.DataSnapshot tempSnapshot = history.pop();
            System.out.println("Restoring snapshot: " + tempSnapshot);
            originator.restore(tempSnapshot);
            System.out.println(originator);
        }
    }
}
