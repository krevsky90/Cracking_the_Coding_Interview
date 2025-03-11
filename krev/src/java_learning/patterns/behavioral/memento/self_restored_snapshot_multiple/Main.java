package java_learning.patterns.behavioral.memento.self_restored_snapshot_multiple;

public class Main {
    public static void main(String[] args) {
        CareTaker careTaker = new CareTaker();
        careTaker.run();
        System.out.println("Restorations");
        careTaker.undo();
    }
}
