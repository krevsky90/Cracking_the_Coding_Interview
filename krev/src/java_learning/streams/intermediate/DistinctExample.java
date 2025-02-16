package java_learning.streams.intermediate;

import java.util.stream.Stream;

public class DistinctExample {
    public static void main(String[] args) {
        //distinct - intermediate method
        Stream.of(1,2,3,4,4,4,4,4,5,6,6,7).distinct().sorted().forEach(System.out::println);
    }
}
