package java_learning.streams.terminal;

import java.util.stream.Stream;

public class CountExample {
    public static void main(String[] args) {
        //count - terminal method
        Stream<Integer> s = Stream.of(1,2,3,4,4,4,4,4,5,6,6,7);
        long res = s.count();   //always returns long!
        System.out.println(res);

        //will throw java.lang.IllegalStateException: stream has already been operated upon or closed,
        // since s.count() was written above
//        s.distinct().forEach(System.out::println);
    }
}
