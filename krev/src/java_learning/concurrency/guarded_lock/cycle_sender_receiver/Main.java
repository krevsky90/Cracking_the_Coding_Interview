package java_learning.concurrency.guarded_lock.cycle_sender_receiver;

import java.util.concurrent.ThreadLocalRandom;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Data data = new Data();

        Thread sender = new Thread(() -> {
           String[] text = {"msg1", "msg2", "msg3", "END"};
           for (String msg : text) {
               data.send(msg);
               System.out.println(Thread.currentThread().getName() + ": Message '" + msg + "' is successfully sent. time = " + System.currentTimeMillis());

               try {
                   Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 5000));
               } catch (InterruptedException e) {
                   Thread.currentThread().interrupt();
                   System.err.println(Thread.currentThread().getName() + " Interrupted");
               }
           }
        }, "senderT");

        Thread receiver = new Thread(() -> {
            for(String receivedMessage = data.receive(); !"END".equals(receivedMessage); receivedMessage = data.receive()) {

                System.out.println(Thread.currentThread().getName() + ": Message '" + receivedMessage + "' is successfully received. time = " + System.currentTimeMillis());

                //Thread.sleep() to mimic heavy server-side processing
                try {
                    Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 5000));
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.err.println("Thread Interrupted");
                }
            }
        }, "receiverT");

        sender.start();
        receiver.start();
        sender.join();
        receiver.join();

        System.out.println("FINISH ALL");
    }
}
