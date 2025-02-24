package java_learning.patterns.creational.factory_method;

public class Dog implements Animal{
    @Override
    public void move() {
        System.out.println("Dog runs");
    }

    @Override
    public void sound() {
        System.out.println("Dog barks");

    }
}
