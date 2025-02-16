package java_learning.streams.terminal;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectorsExample {
    public static void main(String[] args) {
        //just for understanding generic view of collector:
        Stream<String> s = null;
        List<String> list = s.collect(Collectors.toList());
        //or in other words
        list = s.collect(() -> new ArrayList<>(),   //ArrayList is Supplier that will store the final result
                (l, t) -> l.add(t), //BiConsumer - accumulator, that is applied for stream's element ALSO in the case of parallel java_learning.streams
                (l1, l2) -> l1.addAll(l2)   //BiConsumer - combiner that will make the final result (that might come from different threads)
        );
        //so... the example above will work in parallel CORRECTLY! despite ArrayList is not synced. But Stream API takes care of it!
    }
}
