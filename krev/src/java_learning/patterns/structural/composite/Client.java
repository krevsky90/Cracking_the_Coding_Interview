package java_learning.patterns.structural.composite;

import java.util.Arrays;

public class Client {
    public static void main(String[] args) {
        Container container = new Container();
        container.add(new Ball());
        container.add(new Container(Arrays.asList(new Ball(), new Table(), new Table())));

        System.out.println(container.calcPrice());
    }
}
