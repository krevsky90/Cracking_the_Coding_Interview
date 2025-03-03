package java_learning.concurrency.executors;

import java.util.Random;

public class GenerateRandomIntegerTask implements Runnable {
    @Override
    public void run() {
        try {
            Thread.sleep(100);
            Random r = new Random();
            int num = r.nextInt(1000) + 1;
            System.out.println(Thread.currentThread().getName() + " generated " + num);
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + " is interrupted due to " + e.getLocalizedMessage());
            Thread.currentThread().interrupt();
        }
    }
}
