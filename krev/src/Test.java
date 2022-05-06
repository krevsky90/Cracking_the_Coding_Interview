import java.util.Arrays;
import java.util.Stack;

public class Test {
    public static void main(String[] args) {
        Stack<Integer> s = new Stack<>();
        s.addAll(Arrays.asList(1,2,3,4,5));
        System.out.println("");
        s.remove(2);
        System.out.println("");
    }
}
