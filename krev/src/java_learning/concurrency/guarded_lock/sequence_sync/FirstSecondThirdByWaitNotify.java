package java_learning.concurrency.guarded_lock.sequence_sync;

/**
 * the same logic as for FirstSecondByWaitNotify
 * NOTE: this task is given to ALL on Sberbank interview
 */
public class FirstSecondThirdByWaitNotify {
    private static Object lock = new Object();
    private static int currentActiveThread = 1;

    public static void main(String[] args) {
        Thread first = new Thread(() -> {
            synchronized (lock) {
                System.out.println("First");
                currentActiveThread = 2;
                lock.notify();
            }
        });

        Thread second = new Thread(() -> {
            synchronized (lock) {
                while (currentActiveThread != 2) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }

                System.out.println("Second");
                currentActiveThread = 3;
                lock.notify();
            }
        });

        Thread third = new Thread(() -> {
            synchronized (lock) {
                while (currentActiveThread != 3) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }

                System.out.println("Third");
                lock.notifyAll(); // Notify all waiting threads (optional here)
            }
        });

        first.start();
        second.start();
        third.start();
    }
}
