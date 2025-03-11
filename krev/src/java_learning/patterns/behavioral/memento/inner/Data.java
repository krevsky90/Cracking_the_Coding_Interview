package java_learning.patterns.behavioral.memento.inner;

public class Data {
    private int x;
    private int y;
    private String s;

    public Data(int x, int y, String s) {
        this.x = x;
        this.y = y;
        this.s = s;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    @Override
    public String toString() {
        return "Data{" +
                "x=" + x +
                ", y=" + y +
                ", s='" + s + '\'' +
                '}';
    }

    public DataSnapshot save() {
        return new DataSnapshot(x, y, s);
    }

    public void restore(DataSnapshot snapshot) {
        this.x = snapshot.x;
        this.y = snapshot.y;
        this.s = snapshot.s;
    }

    public class DataSnapshot {
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
}
