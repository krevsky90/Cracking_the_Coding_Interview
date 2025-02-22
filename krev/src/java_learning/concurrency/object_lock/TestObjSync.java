package java_learning.concurrency.object_lock;

public class TestObjSync {
    public static void main(String[] args) throws InterruptedException {
        ObjSync obj = new ObjSync(1);
        ObjSync obj2 =  new ObjSync(2);

        Runnable r = () -> {
            for (int i = 0; i < 5; i++) {
                obj.method();
            }
        };

        Runnable r2 = () -> {
            for (int i = 0; i < 5; i++) {
                obj2.method();
            }
        };

        Thread t1 = new Thread(r);
        t1.setName("t1");
        Thread t2 = new Thread(r2);
        t2.setName("t2");

        long startTime = System.currentTimeMillis();

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println(System.currentTimeMillis() - startTime);
    }
}
