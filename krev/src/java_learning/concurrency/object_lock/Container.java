package java_learning.concurrency.object_lock;

/**
 * like this
 * https://docs.oracle.com/javase/tutorial/essential/concurrency/locksync.html
 */
public class Container {
    private int counter1 = 0;
    private int counter2 = 0;

    private Object lock = new Object();
    private Object lock2 = new Object();

    public void increment1() throws InterruptedException {
        synchronized (lock) {
            Thread.sleep(500);
            counter1++;
            System.out.println("thread = " + Thread.currentThread().getName() + ": counter1 = " + counter1 + ", time = " + System.currentTimeMillis());

        }
    }

    public int getCounter1() {
        synchronized (lock) {
            return counter1;
        }
    }

    public void increment2() throws InterruptedException {
        synchronized (lock2) {
            Thread.sleep(500);
            counter2++;
            System.out.println("thread = " + Thread.currentThread().getName() + ": counter2 = " + counter2 + ", time = " + System.currentTimeMillis());
        }
    }

    public int getCounter2() {
        synchronized (lock2) {
            return counter2;
        }
    }
}
