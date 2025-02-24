package java_learning.patterns.creational.factory_method;

public class Bird implements Animal {
    @Override
    public void move() {
        System.out.println("Bird flies");
    }

    @Override
    public void sound() {
        System.out.println("Bird sings");
    }
}
