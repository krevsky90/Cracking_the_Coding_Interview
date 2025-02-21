package java_learning.concurrency.concurrent_collections;

import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteArrayListExample {
    public static void main(String[] args) throws InterruptedException {

        CopyOnWriteArrayList list = new CopyOnWriteArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));

        Thread t1 = new Thread(() -> {
            try {
                Thread.sleep(200);
                list.set(3, 444);
                System.out.println("set 444 at position 3");
                list.addAll(Arrays.asList(7, 8, 9));
                System.out.println("add 7,8,9");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        //will print the latest content of list
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < list.size(); i++) {
                System.out.println(list.get(i));
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        //will print the content of list that was actual when we started this thread (i.e. 1.2.3.4.5.6)
        Thread t21 = new Thread(() -> {
            Iterator<Integer> it = list.iterator();
            while (it.hasNext()) {
            try {
                Thread.sleep(200);
                System.out.println(it.next());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            }
        });

        t1.start();
//        t2.start();
        t21.start();
        t1.join();
//        t2.join();
        t21.join();
        System.out.println(list);
    }
}
