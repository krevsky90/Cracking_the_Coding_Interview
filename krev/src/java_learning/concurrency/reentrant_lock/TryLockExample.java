package java_learning.concurrency.reentrant_lock;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TryLockExample {
    private static int counter = 0;

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();

        Runnable r = () -> {
            boolean hasLock = false;
            try {
                hasLock = lock.tryLock(ThreadLocalRandom.current().nextInt(200), TimeUnit.MILLISECONDS); //NOTE: use tryLock with timeout, just for fun
            } catch (InterruptedException e) {
                //InterruptedException – if the current thread is interrupted while acquiring the lock (and interruption of lock acquisition is supported)
                Thread.currentThread().interrupt();
            }

            if (hasLock) {
                try {
                    //critical section
                    System.out.println(Thread.currentThread().getName() + " acquires lock and will change counter");
                    counter++;
                    System.out.println(Thread.currentThread().getName() + ": counter = " + counter);
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                } finally {
                    lock.unlock();
                }

            } else {
                System.out.println(Thread.currentThread().getName() + ": can't get lock");
            }

            //if current thread unable to get lock => it will continue doing smth else
            System.out.println(Thread.currentThread().getName() + " executing smth else");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        Thread t1 = new Thread(r, "t1");
        Thread t2 = new Thread(r, "t2");

        t1.start();
        t2.start();
    }
}
