package java_learning.oop;

import java_learning.oop.dogs.Dog;

public class Test {
    public static void main(String[] args) {
        Dog d1 = new Dog("dog1");
        //init order:
        //static block of Creature
        //non-static block of Creature
        //static block of Animal
        //non-static block of Animal
        //static block of Dog
        //non-static block of Dog
        //default constructor of Creature abstract class
        //default constructor of Animal abstract class
        //default constructor of Dog class

    }
}
