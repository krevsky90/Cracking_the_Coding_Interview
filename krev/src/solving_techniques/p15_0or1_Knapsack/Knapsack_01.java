package solving_techniques.p15_0or1_Knapsack;

/**
 * https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/63a388c3b7eef54e90110f88
 * <p>
 * Given the weights and profits of ?N? items, we are asked to put these items in a knapsack with a capacity ?C.?
 * The goal is to get the maximum profit out of the knapsack items.
 * Each item can only be selected once, as we don?t have multiple quantities of any item.
 * Let?s take Merry?s example, who wants to carry some fruits in the knapsack to get maximum profit. Here are the weights and profits of the fruits:
 * Items: { Apple, Orange, Banana, Melon }
 * Weights: { 2, 3, 1, 4 }
 * Profits: { 4, 5, 3, 7 }
 * Knapsack capacity: 5
 */
public class Knapsack_01 {
    public static void main(String[] args) {
        String[] items = {"Apple", "Orange", "Banana", "Melon"};
        int[] weights = {2, 3, 1, 4};
        int[] profits = {4, 5, 3, 7};
        int capacity = 5;

        int result = maxProfitOptimizedNP(items, weights, profits, capacity);
        System.out.println("max Profit = " + result);
    }

    /**
     * SOLUTION #1:
     * time complexity ~ O(2^items.length)
     * space complexity ~ O(1) or O(items.length)
     */
    public static int maxProfitNaiveBinaryVector(String[] items, int[] weights, int[] profits, int capacity) {
        int amountOfCombinations = 1 << items.length;
        int maxProfit = 0;
        for (int i = 0; i < amountOfCombinations; i++) {
            maxProfit = Math.max(maxProfit, maxProfitNaiveBinaryVectorHelper(items, weights, profits, capacity, i));
        }

        return maxProfit;
    }

    public static int maxProfitNaiveBinaryVectorHelper(String[] items, int[] weights, int[] profits, int capacity, int combination) {
        int totalProfit = 0;
        int totalWeight = 0;
        for (int i = 0; i < items.length; i++) {
            if (((combination >> i) & 1) == 1) {
                totalWeight += weights[i];
                if (totalWeight > capacity) {
                    //this combination can not exist
                    return 0;
                } else {
                    totalProfit += profits[i];
                }
            }
        }

        return totalProfit;
    }

    /**
     * SOLUTION #2:
     * the same as https://leetcode.com/discuss/study-guide/1152328/01-Knapsack-Problem-and-Dynamic-Programming
     * time complexity ~ O(items.length*capacity)
     * space complexity ~ O(items.length*capacity)
     */
    public static int maxProfitOptimizedNP(String[] items, int[] weights, int[] profits, int capacity) {
        int[][] dp = new int[items.length + 1][capacity + 1];
        //0-th row contains only 0s by default
        //It fits the rule: IF i = 0 THEN V[i,w] = 0
        //The other (items.length) rows are for the items

        //NOTE: i + 1 = items.length in the last iteration
        for (int i = 0; i < items.length; i++) {
            for (int w = 0; w <= capacity; w++) {
                if (weights[i] > w) {
                    dp[i + 1][w] = dp[i][w];
                } else {
                    dp[i + 1][w] = Math.max(dp[i][w], dp[i][w - weights[i]] + profits[i]);
                }
            }
        }

        return dp[items.length - 1][capacity];
    }
}
