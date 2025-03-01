package java_learning.patterns.structural.bridge.shapes_colors.before;

public class Client {
    public static void main(String[] args) {
        Shape blueBall = new BlueBall();
        blueBall.print();
    }
}
