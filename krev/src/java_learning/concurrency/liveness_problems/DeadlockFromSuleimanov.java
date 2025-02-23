package java_learning.concurrency.liveness_problems;

/**
 * info: https://github.com/proselytear/javaconcurrency/blob/main/src/main/java/net/proselyte/concurrency/synchronization
 *
 * idea is similar to DeadlockBankAccount:
 * i.e. nested synch blocks each of them requires different objects
 */
public class DeadlockFromSuleimanov {
    static class SyncThread implements Runnable {
        private final Object lock1;
        private final Object lock2;

        public SyncThread(Object o1, Object o2) {
            this.lock1 = o1;
            this.lock2 = o2;
        }

        @Override
        public void run() {
            String name = Thread.currentThread().getName();
            System.out.println("Thread: " + name + " acquiring lock on " + lock1);
            synchronized (lock1) {
                System.out.println(name + " acquired lock on " + lock1);
                work();
                System.out.println(name + " acquiring lock on " + lock2);
                synchronized (lock2) {
                    System.out.println(name + " acquired lock on " + lock2);
                    work();
                }
                System.out.println(name + " released lock on " + lock2);
            }
            System.out.println(name + " released lock on " + lock1);
            System.out.println(name + " finished execution.");
        }

        private void work() {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class MyLock {
        String name;

        public MyLock(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "[" + name + "]";
        }
    }

    public static void main(String[] args) {
        MyLock obj1 = new MyLock("lock1");
        MyLock obj2 = new MyLock("lock2");
        MyLock obj3 = new MyLock("lock3");

        Thread t1 = new Thread(new SyncThread(obj1, obj2), "t1");
        Thread t2 = new Thread(new SyncThread(obj2, obj3), "t2");
        Thread t3 = new Thread(new SyncThread(obj3, obj1), "t3");

        t1.start();
        try {
            Thread.sleep(1000);
            t2.start();
            Thread.sleep(1000);
            t3.start();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


}
