package java_learning.patterns.creational.prototype.guru;

public class Circle extends Shape {
    private int radius;

    public Circle(int x, int y, String color, int radius) {
        super(x, y, color);
        this.radius = radius;
    }

    public Circle(Circle target) {
        super(target);
        this.radius = target.radius;
    }

    @Override
    public Shape clone() {
        return new Circle(this);
    }
}
