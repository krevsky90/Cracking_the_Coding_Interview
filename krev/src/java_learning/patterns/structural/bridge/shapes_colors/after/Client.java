package java_learning.patterns.structural.bridge.shapes_colors.after;

public class Client {
    public static void main(String[] args) {
        Color blue = new Blue();
        Shape blueBall = new Ball(blue);
        blueBall.print();
    }
}
