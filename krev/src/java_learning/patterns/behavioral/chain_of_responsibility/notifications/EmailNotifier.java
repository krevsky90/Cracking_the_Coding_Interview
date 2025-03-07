package java_learning.patterns.behavioral.chain_of_responsibility.notifications;

public class EmailNotifier extends AbstractNotifier{
    @Override
    public void notify(String message, int level) {
        if (level >= 2) {
            System.out.println("Send message '" + message + "' by email ... ");
        }

        if (next != null) {
            next.notify(message, level);
        }
    }
}
