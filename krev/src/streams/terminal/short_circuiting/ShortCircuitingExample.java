package streams.terminal.short_circuiting;

import java.util.stream.Stream;

public class ShortCircuitingExample {
    public static void main(String[] args) {
        //for case when we have infinite stream we need to use short-circuiting methods
        //idea is like "we have already found the result, no sense to continue"
        //methods: find* (like findFirst. findAny etc),
        //         *Match,
        //          limit
        int v = Stream.iterate(0, i -> i + 1).filter(i -> i % 2 == 0).findFirst().get();

    }

}
