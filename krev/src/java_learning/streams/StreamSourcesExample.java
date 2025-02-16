package java_learning.streams;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamSourcesExample {
    private void streamBuilders() {
        //how to create stream
        //1.
        Stream<Integer> s = Arrays.stream(new Integer[]{1, 2, 3, 4});

        //2.
        Stream<Integer> s2 = Stream.of(1, 2, 3, 4);

        //3.
        Stream<Integer> s3 = Stream.builder()
                .add(Integer.valueOf(1))
                .add(Integer.valueOf(2))
                .add(Integer.valueOf(3))
                .add(Integer.valueOf(4))
                .build().map(e -> (Integer) e);

        //4. specific java_learning.streams, extends common stream
        IntStream s4 = IntStream.range(0, 100);
    }

    private void streamGenerators() {
        //generate INFINITE java_learning.streams
        //1.
        AtomicInteger init = new AtomicInteger(0);
        Stream<Integer> s1 = Stream.generate(init::getAndIncrement);

        //2.
        Stream<Integer> s2 = Stream.iterate(0, i ->  i + 1);
    }

    public static void main(String[] args) {

    }
}
