package solving_techniques.p27_Stacks;

import java.util.Stack;

/**
 * https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/6490145d2d3ff689ba978d02
 *
 * Given a positive integer n, write a function that returns its binary equivalent as a string.
 * The function should not use any in-built binary conversion function.
 *
 * Example 1:
 * Input: 2
 * Output: "10"
 * Explanation: The binary equivalent of 2 is 10.
 *
 * Example 2:
 * Input: 7
 * Output: "111"
 * Explanation: The binary equivalent of 7 is 111.
 *
 * Example 3:
 * Input: 18
 * Output: "10010"
 * Explanation: The binary equivalent of 18 is 10010.
 *
 * Constraints:
 * 0 <= num <= 10^9
 */
public class DecimalToBinaryConversion {
    public static void main(String[] args) {
        int n1 = 2;
        int n2 = 7;
        int n3 = 18;

        DecimalToBinaryConversion obj = new DecimalToBinaryConversion();
        System.out.println(obj.convert(n1)); //expected 10
        System.out.println(obj.convert(n2)); //expected 111
        System.out.println(obj.convert(n3)); //expected 10010
    }

    /**
     * KREVSKY SOLUTION:
     * time to solve ~ 3 mins
     * time ~ O(logN), where N - given number
     * space ~ O(logN)
     *
     * 1 attempt
     *
     */
    public String convert(int n) {
        Stack<Integer> stack = new Stack<>();
        while (n > 0) {
            stack.add(n % 2);
            n = n / 2;
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        return sb.toString();
    }
}
