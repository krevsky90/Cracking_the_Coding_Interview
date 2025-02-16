package java_learning.streams.intermediate;

import java.util.stream.Stream;

public class PeekExample {
    //peek - intermediate, to see, how method chaining is going on
    //it is like debug or for each, but not terminal
    public static void main(String[] args) {
        Stream<Integer> s = Stream.of(1, 2, 3, 4, 4, 4, 4, 4, 5, 6, 6, 7);
        long res = s.distinct().peek(System.out::println).count();
        System.out.println("count = " + res);
    }
}
