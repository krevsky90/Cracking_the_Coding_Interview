package java_learning.patterns.structural.facade;

public class MobilePhone extends Product {
    public MobilePhone(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "MobilePhone{" +
                "name='" + name + '\'' +
                '}';
    }
}
