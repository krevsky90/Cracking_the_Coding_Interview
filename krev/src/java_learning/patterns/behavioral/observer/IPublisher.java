package java_learning.patterns.behavioral.observer;

public interface IPublisher {
    void subscribe(ISubscriber subscriber);

    void unsubscribe(ISubscriber subscriber);
}
