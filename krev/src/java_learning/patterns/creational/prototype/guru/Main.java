package java_learning.patterns.creational.prototype.guru;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Circle circleProto = new Circle(10, 10, "BLUE", 5);
        Rectangle rectangleProto = new Rectangle(0, 0, "RED", 4, 5);

        List<Shape> shapes = new ArrayList<>();
        shapes.add(circleProto);
        shapes.add(rectangleProto);

        List<Shape> shapesCopy = new ArrayList<>();
        for (Shape shape : shapes) {
            shapesCopy.add(shape.clone());
        }

        System.out.println("");
    }
}
