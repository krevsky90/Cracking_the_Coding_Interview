package java_learning.patterns.behavioral.chain_of_responsibility.notifications;

public abstract class AbstractNotifier implements Notifier {
    protected Notifier next;

    public void setNext(Notifier next) {
        this.next = next;
    }

    /**
     * NOTE:
     * here we can also remove interface and implement 'notify method here, that will call 'write' method of subclassed depending on priority level
     *
     * like
     * private priority;
     *
     * public AbstractNotifier(int priority) {
     *     this.priority = priority;
     * }
     *
     *  public void notify(String message, int level) {
     *      if (level >= priority) {
     *          write(message);  //i.e. delegate to subclass
     *      }
     *
     *      if(next != null) {
     *          next.notify(message, level);
     *      }
     *  }
     *
     *  public abstract void write(String message);
     *
     *  NOTE: here we can implement the idea of validation directly in abstract class! BUT in general case it is implementd in subclass!
     */
}
