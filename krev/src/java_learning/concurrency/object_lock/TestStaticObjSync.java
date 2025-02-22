package java_learning.concurrency.object_lock;

public class TestStaticObjSync {
    public static void main(String[] args) throws InterruptedException {
        ObjSync obj = new ObjSync(1);

        Runnable r = () -> {
            for (int i = 0; i < 5; i++) {
                ObjSync.methodStatic();
            }
        };

        Thread t1 = new Thread(r);
        t1.setName("t1");
        Thread t2 = new Thread(r);
        t2.setName("t2");

        long startTime = System.currentTimeMillis();

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println(System.currentTimeMillis() - startTime);
    }
}
