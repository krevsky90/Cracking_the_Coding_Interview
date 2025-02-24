package java_learning.patterns.creational.factory_method;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        AnimalFactory factory = createFactoryByType("dog");
        Animal animal = factory.createAnimal();
        animal.move();
        animal.sound();

        //or call undirectly
        factory.sing();
        factory.move();
    }

    private static AnimalFactory createFactoryByType(String type) {
        if ("dog".equals(type)) {
            return new DogFactory();
        } else if ("bird".equals(type)) {
            return new BirdFactory();
        } else {
            throw new IllegalArgumentException(type + " is unknown");
        }
    }
}
