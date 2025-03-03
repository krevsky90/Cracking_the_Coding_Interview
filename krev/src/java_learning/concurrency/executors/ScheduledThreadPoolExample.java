package java_learning.concurrency.executors;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadPoolExample {
    public static void main(String[] args) {
        int cores = Runtime.getRuntime().availableProcessors();
        System.out.println(cores);  //prints 12
        long startTime = System.currentTimeMillis();

        /**
         * use autoCloseable feature for ExecutorService that works starting from Java 21
         */
        try (ScheduledExecutorService executorService = Executors.newScheduledThreadPool(cores)) {

            executorService.schedule(new GenerateRandomIntegerTask(), 10, TimeUnit.SECONDS);
            executorService.schedule(new GenerateRandomIntegerTask(), 3, TimeUnit.SECONDS);
            executorService.schedule(new GenerateRandomIntegerTask(), 2, TimeUnit.SECONDS);
            executorService.schedule(new GenerateRandomIntegerTask(), 1, TimeUnit.SECONDS);
            executorService.schedule(new GenerateRandomIntegerTask(), 0, TimeUnit.SECONDS);
        } finally {
            //expected time ~ max delay ~ 10s
            System.out.println("Time spend = " + (System.currentTimeMillis() - startTime));
        }
    }
}
