package java_learning.concurrency.object_lock;

public class ObjectLockTest {
    public static void main(String[] args) throws InterruptedException {
        Container container = new Container();

        Runnable r1 = () -> {
            for (int i = 0; i < 10; i++) {
                try {
                    container.increment1();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        Runnable r2 = () -> {
            for (int i = 0; i < 10; i++) {
                try {
                    container.increment1();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        Thread t1 = new Thread(r1);
        t1.setName("t1");
        Thread t2 = new Thread(r2);
        t2.setName("t2");

        long time = System.currentTimeMillis();

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println(container.getCounter1());
        System.out.println(container.getCounter2());

        System.out.println("Total time = " + (System.currentTimeMillis() - time));


    }
}
