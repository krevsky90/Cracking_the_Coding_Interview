package java_learning.patterns.creational.prototype.cloneable;

public class MyObject implements Cloneable {
    //if we implement Cloneable => we allow JVM to clone instance of class MyObject
    //interface Cloneable is interface-marker (it does not contain clone() method)

    //NOTE: if we override clone() method => we must write "implements Cloneable"
    public Object clone() throws CloneNotSupportedException {
        //we can use default implementation (from Object class):
        return super.clone();

        //or we can write custom implementation of clone() method (see neighbour folders)
    }
}
