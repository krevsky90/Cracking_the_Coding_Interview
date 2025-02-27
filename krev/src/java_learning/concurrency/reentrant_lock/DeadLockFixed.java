package java_learning.concurrency.reentrant_lock;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Based on locks (and tryLock() method)
 * info: https://docs.oracle.com/javase/tutorial/essential/concurrency/newlocks.html
 */
public class DeadLockFixed {
    static class Account {
        double balance;
        final int id;
        final ReentrantLock lock;

        public Account(int id, double balance) {
            this.balance = balance;
            this.id = id;
            this.lock = new ReentrantLock();
        }

        void withdraw(double amount) {
            balance -= amount;
        }

        void deposit(double amount) {
            balance += amount;
        }
    }

    private static boolean canTakeBothLock(Account a, Account b) {
        boolean aLock = false;
        boolean bLock = false;
        try {
            aLock = a.lock.tryLock();
            bLock = b.lock.tryLock();
        } finally {
            //if we can't take both locks -> release
            if (!(aLock && bLock)) {
                if (aLock) {
                    a.lock.unlock();
                }

                if (bLock) {
                    b.lock.unlock();
                }
            }
        }

        return aLock && bLock;
    }

    public static void main(String[] args) throws InterruptedException {
        final Account a = new Account(1, 1000);
        final Account b = new Account(2, 300);
        Thread t1 = new Thread() {
            public void run() {
                for (int i = 0; i < 1000000; i++) {
                    transferDeadlock(a, b, 200);
                    try {
                        Thread.sleep(ThreadLocalRandom.current().nextInt(1));
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        };
        Thread t2 = new Thread() {
            public void run() {
                for (int i = 0; i < 1000000; i++) {
                    transferDeadlock(b, a, 300);
                    try {
                        Thread.sleep(ThreadLocalRandom.current().nextInt(1));
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        };
        t1.start();
        t2.start();

        t1.join();
        t2.join();
        System.out.println("FINISH");
    }

    public static void transferDeadlock(Account from, Account to, double amount) {
        if (canTakeBothLock(from, to)) {
            try {
                System.out.println(Thread.currentThread().getName() + ": transfer from " + from.id + " to " + to.id);
                from.withdraw(amount);
                to.deposit(amount);
            } finally {
                //release both locks:
                to.lock.unlock();
                from.lock.unlock();
            }
        }
    }
}
