package solving_techniques.p16_UnboundedKnapsack;

import java.util.Arrays;

/**
 * https://www.designgurus.io/course-play/grokking-dynamic-programming/doc/637f3802aad31bf9612b478a
 * OR
 * https://www.geeksforgeeks.org/unbounded-knapsack-repetition-items-allowed/
 */
public class UnboundedKnapsack {
    public static void main(String[] args) {
        //example 1:
        int capacity1 = 100;
        int[] values1 = {1, 30};
        int[] weights1 = {1, 50};
        //expected result = 100, when we take 100 instances of 1 unit weight item.

        //example 2:
        int capacity2 = 8;
        int[] values2 = {10, 40, 50, 70};
        int[] weights2 = {1, 3, 4, 5};
        //expected result = 110, when we take 1 unit of weight 5 and 1 unit of weight 3.
        System.out.println(unboundedKnapsack1memo(values2, weights2, capacity2));
        System.out.println(unboundedKnapsack2memo(values2, weights2, capacity2));
    }

    /**
     * SOLUTION #1
     * Idea: see APPROACH #1 here Cracking_the_Coding_Interview\krev\src\solving_techniques\p16_UnboundedKnapsack\readme.txt
     * time complexity ~ O(2^N)
     * space complexity ~ O(N)
     */
    public static int unboundedKnapsack1(int[] values, int[] weights, int capacity) {
        return unboundedKnapsack1(values, weights, 0, capacity);
    }

    // where i - sequence number of item
    public static int unboundedKnapsack1(int[] values, int[] weights, int i, int capacity) {
        //base case
        if (capacity < 0) {
            return Integer.MIN_VALUE;
        }

        if (i == values.length) {
            return 0;
        } else {
            int takeIthElement = values[i] + unboundedKnapsack1(values, weights, i, capacity - weights[i]);
            int notTakeIthElement = unboundedKnapsack1(values, weights, i + 1, capacity);
            return Math.max(takeIthElement, notTakeIthElement);
        }
    }

    /**
     * SOLUTION #1.2
     * Idea: see APPROACH #1 + Memoization
     * time complexity ~ O(N*capacity)
     * space complexity ~ O(N*capacity)
     */
    public static int unboundedKnapsack1memo(int[] values, int[] weights, int capacity) {
        int[][] dp = new int[values.length][capacity + 1];
        //fill dp by -1s
        for (int row[] : dp) {
            Arrays.fill(row, -1);
        }

        int result = unboundedKnapsack1memo(values, weights, 0, capacity, dp);
        return result;
    }

    // where i - sequence number of item
    public static int unboundedKnapsack1memo(int[] values, int[] weights, int i, int capacity, int[][] dp) {
        //base case
        if (capacity < 0) {
            return Integer.MIN_VALUE;
        }

        if (i == values.length) {
            return 0;
        } else {
            if (dp[i][capacity] != -1) return dp[i][capacity];

            int takeIthElement = values[i] + unboundedKnapsack1memo(values, weights, i, capacity - weights[i], dp);
            int notTakeIthElement = unboundedKnapsack1memo(values, weights, i + 1, capacity, dp);
            dp[i][capacity] = Math.max(takeIthElement, notTakeIthElement);
            return dp[i][capacity];
        }
    }

    /**
     * SOLUTION #2.1
     * Idea: see APPROACH #2 here Cracking_the_Coding_Interview\krev\src\solving_techniques\p16_UnboundedKnapsack\readme.txt
     * time complexity ~ O(N^capacity)
     * space complexity ~ O(todo)
     */
    public static int unboundedKnapsack2(int[] values, int[] weights, int capacity) {
        int result = 0;
        for (int i = 0; i < values.length; i++) {
            if (weights[i] <= capacity) {
                result = Math.max(result, values[i] + unboundedKnapsack2(values, weights, capacity - weights[i]));
            }
        }

        return result;
    }

    /**
     * SOLUTION #2.2
     * Idea: see APPROACH #2 + memoization
     * time complexity ~ O(N*capacity)
     * space complexity ~ O(capacity)
     */
    public static int unboundedKnapsack2memo(int[] values, int[] weights, int capacity) {
        int[] dp = new int[capacity + 1];
        int res = unboundedKnapsack2memo(values, weights, capacity, dp);
        return res;
    }

    public static int unboundedKnapsack2memo(int[] values, int[] weights, int capacity, int[] dp) {
        if (dp[capacity] != 0) return dp[capacity];

        int result = 0;
        for (int i = 0; i < values.length; i++) {
            if (weights[i] <= capacity) {
                result = Math.max(result, values[i] + unboundedKnapsack2memo(values, weights, capacity - weights[i], dp));
            }
        }

        dp[capacity] = result;
        return dp[capacity];
    }
}


