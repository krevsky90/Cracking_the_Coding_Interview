package java_learning.patterns.behavioral.chain_of_responsibility.notifications;

public interface Notifier {
    void notify(String message, int level);
}
