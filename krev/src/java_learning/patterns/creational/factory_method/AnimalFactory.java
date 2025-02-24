package java_learning.patterns.creational.factory_method;

public abstract class AnimalFactory {
    abstract Animal createAnimal();

    //also the factory may contain methods that uses created objects
    public void sing() {
        Animal animal = createAnimal();
        animal.sound();
    }

    public void move() {
        Animal animal = createAnimal();
        animal.move();
    }


}
