package java_learning.patterns.behavioral.memento.self_restored_snapshot_multiple;

import java.util.ArrayList;
import java.util.List;

public class CareTaker {
    private Dot dot;
    private Rectangle rectangle;
    List<ISnapshot> list = new ArrayList<>();

    public CareTaker() {
    }

    public void run() {
        IData dot = new Dot(3, 6, "color");
        IData rectangle = new Rectangle(100, 400);

        System.out.println(dot);
        System.out.println(rectangle);

        list.add(dot.save());
        list.add(rectangle.save());


        //BUT we can't change data from this class! since we interact with dot and rectangle through IData
        // => do not understand profit of this approach!
    }

    public void undo() {
        for (ISnapshot snapshot : list) {
            System.out.println("Restoring snapshot: " + snapshot.getSnapshotName());
            snapshot.restore();
        }
    }
}
