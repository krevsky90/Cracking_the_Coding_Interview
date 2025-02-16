package java_learning.streams.terminal;

import java.util.Arrays;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class CollectorJoiningExample {
    public static void main(String[] args) {
        String[] arr = new String[]{"a", "b", "c"};
        //how to get "a,b,c"?
        String res = Arrays.stream(arr).collect(Collectors.joining(","));
        System.out.println(res);

        //2. StringJoined that is based on StringBuilder and Streams
        System.out.println(new StringJoiner(",").add("a").add("b").add("c"));
    }
}
