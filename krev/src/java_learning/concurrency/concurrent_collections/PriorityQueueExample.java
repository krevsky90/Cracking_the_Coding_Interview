package java_learning.concurrency.concurrent_collections;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * info: https://www.youtube.com/watch?v=m-7EljqdxpA&list=WL&index=4
 */
public class PriorityQueueExample {
    static class Pair {
        int priority;   //the less number, the higher real priority!
        String name;

        Pair(String name, int priority) {
            this.priority = priority;
            this.name = name;
        }
    }

    static class Writer implements Runnable {
        private BlockingQueue<Pair> q;
        private List<Pair> list;

        {
            list = new ArrayList<>();
            list.add(new Pair("Mid importance", 4));
            list.add(new Pair("below mid importance", 6));
            list.add(new Pair("high importance", 1));
            list.add(new Pair("upper mid importance", 2));
            list.add(new Pair("low importance", 10));
        }

        public Writer(BlockingQueue<Pair> q) {
            this.q = q;
        }

        @Override
        public void run() {
            System.out.println("Writing to Priority queue...");
            for (Pair pair : list) {
                q.add(pair);
                System.out.println(pair.name);
            }
        }
    }

    static class Reader implements Runnable {
        private BlockingQueue<Pair> q;

        public Reader(BlockingQueue<Pair> q) {
            this.q = q;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(1000); //just to avoid correct implementation of sync
                System.out.println("\nReading to Priority queue...");
                while (!q.isEmpty()) {
                    Pair pair = q.poll();
                    System.out.println(pair.name);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Pair> pq = new PriorityBlockingQueue<>(10, (a, b) -> a.priority - b.priority);
        Reader reader = new Reader(pq);
        Writer writer = new Writer(pq);

        Thread t1 = new Thread(writer);
        Thread t2 = new Thread(reader);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("FINISH");
    }

}
