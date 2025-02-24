package java_learning.patterns.creational.factory_method;

public class BirdFactory extends AnimalFactory {
    @Override
    Animal createAnimal() {
        return new Bird();
    }
}
