package java_learning.patterns.structural.composite.using_builder;

import java_learning.patterns.structural.composite.Ball;
import java_learning.patterns.structural.composite.Container;
import java_learning.patterns.structural.composite.Table;

public class Client {
    public static void main(String[] args) {
        Container container = new ContainerBuilder()
                .createContainer()
                .addItem(new Table())
                .addItem(new Ball())
                .addItem(new ContainerBuilder()
                            .createContainer()
                        .addItem(new Ball())
                        .addItem(new Table())
                        .addItem(new Table())
                        .build()
                )
                .build();

        System.out.println(container.calcPrice());
    }
}
