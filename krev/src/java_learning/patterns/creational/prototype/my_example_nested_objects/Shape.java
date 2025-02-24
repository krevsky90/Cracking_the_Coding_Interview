package java_learning.patterns.creational.prototype.my_example_nested_objects;

import java.util.UUID;

public class Shape implements Copyable {
    private String id;
    private int x;
    private int y;
    private Color color;

    public Shape(int x, int y, Color color) {
        this.id = UUID.randomUUID().toString();
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public Shape(Shape shape) {
        this(shape.x, shape.y, (Color) shape.color.clone());
    }

    public Copyable clone() {
        return new Shape(this);
    }

    @Override
    public String toString() {
        return "Shape{" +
                "id='" + id + '\'' +
                ", x=" + x +
                ", y=" + y +
                ", color=" + color +
                '}';
    }
}
