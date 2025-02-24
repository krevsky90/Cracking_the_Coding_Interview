package java_learning.patterns.creational.prototype.my_example_nested_objects;

public class Main {
    public static void main(String[] args) {
        Copyable shape1 = new Shape(1,2,new Color("Red"));
        System.out.println(shape1);
        Copyable shape2 = shape1.clone();

        System.out.println(shape2);
    }
}
