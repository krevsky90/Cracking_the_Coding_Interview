package java_learning.patterns.behavioral.memento.self_restored_snapshot_multiple;

public class Dot implements IData {
    private int x;
    private int y;
    private String s;

    public Dot(int x, int y, String s) {
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
        return "Dot{" +
                "x=" + x +
                ", y=" + y +
                ", s='" + s + '\'' +
                '}';
    }

    public ISnapshot save() {
        return new DotSnapshot(this);
    }


}
