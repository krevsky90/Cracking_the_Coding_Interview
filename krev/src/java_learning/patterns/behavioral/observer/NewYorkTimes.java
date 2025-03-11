package java_learning.patterns.behavioral.observer;

public class NewYorkTimes extends AbstractPublisher {
    private int counter = 0;

    public void publish() {
        counter++;
        String info = "New York Times # " + counter;

        notify(info);
    }

    public void notify(String info) {
        for (ISubscriber subscriber : getSubscriberList()) {
            subscriber.update(info);
        }
    }
}
