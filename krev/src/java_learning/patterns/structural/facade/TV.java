package java_learning.patterns.structural.facade;

public class TV extends Product {
    public TV(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "TV{" +
                "name='" + name + '\'' +
                '}';
    }
}
