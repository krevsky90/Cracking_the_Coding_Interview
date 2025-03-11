package java_learning.patterns.behavioral.memento.outer;

public class DataSnapshot implements ISnapshot {
    private final int x;
    private final int y;
    private final String s;
    private final String snapshotName;

    public DataSnapshot(int x, int y, String s) {
        this.x = x;
        this.y = y;
        this.s = s;
        snapshotName = x + "_" + y + "_" + s + "_" + System.currentTimeMillis();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getS() {
        return s;
    }

    @Override
    public String getSnapshotName() {
        return snapshotName;
    }

    //shows ONLY name, NOT detailed information
    @Override
    public String toString() {
        return "DataSnapshot{" + snapshotName + '\'' +
                '}';
    }
}
