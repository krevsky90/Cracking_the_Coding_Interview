package java_learning.patterns.creational.prototype.my_example_nested_objects;

import java.util.UUID;

public class Color implements Copyable {
    private final String id;
    private final String name;

    public Color(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
    }

    public Color(Color color) {
        this(color.name);
    }

    @Override
    public Copyable clone() {
        return new Color(this);
    }

    @Override
    public String toString() {
        return "Color{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
