package java_learning.patterns.behavioral.visitor;

public interface IVisitor {
    String visitPoint(Point point);
    String visitCircle(Circle circle);
    String visitRectangle(Rectangle rectangle);
    String visitContainer(Container container);

}
