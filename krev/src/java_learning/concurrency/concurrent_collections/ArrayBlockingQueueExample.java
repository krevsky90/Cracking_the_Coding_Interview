package java_learning.concurrency.concurrent_collections;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ArrayBlockingQueueExample {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Integer> bq = new ArrayBlockingQueue<>(4);
        Producer producer = new Producer(bq);
        Consumer consumer1 = new Consumer(bq, "1");
        Consumer consumer2 = new Consumer(bq, "2");

        new Thread(producer).start();
        new Thread(consumer1).start();
        new Thread(consumer2).start();
    }
}
