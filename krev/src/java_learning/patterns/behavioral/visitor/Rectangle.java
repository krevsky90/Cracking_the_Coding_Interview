package java_learning.patterns.behavioral.visitor;

public class Rectangle implements IShape {
    private int id;
    private int width;
    private int height;

    public Rectangle() {}

    public Rectangle(int id, int width, int height) {
        this.id = id;
        this.width = width;
        this.height = height;
    }

    //some specific actions


    public int getId() {
        return id;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public String apply(IVisitor visitor) {
        return visitor.visitRectangle(this);
    }
}
