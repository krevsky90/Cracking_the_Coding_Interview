package java_learning.streams.terminal;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ReduceExample {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1,2,3,4,5,6);

        //1. initially, 1st element of collection goes to accumulator var, and reduce process starts with the 2nd element of collection!
        //accumulator = 1 3 6 10 15 21
        //el          = 2 3 4 5  6
        Optional<Integer> opt = list.stream().reduce((accumulator, el) -> accumulator + el);
        if (opt.isPresent()) {
            System.out.println(opt.get());
        } else {
            System.out.println("opt contains null");
        }

        //2. empty list => Optional helps us to avoid potential  java.util.NoSuchElementException: No value present when we call opt.get()
        List<Integer> emptyListAfterFiltering = list.stream().filter(a -> a > 20).collect(Collectors.toList());
        Optional<Integer> opt2 = emptyListAfterFiltering.stream().reduce((accumulator, el) -> accumulator + el);
        if (opt2.isPresent()) {
            System.out.println(opt.get());
        } else {
            System.out.println("opt2 contains null");
        }

        //2.2 replace if using orElse(..) method: i.e. if Optional contains null, it returns -100500
        System.out.println(opt2.orElse(-100500));

        //3. to avoid potential  java.util.NoSuchElementException, use default value for accumulator:
        int defaultAccumValue = 0;
        int val = list.stream().reduce(defaultAccumValue, (accumulator, el) -> accumulator + el);
        System.out.println("val = " + val);
        int valEmpty = emptyListAfterFiltering.stream().reduce(defaultAccumValue, (accumulator, el) -> accumulator + el);
        System.out.println("valEmpty = " + valEmpty);
    }
}
