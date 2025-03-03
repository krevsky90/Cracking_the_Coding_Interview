package java_learning.concurrency.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SingleThreadExecutorExample {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        ExecutorService executorService = Executors.newSingleThreadExecutor();

        for (int i = 0; i < 100; i++) {
            executorService.execute(new GenerateRandomIntegerTask());
        }

        /**
         * NOTE: before Java 21 we need shutdown executorService manually
         * starting from Java 21 ExecutorService implements AutoCloseable
         */
        executorService.shutdown();
        try {
            //wait for some time that is definitely greater than expected execution time of all tasks
            if (!executorService.awaitTermination(100, TimeUnit.SECONDS)) {
                executorService.shutdownNow();  //returns List<Runnable> notExecutedTasks, let's just skip it
            }
        } catch (InterruptedException e) {
            //this catch block exists because awaitTermination method might throw InterruptedException
            executorService.shutdownNow();  //returns List<Runnable> notExecutedTasks, let's just skip it
        }

        //is about 100ms*100 = 10000ms
        System.out.println("Time spend = " + (System.currentTimeMillis() - startTime));
    }
}
