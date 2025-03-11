package java_learning.patterns.behavioral.memento_command;

public class RectangleSnapshot {
    private final int width;
    private final int height;
    private final String color;
    private final String snapshotName;
    private final Rectangle originator;

    public RectangleSnapshot(Rectangle originator) {
        this.width = originator.getWidth();
        this.height = originator.getHeight();
        this.color = originator.getColor();
        this.originator = originator;
        snapshotName = width + "_" + height + "_" + color + "_" + System.currentTimeMillis();
    }

    //to restore snapshot
    public void restore() {
        originator.setWidth(width);
        originator.setHeight(height);
        originator.setColor(color);
    }

    public String getSnapshotName() {
        return snapshotName;
    }

    @Override
    public String toString() {
        return "RectangleSnapshot{" +
                "snapshotName='" + snapshotName + '\'' +
                '}';
    }
}
