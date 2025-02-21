package java_learning.concurrency.concurrent_collections;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {
    private BlockingQueue<Integer> bq;
    private String name;

    public Consumer(BlockingQueue<Integer> bq, String name) {
        this.bq = bq;
        this.name = name;
    }

    @Override
    public void run() {
        while (true) {
            try {
                int res = bq.take();
                System.out.println("consumer " + name + " takes " + res + ". queue = " + bq.toString());
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
