package java_learning.patterns.creational.prototype.guru;

//instead of Cloneable interface
public abstract class Shape {
    private int x;
    private int y;
    private String color;

    public Shape(int x, int y, String color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public Shape(Shape target) {
        this(target.x, target.y, target.color);
    }

    public abstract Shape clone();
}
