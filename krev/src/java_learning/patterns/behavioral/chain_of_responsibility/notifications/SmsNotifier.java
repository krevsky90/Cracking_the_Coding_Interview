package java_learning.patterns.behavioral.chain_of_responsibility.notifications;

public class SmsNotifier extends AbstractNotifier {
    @Override
    public void notify(String message, int level) {
        if (level >= 3) {
            System.out.println("Send message '" + message + "' by sms ... ");
        }

        if (next != null) {
            next.notify(message, level);
        }
    }
}
