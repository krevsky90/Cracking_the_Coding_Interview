package java_learning.streams;

import java.util.stream.Stream;

public class ConcatExample {
    public static void main(String[] args) {
        Stream<Integer> s1 = Stream.of(1,2,3,4,5);
        Stream<Integer> s2 = Stream.of(6,7,8,9,10);

        //concat - static method!
        //NOR intermediate, NEITHER terminal!
        Stream.concat(s1, s2).forEach(System.out::println);

    }
}
