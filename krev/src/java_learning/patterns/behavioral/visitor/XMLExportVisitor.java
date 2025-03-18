package java_learning.patterns.behavioral.visitor;

import java.util.List;

public class XMLExportVisitor implements IVisitor {

    public String exportXml(List<IShape> shapes) {
        StringBuilder sb = new StringBuilder();
        sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>" + "\n");
        for (IShape shape : shapes) {
            sb.append(shape.apply(this)).append("\n");
        }
        return sb.toString();
    }

    @Override
    public String visitPoint(Point point) {
        return "<dot>" + "\n" +
                "    <id>" + point.getId() + "</id>" + "\n" +
                "    <x>" + point.getX() + "</x>" + "\n" +
                "    <y>" + point.getY() + "</y>" + "\n" +
                "</dot>";
    }

    @Override
    public String visitCircle(Circle circle) {
        return "<circle>" + "\n" +
                "    <id>" + circle.getId() + "</id>" + "\n" +
                "    <x>" + circle.getX() + "</x>" + "\n" +
                "    <y>" + circle.getY() + "</y>" + "\n" +
                "    <radius>" + circle.getRadius() + "</radius>" + "\n" +
                "</circle>";
    }

    @Override
    public String visitRectangle(Rectangle rectangle) {
        return "<rectangle>" + "\n" +
                "    <id>" + rectangle.getId() + "</id>" + "\n" +
                "    <height>" + rectangle.getHeight() + "</height>" + "\n" +
                "    <width>" + rectangle.getHeight() + "</width>" + "\n" +
                "</rectangle>";
    }

    @Override
    public String visitContainer(Container container) {
        StringBuilder sb = new StringBuilder("<container>" + "\n" + "    <id>" + container.getId() + "</id>" + "\n");

        for (IShape child : container.getShapes()) {
            String tempResult = child.apply(this);;
            tempResult = "    " + tempResult.replace("\n", "\n    ") + "\n";
            sb.append(tempResult);
        }

        sb.append("</container>");
        return sb.toString();
    }


}
