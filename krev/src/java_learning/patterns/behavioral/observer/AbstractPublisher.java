package java_learning.patterns.behavioral.observer;

import java.util.HashSet;
import java.util.Set;

public class AbstractPublisher implements IPublisher {
    private Set<ISubscriber> subscriberList = new HashSet<>();

    public Set<ISubscriber> getSubscriberList() {
        return subscriberList;
    }

    @Override
    public void subscribe(ISubscriber subscriber) {
        subscriberList.add(subscriber);
    }

    @Override
    public void unsubscribe(ISubscriber subscriber) {
        subscriberList.remove(subscriber);
    }
}
