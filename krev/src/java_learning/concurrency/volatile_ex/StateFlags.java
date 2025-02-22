package java_learning.concurrency.volatile_ex;

public class StateFlags {
    static class ShutdownObject {
        volatile boolean shutdownRequested;

        public void shutdown() {
            shutdownRequested = true;
        }

        public void doWork() {
            while (!shutdownRequested) {
                System.out.println(Thread.currentThread().getName() + " is working");
            }
            System.out.println(Thread.currentThread().getName() + " is FINISHED!");
        }
    }

    /**
     * without volatile:
     * Thread-0 is working
     * Thread-4 is FINISHED!
     * Thread-1 is working
     * Thread-1 is FINISHED!
     * Thread-5 is working
     * Thread-5 is FINISHED!
     * Thread-3 is working
     * Thread-3 is FINISHED!
     * Thread-0 is FINISHED!
     * Main is finished
     *
     * with volatile:
     *  ----------- STOOOOOOOOP ------------
     * Thread-4 is working
     * Thread-5 is FINISHED!
     * Thread-1 is working
     * Thread-1 is FINISHED!
     * Thread-0 is FINISHED!
     * Thread-3 is FINISHED!
     * Thread-4 is FINISHED!
     * Main is finished
     *
     * todo: why?????????
     *
     **/
    public static void main(String[] args) throws InterruptedException {
        ShutdownObject obj = new ShutdownObject();
        Runnable work = () -> {
            obj.doWork();
        };

        Runnable stopWork = () -> {
            try {
                Thread.sleep(100);
                obj.shutdown();
                System.out.println(" ----------- STOOOOOOOOP ------------");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        };

        Thread t1 = new Thread(work);
        Thread t2 = new Thread(work);

        Thread t3 = new Thread(stopWork);
        Thread t4 = new Thread(work);
        Thread t5 = new Thread(work);
        Thread t6 = new Thread(work);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();

        t1.join();
        t2.join();
        t3.join();
        t4.join();
        t5.join();
        t6.join();

        System.out.println("Main is finished");
    }
}
