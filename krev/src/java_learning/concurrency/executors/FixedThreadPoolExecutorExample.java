package java_learning.concurrency.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * NOTE: usually optimal number of threads = number of available processors - 1
 */
public class FixedThreadPoolExecutorExample {
    public static void main(String[] args) {
        int cores = Runtime.getRuntime().availableProcessors();
        System.out.println(cores);  //prints 12
        long startTime = System.currentTimeMillis();

        /**
         * use autoCloseable feature for ExecutorService that works starting from Java 21
         */
        try (ExecutorService executorService = Executors.newFixedThreadPool(50)) {
            for (int i = 0; i < 100; i++) {
                executorService.execute(new GenerateRandomIntegerTask());
            }
        }
        //if number of thread = cores = 12 => time = 10009ms
        //50 => 240
        //100 => 141
        //200 => 133, 256
        //1000 => 258

        //summary: optimal number of threads ~ 100
        System.out.println("Time spend = " + (System.currentTimeMillis() - startTime));

    }
}
