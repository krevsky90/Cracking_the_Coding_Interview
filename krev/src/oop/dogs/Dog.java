package oop.dogs;

import oop.Animal;

public class Dog extends Animal {
    static {
        System.out.println("static block of " + Dog.class.getSimpleName());
    }

    static {
        System.out.println("non-static block of " + Dog.class.getSimpleName());
    }

    @Override
    protected void voice() {
        System.out.println("Dog is barking");
    }

    public Dog(String name) {
        super(name);
        System.out.println("default constructor of Dog class");
    }
}
