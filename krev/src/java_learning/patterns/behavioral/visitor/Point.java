package java_learning.patterns.behavioral.visitor;

public class Point implements IShape {
    private int id;
    private int x;
    private int y;

    public Point() {}

    public Point(int id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }

    @Override
    public String apply(IVisitor visitor) {
        return visitor.visitPoint(this);
    }

    //some specific actions

    public int getId() {
        return id;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
