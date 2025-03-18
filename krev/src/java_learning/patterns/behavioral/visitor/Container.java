package java_learning.patterns.behavioral.visitor;

import java.util.ArrayList;
import java.util.List;

public class Container implements IShape {
    private int id;
    private List<IShape> shapes;

    public Container(int id) {
        this.id = id;
        shapes = new ArrayList<>();
    }

    public void addShape(IShape shape) {
        shapes.add(shape);
    }

    public int getId() {
        return id;
    }

    public List<IShape> getShapes() {
        return shapes;
    }

    @Override
    public String apply(IVisitor visitor) {
        return visitor.visitContainer(this);
    }
}
