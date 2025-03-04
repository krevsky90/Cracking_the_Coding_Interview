package java_learning.concurrency.future;

import java.util.Random;
import java.util.concurrent.*;

public class ExamplesOfFutureMethods {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        Callable<Integer> r = () -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return new Random().nextInt(10);
        };

        Future<Integer> f1 = executor.submit(r);
        Future<Integer> f2 = executor.submit(r);

        while (!(f1.isDone() && f2.isDone())) {
            System.out.println(
                    String.format(
                            "future1 is %s and future2 is %s",
                            f1.isDone() ? "done" : "not done",
                            f2.isDone() ? "done" : "not done"
                    )
            );
            Thread.sleep(300);
        }

        //in this case get() method will return the result immediately
        Integer result1 = f1.get();
        Integer result2 = f2.get();
        executor.shutdown();

        System.out.println(result1 + " and " + result2);
    }


}
