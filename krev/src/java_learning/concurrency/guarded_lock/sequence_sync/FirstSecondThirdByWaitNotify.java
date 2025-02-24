package java_learning.concurrency.guarded_lock.sequence_sync;

/**
 * the same logic as for FirstSecondByWaitNotify
 * NOTE: this task is given to ALL on Sberbank interview
 */
public class FirstSecondThirdByWaitNotify {
    private static Object lock = new Object();
    private static boolean secondStart = false;
    private static boolean thirdStart = false;

    public static void main(String[] args) {
        Thread first = new Thread(() -> {
            synchronized (lock) {
                System.out.println("First");
                secondStart = true;
                lock.notify();
            }
        });

        Thread second = new Thread(() -> {
            synchronized (lock) {
                while (!secondStart) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }

                System.out.println("Second");
                thirdStart = true;
                lock.notify();
            }
        });

        Thread third = new Thread(() -> {
            synchronized (lock) {
                while (!thirdStart) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }

                System.out.println("Third");
            }
        });

        first.start();
        second.start();
        third.start();
    }
}
