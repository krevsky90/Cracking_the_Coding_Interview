package java_learning.concurrency.liveness_problems;

/**
 * info (only theory): https://docs.oracle.com/javase/tutorial/essential/concurrency/starvelive.html
 */
public class Starvation {

    static class Worker {
        int i = 0;

        public synchronized void heavyMethod() throws InterruptedException {
            System.out.println("============= This is heavy method ============= ");
            Thread.sleep(5000);

            System.out.println("Heavy method is finished at " + System.currentTimeMillis());
        }

        /**
         * NOTE: if this method is not synched => it will be called w/o lock => heavyMethod does not affect calls of fastMethod
         */
        public synchronized void fastMethod() {
            System.out.println("This is fast method");
            i++;
            System.out.println("i = " + i);
            System.out.println("Fast method is finished");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Worker worker = new Worker();
        Thread greedyThread = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    worker.heavyMethod();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Thread starvingThread = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                //here is starvation: startingThread is waiting until greedyThread releases 'worker' object since this object is lock
                worker.fastMethod();
            }
        });

        greedyThread.start();
        starvingThread.start();

        greedyThread.join();
        starvingThread.join();

        System.out.println("FINISHED");
    }
}
