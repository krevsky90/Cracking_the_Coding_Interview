package solving_techniques.p16_UnboundedKnapsack;


import java.util.HashMap;
import java.util.Map;

/**
 * https://www.designgurus.io/course-play/grokking-dynamic-programming/doc/637f3c34adf0aba992ca129c
 * <p>
 * Rod Cutting
 * Problem Statement
 * Given a rod of length 'n', we are asked to cut the rod and sell the pieces in a way that will maximize the profit.
 * We are also given the price of every piece of length 'i' where '1 <= i <= n'.
 * <p>
 * Example:
 * <p>
 * Lengths: [1, 2, 3, 4, 5]
 * Prices: [2, 6, 7, 10, 13]
 * Rod Length: 5
 * <p>
 * Let's try different combinations of cutting the rod:
 * <p>
 * Five pieces of length 1 => 10 price
 * Two pieces of length 2 and one piece of length 1 => 14 price
 * One piece of length 3 and two pieces of length 1 => 11 price
 * One piece of length 3 and one piece of length 2 => 13 price
 */
public class RodCutting {
    public static void main(String[] args) {
        int capacity = 5;
        int[] prices = {2, 6, 7, 10, 13};
        int[] lengths = {1, 2, 3, 4, 5};
        System.out.println(getMax(lengths, prices, capacity));  // expected result = 14
    }

    /**
     * KREVSKY SOLUTION:
     * use APPROACH 2 + Memoization
     *
     * time to solve ~ 20 mins
     * time ~ O(N*capacity)
     * space ~ O(capacity)
     * 1 attempt
     * NOTE: it is NOT checked anywhere like leetcode!
     */
    public static int getMax(int[] lengths, int[] prices, int capacity) {
        int[] dp = new int[capacity + 1];
        return getMax(lengths, prices, capacity, dp);
    }

    /**
     *
     * dp = 0 2 6 8 12 14
     * capacity = 5
     * result = 0
     * i = 0
     * getMax(5)
     *      i = 0: result = 2 + getMax(4)
     *                              i = 0: result = 2 + getMax(3)
     *                                      ...
     *      i = 1: result = 6 + getMax(3)
     *      i = 2: result = 7 + getMax(2)
     *      i = 3: result = 10 + getMax(1)
     *      i = 4: result = 13 + getMax(0)
     *
     * getMax(0) = dp[0] = 0
     * getMax(1) = 2
     *      result = 2 + getMax(0) = 2
     *      dp[1] = 2
     * getMax(2) = dp[2] = 6
     *      result = 2 + getMax(1) = 2 + 2 + 4
     *      or
     *      result = 6 + getMax(0) = 6
     * getMax(3) = 8
     *      result = 2 + getMax(2) = 2 + 6 = 8
     *      or
     *      result = 6 + getMax(1) = 6 + 2 = 8
     *      or
     *      result = 7 + getMax(0) = 7 + 0 = 7
     * getMax(4) = 12
     *      result = 2 + getMax(3) = 2 + 8 = 10
     *      or
     *      result = 6 + getMax(2) = 6 + 6 = 12
     *      or
     *      result = 7 + getMax(1) = 7 + 2 = 9
     *      or
     *      result = 10 + getMax(0) = 10 + 0 = 10
     * getMax(5) = 14
     *      result = 2 + getMax(4) = 2 + 12 = 14
     *      or
     *      result = 6 + getMax(3) = 6 + 8 = 14
     *      or
     *      result = 7 + get(2) = 7 + 6 = 13
     *      or
     *      result = 10 + getMax(1) = 10 + 2 = 12
     *      or
     *      result = 13 + getMax(0) = 13 + 0 = 13
     *
     */
    protected static int getMax(int[] lengths, int[] prices, int capacity, int[] dp) {
        if (dp[capacity] > 0) return dp[capacity];

        int result = 0;
        for (int i = 0; i < lengths.length; i++) {
            if (lengths[i] <= capacity) {
                result = Math.max(result, prices[i] + getMax(lengths, prices, capacity - lengths[i], dp));
            }
        }

        dp[capacity] = result;
        return dp[capacity];
    }
}