package java_learning.patterns.structural.bridge.shapes_colors.before;

public abstract class Shape {
    abstract String getShape();

    public void print() {
        System.out.println(getShape());
    }

}
