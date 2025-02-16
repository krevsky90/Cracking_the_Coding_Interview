package java_learning.streams.intermediate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ParallelStreamExample {
    public static void main(String[] args) {
        //list.parallelStream(). ...
        //or
        //Stream<T> stream = Stream.of(..)
        // stream.parallel(). ...

        //NOTE: parallel java_learning.streams help when we use aggregated functions and amount of elements is very big
        List<Double> list = Arrays.asList(10.0, 5.0, 1.0, 0.25);

        System.out.println(list.stream().reduce(0.0, (sum, el) -> sum + el));   //return 16.25
        System.out.println(list.parallelStream().reduce(0.0, (sum, el) -> sum + el));   //return 16.25

        //BUT does NOT fit when the result depends on the order of elements!!!
        System.out.println(list.stream().reduce((sum, el) -> sum / el));        //returns Optional[8.0]
        //return Optional[0.5], since 1st thread calcs 10.0/5.0 = 2.0, 2nd thread calcs 1.0/0.25 = 4.0 => finally 2.0/4.0 = 0.5
        System.out.println(list.parallelStream().reduce((sum, el) -> sum / el));

        ////////////////////////////////
        Stream<Integer> stream = IntStream.range(0, 100).mapToObj(i -> 1);
        System.out.println(getSum(stream)); //returns 100

        Stream<Integer> stream2 = IntStream.range(0, 100).mapToObj(i -> 1).parallel();
        System.out.println(getSum(stream2)); //returns UNpredictable result which is <= 100
    }

    private static int getSum(Stream<Integer> stream) {
        //NOTE: since we must use effectively elements inside lambda expression. we use array and change its value
        int[] sum = new int[1];
        stream.forEach(i -> sum[0] += i);
        return sum[0];
    }
}
