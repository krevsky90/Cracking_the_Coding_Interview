package java_learning.patterns.behavioral.visitor;

public class Circle implements IShape {
    private int id;
    private int x;
    private int y;
    private int radius;

    public Circle() {}

    public Circle(int id, int x, int y, int radius) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.radius = radius;
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

    public int getRadius() {
        return radius;
    }

    @Override
    public String apply(IVisitor visitor) {
        return visitor.visitCircle(this);
    }


}
