package java_learning.concurrency.reentrant_lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SharedObjectWithLock {
    private int counter = 0;

    public void increment() {
        Lock lock = new ReentrantLock();
        //NOTE: lock() method should be called OUT of try block! otherwise if lock() throws exception => it will be caught by finally
        lock.lock();
        try {
            counter++;
        } finally {
            lock.unlock();
        }
    }
}
