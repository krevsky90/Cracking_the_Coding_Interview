package java_learning.patterns.behavioral.chain_of_responsibility.notifications;

public class ReportNotifier extends AbstractNotifier {
    @Override
    public void notify(String message, int level) {
        if (level >= 1) {
            System.out.println("Send message '" + message + "' by weekly report ... ");
        }

        if (next != null) {
            next.notify(message, level);
        }
    }
}
