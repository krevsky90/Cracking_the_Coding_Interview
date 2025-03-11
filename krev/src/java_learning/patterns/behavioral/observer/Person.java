package java_learning.patterns.behavioral.observer;

public class Person implements ISubscriber {
    @Override
    public void update(String data) {
        System.out.println("I'm human and I've got an update!");
        System.out.println(data);
    }
}
