package java_learning.patterns.behavioral.memento.self_restored_snapshot;

import java_learning.patterns.behavioral.memento.outer.CareTaker;

public class Main {
    public static void main(String[] args) {
        CareTaker careTaker = new CareTaker();
        careTaker.run();
        System.out.println("Restorations");
        careTaker.undo();
        careTaker.undo();
        careTaker.undo();
    }
}
