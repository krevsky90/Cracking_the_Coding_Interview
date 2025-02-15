package streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class GeneralFunctionalInterfaces {
    /**
     * info:
     * https://www.examclouds.com/ru/java/java-core-russian/lesson15-tasks
     *
     * see interfaces
     * Predicates
     * Suppliers
     * Consumers (andThen method)
     * Function
     *
     */
    public static void main(String[] args) {
        Predicate<Integer> p1 = a -> a < 8;
        Predicate<Integer> p2 = a -> a > 2;
        Supplier<Integer> get4 = () -> 4;
        Function<Integer, String> funcToString = (a) -> "str: " + a;

        Consumer<String> consumer1 = b -> System.out.println("apply cons1 to " + b);
        Consumer<String> consumer2 = b -> System.out.println("apply cons2 to " + b);

        List<String> res = Arrays.asList(1,2,3,4,5,6,7,8,9).stream()
                .filter(p1.and(p2))
                .map(funcToString)
                .peek(consumer1.andThen(consumer2))
                .collect(Collectors.toList());

        System.out.println(res);

        //consumers
        int[] num = new int[]{10};
        Consumer<int[]> multi2 = arr -> arr[0] *= 2;
        Consumer<int[]> plus10 = arr -> arr[0] += 10;

        multi2.andThen(plus10).accept(num); //10*2 + 10 = 30
        System.out.println(num[0]);

    }
}
