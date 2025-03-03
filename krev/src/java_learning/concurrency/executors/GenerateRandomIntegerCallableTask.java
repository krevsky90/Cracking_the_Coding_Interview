package java_learning.concurrency.executors;

import java.util.Random;
import java.util.concurrent.Callable;

public class GenerateRandomIntegerCallableTask implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        try {
            Thread.sleep(1000);
            Random r = new Random();
            int result = r.nextInt(1000) + 1;
            System.out.println(Thread.currentThread().getName() + " generated " + result);
            return result;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
