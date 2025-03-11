package java_learning.patterns.behavioral.memento.self_restored_snapshot;

public class DataSnapshot {
    private final int x;
    private final int y;
    private final String s;
    private final String snapshotName;
    private final Data originator;

    //or we can also propagate state of originator directly, as constructor's parameters
    public DataSnapshot(Data originator) {
        this.originator = originator;
        this.x = originator.getX();
        this.y = originator.getY();
        this.s = originator.getS();
        snapshotName = x + "_" + y + "_" + s + "_" + System.currentTimeMillis();
    }

    //no getters for state of snapshot!

    public String getSnapshotName() {
        return snapshotName;
    }

    //restore snapshot by... call from snapshot!
    public void restore() {
        originator.setX(x);
        originator.setY(y);
        originator.setS(s);
    }

    //shows ONLY name, NOT detailed information
    @Override
    public String toString() {
        return "DataSnapshot{" + snapshotName + '\'' +
                '}';
    }
}
