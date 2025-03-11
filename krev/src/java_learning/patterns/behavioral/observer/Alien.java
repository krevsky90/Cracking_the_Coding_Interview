package java_learning.patterns.behavioral.observer;

public class Alien implements ISubscriber {
    @Override
    public void update(String data) {
        System.out.println("I'm Alien and I've got an update!");
        System.out.println(data);
    }
}
