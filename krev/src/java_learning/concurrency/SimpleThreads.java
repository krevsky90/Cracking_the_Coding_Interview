package java_learning.concurrency;

/**
 * https://docs.oracle.com/javase/tutorial/essential/concurrency/simple.html
 */
public class SimpleThreads {
    // Display a message, preceded by the name of the current thread
    static void threadMessage(String message) {
        String threadName = Thread.currentThread().getName();
        System.out.format("%s: %s%n", threadName, message);
    }

    private static class MessageLoop implements Runnable {
        public void run() {
            String importantInfo[] = {"Mares eat oats", "Does eat oats", "Little lambs eat ivy", "A kid will eat ivy too"};
            try {
                for (int i = 0; i < importantInfo.length; i++) {
                    Thread.sleep(4000);
                    threadMessage(importantInfo[i]);
                }
            } catch (InterruptedException e) {
                threadMessage("I wasn't done!");
            }
        }
    }

    public static void main(String args[]) throws InterruptedException {
        long delay = 1000 * 3;
        threadMessage("Starting MessageLoop thread");
        long startTime = System.currentTimeMillis();
        Thread t = new Thread(new MessageLoop());
        t.start();

        threadMessage("Waiting for MessageLoop thread to finish");
        // loop until MessageLoop thread exits
        while (t.isAlive()) {
            threadMessage("Still waiting...");
            // Wait maximum of 1 second for MessageLoop thread to finish
            t.join(1000);
            //or Thread.sleep(1000);
            if (((System.currentTimeMillis() - startTime) > delay) && t.isAlive()) {
                threadMessage("Tired of waiting!");
                t.interrupt();
                // Shouldn't be long now, BUT wait indefinitely
                //NOTE: without this join the output will be:
                // main: Still waiting...
                // Thread-0: I wasn't done!
                // main: Finally!
                //i.e. main thread will not wait finally block of interrupted thread in this case
                t.join();
            }
        }
        threadMessage("Finally!");
    }
}
