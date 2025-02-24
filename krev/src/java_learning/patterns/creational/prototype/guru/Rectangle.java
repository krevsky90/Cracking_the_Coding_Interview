package java_learning.patterns.creational.prototype.guru;

public class Rectangle extends Shape {
    private int width;
    private int height;

    public Rectangle(int x, int y, String color, int width, int height) {
        super(x, y, color);
        this.width = width;
        this.height = height;
    }

    public Rectangle(Rectangle target) {
        super(target);
        this.width = target.width;
        this.height = target.height;
    }

    @Override
    public Shape clone() {
       return new Rectangle(this);
    }
}
