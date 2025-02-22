package java_learning.concurrency.object_lock;

public class ObjSync {
    private int id;
    private Object mutex = new Object();
    private static Object mutexStatic = new Object();

    public ObjSync(int id) {
        this.id = id;
    }

    public void method() {
        synchronized (mutexStatic) {
            try {
                System.out.println("Thread " + Thread.currentThread() + " uses method() in object with id = " + id);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void methodStatic() {
        synchronized (ObjSync.class) {
            try {
                System.out.println("Thread " + Thread.currentThread() + " uses methodStatic()");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
