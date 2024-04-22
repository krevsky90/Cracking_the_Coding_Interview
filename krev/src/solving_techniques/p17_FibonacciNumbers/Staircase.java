package solving_techniques.p17_FibonacciNumbers;

/**
 * https://www.designgurus.io/course-play/grokking-dynamic-programming/doc/637f48f8e57c531cec3998d2
 * similar to
 * 70. Climbing Stairs
 * https://leetcode.com/problems/climbing-stairs/ (easy)
 *
 * let's solve designgurus's problem:
 *
 * Given a stair with 'n' steps,
 * implement a method to count how many possible ways are there to reach the top of the staircase,
 * given that, at every step you can either take 1 step, 2 steps, or 3 steps.
 *
 * Example 1:
 * Number of stairs (n) : 3
 * Number of ways = 4
 * Explanation: Following are the four ways we can climb : {1,1,1}, {1,2}, {2,1}, {3}
 *
 * Example 2:
 * Number of stairs (n) : 4
 * Number of ways = 7
 * Explanation: Following are the seven ways we can climb : {1,1,1,1}, {1,1,2}, {1,2,1}, {2,1,1},
 * {2,2}, {1,3}, {3,1}
 *
 * NOTE: the same as src\data_structures\chapter8_recursion_and_dynamic_programming\Problem8_1.java
 */
public class Staircase {
    public static void main(String[] args) {
        int n = 10;
        System.out.println(countWays(n));
        System.out.println(countWaysOptimized(n));
        System.out.println(countWaysIterative(n));
    }

    /**
     * KREVSKY SOLUTION recursively
     * time to solve ~ 10 mins
     * time ~ O(3^n)
     * 1 attempt
     */
    public static int countWays(int n) {
        if (n < 0) {
            return 0;
        } else if (n == 0) {
            return 1;
        } else {
            return countWays(n - 1) + countWays(n - 2) + countWays(n - 3);
        }
    }

    /**
     * KREVSKY SOLUTION recursively
     * time to solve ~ 3 mins
     * time ~ O(n)
     * 1 attempt
     */
    public static int countWaysOptimized(int n) {
        int[] memo = new int[n + 1];
        return countWaysMemo(n, memo);
    }

    public static int countWaysMemo(int n, int[] memo) {
        if (n < 0) {
            return 0;
        } else if (n == 0) {
            return 1;
        } else if (memo[n] != 0) {  //note! case "n < 0" should be already handled before we take n-th element!
            return memo[n];
        } else {
            memo[n] = countWaysMemo(n - 1, memo) + countWaysMemo(n - 2, memo) + countWaysMemo(n - 3, memo);
            return memo[n];
        }
    }

    /**
     * KREVSKY SOLUTION iteratively
     * time to solve ~ 10 mins
     * time ~ O(n)
     * the same memoization, but only 3 last numbers that are necessary for calculations
     * 3 attempts
     * - k1 = 1 despite result (0) = 0
     * - k3 = 2, but not 3 - like typo
     */
    public static int countWaysIterative(int n) {
        if (n <= 0) return 0;
        if (n == 1) return 1;
        if (n == 2) return 2;

        int k1 = 1; //result for n = 0
        int k2 = 1; //result for n = 1
        int k3 = 2; //result for n = 2
        int result = 0;
        for (int i = 3; i <= n; i++) {
            result = k1 + k2 + k3;
            k1 = k2;
            k2 = k3;
            k3 = result;
        }

        return result;
    }
}
