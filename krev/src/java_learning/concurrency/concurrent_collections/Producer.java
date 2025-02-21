package java_learning.concurrency.concurrent_collections;

import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {
    private BlockingQueue<Integer> bq;

    public Producer(BlockingQueue<Integer> bq) {
        this.bq = bq;
    }

    @Override
    public void run() {
        int i = 1;
        while (true) {
            bq.offer(i);
            System.out.println("producer adds " + i + ". queue = " + bq.toString());
            i++;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
