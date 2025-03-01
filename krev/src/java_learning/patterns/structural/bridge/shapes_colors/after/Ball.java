package java_learning.patterns.structural.bridge.shapes_colors.after;

public class Ball extends Shape {

    public Ball(Color color) {
        super(color);
    }

    @Override
    public String getShape() {
        return "Ball";
    }
}
