package java_learning.concurrency.guarded_lock.sequence_sync;

public class FirstSecondByWaitNotify {
    static Object lock = new Object();
    static boolean startSecond = false;

    public static void main(String[] args) {
//        test1_stucksDueToMissedNotification();
        test1_fixed();
    }

    /**
     * Problem:
     * The issue occurs because of a RACE CONDITION between the two threads. Here's what can happen:
     * <p>
     * If Thread second starts executing before Thread first , it will enter the while (!startSecond) loop and call lock.wait().
     * At this point, it will release the lock and wait indefinitely for a notification.
     * However, if Thread first executes completely (prints "First", sets startSecond = true, and calls lock.notify()) before Thread second enters the synchronized (lock) block,
     * the notify() call will be lost. This happens because Thread second has not yet called wait(), so the notification has no effect.
     * As a result, Thread second will remain stuck in the while (!startSecond) loop, continuously calling lock.wait() without ever being notified.
     * <p>
     * Solution:
     * To fix this issue, you need to ensure that Thread second does not miss the notification from Thread first.
     * One way to achieve this is by using a proper condition-checking mechanism inside the synchronized block.
     * Specifically, you should re-check the startSecond flag after waking up from wait() to ensure that the condition has been met.
     */
    public static void test1_stucksDueToMissedNotification() {
        Thread first = new Thread(() -> {
            synchronized (lock) {
                System.out.println("First");
                startSecond = true;
                lock.notify();
            }
        });

        Thread second = new Thread(() -> {
            //here is the problem starts!
            while (!startSecond) {
                synchronized (lock) {
                    //here is the problem ends!
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        System.err.println(Thread.currentThread().getName() + " Interrupted");
                    }
                }
            }

            System.out.println("Second");
        });

        first.start();
        second.start();
    }

    public static void test1_fixed() {
        Thread first = new Thread(() -> {
            synchronized (lock) {
                System.out.println("First");
                startSecond = true;
                lock.notify();
            }
        });

        Thread second = new Thread(() -> {
            synchronized (lock) {
                while (!startSecond) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        System.err.println(Thread.currentThread().getName() + " Interrupted");
                    }
                }
            }

            System.out.println("Second");
        });

        first.start();
        second.start();
    }
}
