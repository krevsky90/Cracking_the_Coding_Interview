package java_learning.concurrency.reentrant_lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Similar to wait/notify, but there may be multiple conditions
 */
public class LockWithCondition {
    static boolean startSecond = false;

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        Condition startSecondCondition = lock.newCondition();

        Thread t1 = new Thread(() -> {
            lock.lock();
            try {
                System.out.println("First");
                startSecond = true;
                startSecondCondition.signalAll();
            } finally {
                lock.unlock();
            }
        });

        Thread t2 = new Thread(() -> {
            lock.lock();
            try {
                //NOTE: if we remove this loop, there might be the case when
                //startSecondCondition.signalAll(); will be done before startSecondCondition.await();
                //in this case signal will be lost! And t2 will never be awakened!
                while (!startSecond) {
                    startSecondCondition.await();
                }

                System.out.println("Second");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
        });

        t1.start();
        t2.start();
    }
}
