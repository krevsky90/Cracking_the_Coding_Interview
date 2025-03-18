package java_learning.patterns.behavioral.visitor;

import java.util.ArrayList;
import java.util.List;


/**
 * NOTE: this examples uses not only Visitor pattern. but also idea of Composite pattern
 */
public class Main {
    public static void main(String[] args) {
        Point p1 = new Point(1, 10, 20);
        Point p2 = new Point(2, 15, 25);
        Circle c1 = new Circle(3, 5, 5, 9);
        Rectangle rectangle1 = new Rectangle(4, 70, 40);
        Container container1 = new Container(5);
        container1.addShape(p2);
        container1.addShape(c1);
        container1.addShape(rectangle1);

        XMLExportVisitor xmlExportVisitor = new XMLExportVisitor();

        List<IShape> shapes = new ArrayList<>();
        shapes.add(p1);
        shapes.add(container1);

        System.out.println(xmlExportVisitor.exportXml(shapes));
    }
}
