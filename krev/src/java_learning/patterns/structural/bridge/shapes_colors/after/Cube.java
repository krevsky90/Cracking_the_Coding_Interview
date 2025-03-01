package java_learning.patterns.structural.bridge.shapes_colors.after;

public class Cube extends Shape {
    public Cube(Color color) {
        super(color);
    }

    @Override
    public String getShape() {
        return "Cube";
    }
}
