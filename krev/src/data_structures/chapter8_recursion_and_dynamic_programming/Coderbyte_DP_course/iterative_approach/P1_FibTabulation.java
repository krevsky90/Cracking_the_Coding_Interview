package data_structures.chapter8_recursion_and_dynamic_programming.Coderbyte_DP_course.iterative_approach;

/**
 * https://youtu.be/oBt53YbR9Kk?t=11458
 *
 */
public class P1_FibTabulation {
    public static void main(String[] args) {
        System.out.println(fib(8));
    }

    /**
     * time complexity ~ O(n)
     * space complexity ~ O(n)
     */
    public static int fib(int n) {
        int[] memo = new int[n+1];  //filled by 0
        memo[1] = 1;

        for (int i = 2; i <= n; i++) {
            memo[i] = memo[i-1] + memo[i-2];
        }

        return memo[n];
    }
}
