package java_learning.oop;

public abstract class Animal extends Creature {
    static {
        System.out.println("static block of " + Animal.class.getSimpleName());
    }

    static {
        System.out.println("non-static block of " + Animal.class.getSimpleName());
    }

    public Animal(String name) {
        super(name);
        System.out.println("default constructor of Animal abstract class");
    }
}
