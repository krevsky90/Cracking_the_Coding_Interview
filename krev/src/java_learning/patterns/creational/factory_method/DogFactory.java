package java_learning.patterns.creational.factory_method;

public class DogFactory extends AnimalFactory {
    @Override
    Animal createAnimal() {
        return new Dog();
    }
}
