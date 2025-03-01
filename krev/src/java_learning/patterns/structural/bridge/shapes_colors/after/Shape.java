package java_learning.patterns.structural.bridge.shapes_colors.after;

/**
 * NOTE: it also can be non-abstract!
 */
public abstract class Shape {
    private Color color;

    protected Shape(Color color) {
        this.color = color;
    }

    abstract String getShape();

    public void print() {
        System.out.println(color.getColor() + " " + getShape());
    }
}
