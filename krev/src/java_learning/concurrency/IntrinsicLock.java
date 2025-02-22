package java_learning.concurrency;

public class IntrinsicLock {
    private static int counter1 = 0;
    private static int counter2 = 0;

    public static void main(String[] args) throws InterruptedException {
        Runnable r = () -> {
            for (int i = 0; i < 1_000; i++) {
                increment1();
                increment2();
            }
        };

        Thread t1 = new Thread(r);
        t1.setName("t1");
        Thread t2 = new Thread(r);
        t2.setName("t2");

        long time = System.currentTimeMillis();

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println(counter1);
        System.out.println(counter2);

        System.out.println("Total time = " + (System.currentTimeMillis() - time));
    }

    public static void increment1() {
        synchronized (IntrinsicLock.class) {
            counter1++;
            System.out.println("thread = " + Thread.currentThread().getName() + ": counter1 = " + counter1);
        }
    }

    public static void increment2() {
        synchronized (IntrinsicLock.class) {
            counter2++;
            System.out.println("thread = " + Thread.currentThread().getName() + ": counter2 = " + counter2);
        }
    }
}
