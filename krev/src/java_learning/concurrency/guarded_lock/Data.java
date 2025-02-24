package java_learning.concurrency.guarded_lock;

public class Data {
    private String message;

    private boolean isEmpty = true;

    public synchronized void send(String message) {
        while (!isEmpty) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println(Thread.currentThread().getName() + " Interrupted");
            }
        }

        isEmpty = false;
        this.message = message;
        notifyAll();
    }

    public synchronized String receive() {
        while (isEmpty) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println(Thread.currentThread().getName() + " Interrupted");
            }
        }

        isEmpty = true;
        String result = this.message;
        notifyAll();
        return result;
    }
}
