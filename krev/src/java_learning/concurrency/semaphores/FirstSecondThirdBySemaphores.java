package java_learning.concurrency.semaphores;

import java.util.concurrent.Semaphore;

public class FirstSecondThirdBySemaphores {
    //0 - means semaphore is blocked
    //1 - means - available (for 1 thread)
    //optionally we can create semaphore to start the 1st thread:
    private static final Semaphore firstSem = new Semaphore(1);
    private static final Semaphore secondSem = new Semaphore(0);
    private static final Semaphore thirdSem = new Semaphore(0);

    /**
     * NOTE:
     * semaphores can not miss signal!
     * (like it might be for locks or wait/notify methods)
     */

    public static void main(String[] args) throws InterruptedException {
        Thread first = new Thread(() -> {
            try {
                firstSem.acquire();     // Wait for permission to proceed
                System.out.println("First");
                secondSem.release();        // Allow the second thread to proceed
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread second = new Thread(() -> {
            try {
                Thread.sleep(1000);
                secondSem.acquire();        // Wait for permission to proceed
                System.out.println("Second");
                thirdSem.release();          // Allow the third thread to proceed
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread third = new Thread(() -> {
            try {
                Thread.sleep(1500);
                thirdSem.acquire();     // Wait for permission to proceed
                System.out.println("Third");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        // The threads can be started in any order
        third.start();
//        Thread.sleep(100);
        second.start();
//        Thread.sleep(100);
        first.start();
    }
}
