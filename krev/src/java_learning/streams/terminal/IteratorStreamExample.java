package java_learning.streams.terminal;

import java.util.Iterator;
import java.util.stream.Stream;

public class IteratorStreamExample {
    public static void main(String[] args) {
        //just for compatibility with old school iterator
        Iterator<Integer> it = Stream.iterate(0, i -> i+1).limit(100).iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }
}
