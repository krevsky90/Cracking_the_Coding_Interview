package java_learning.patterns.behavioral.memento.self_restored_snapshot_multiple;

public class RectangleSnapshot implements ISnapshot {
    private final int width;
    private final int height;
    private final Rectangle originator;
    private final String snapshotName;

    public RectangleSnapshot(Rectangle originator) {
        this.originator = originator;
        this.width = originator.getWidth();
        this.height = originator.getHeight();
        snapshotName = width + "_" + height + "_" + System.currentTimeMillis();
    }

    //no getters for state of snapshot!

    @Override
    public String getSnapshotName() {
        return snapshotName;
    }

    @Override
    public void restore() {
        originator.setWidth(width);
        originator.setHeight(height);
    }
}
