package java_learning.patterns.structural.composite.using_builder;

import java_learning.patterns.structural.composite.Ball;
import java_learning.patterns.structural.composite.Container;
import java_learning.patterns.structural.composite.ICalcPrice;
import java_learning.patterns.structural.composite.Table;

public class ContainerBuilder {
    private Container container;

    public ContainerBuilder createContainer() {
        container = new Container();
        return this;
    }

    public ContainerBuilder addItem(ICalcPrice item) {
        container.add(item);
        return this;
    }

    public Container build() {
        return container;
    }
}
