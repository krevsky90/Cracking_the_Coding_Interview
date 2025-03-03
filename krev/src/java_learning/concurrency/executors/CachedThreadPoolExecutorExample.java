package java_learning.concurrency.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CachedThreadPoolExecutorExample {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        /**
         * NOTE: depending on AMOUNT_OF_TASKS the number of workers (in newCachedThreadPool) may increase or decrease
         */
        int AMOUNT_OF_TASKS = 100;
        try (ExecutorService executorService = Executors.newCachedThreadPool()) {
            for (int i = 0; i < AMOUNT_OF_TASKS; i++) {
                executorService.execute(new GenerateRandomIntegerTask());
            }
        } finally {
            System.out.println("Time spend = " + (System.currentTimeMillis() - startTime));
        }
    }
}
