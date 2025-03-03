package java_learning.concurrency.executors.callable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * idea: use ExecutorService#submit(..) method
 */
public class FixedThreadPoolExecutorCallableExample {
    public static void main(String[] args) {
        int cores = Runtime.getRuntime().availableProcessors();
        System.out.println(cores);  //prints 12
        long startTime = System.currentTimeMillis();

        List<Future<Integer>> result = new ArrayList<>();
        try (ExecutorService executorService = Executors.newFixedThreadPool(100000)) {
            for (int i = 0; i < 100; i++) {
                Future<Integer> f = executorService.submit(() -> {
                    try {
                        Thread.sleep(1000);
                        Random r = new Random();
                        int num = r.nextInt(1000) + 1;
                        System.out.println(Thread.currentThread().getName() + " generated " + num);
                        return num;
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }});
                result.add(f);
            }

            //should be ~ 0ms, since it does not wait for submitted tasks!
            System.out.println("Time spend before future#get = " + (System.currentTimeMillis() - startTime));

            try {
                for (Future<Integer> f : result) {
                    System.out.println(f.get());
                }
            } catch (ExecutionException | InterruptedException e) {
                throw new RuntimeException(e);
            }

            System.out.println("Time spend after future#get = " + (System.currentTimeMillis() - startTime));
        }
        //NOTE: finally block or everything that is written after '}' of try block should WAIT for all tasks that were submitted inside try section!
    }
}
