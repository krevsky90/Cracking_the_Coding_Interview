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

        int result = knapSackGfgBottomUpSpaceOptimization(weights, profits, capacity);    //correct result = 10
        System.out.println("max Profit = " + result);
    }

    /**
     * SOLUTION #1.1:
     * KREVSKY SOLUTION
     * naive (recursive) approach, using binary vector
     * time complexity ~ O(2^items.length)
     * space complexity ~ O(items.length)
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
     * SOLUTION #1.2:
     * naive (recursive) approach
     * https://www.geeksforgeeks.org/0-1-knapsack-problem-dp-10/ + my refactoring
     * Time Complexity: O(2^N)
     * Auxiliary Space: O(N), Stack space required for recursion
     */
    // Returns the maximum profit that can be put in a knapsack of capacity
    // NOTE: n = profits.length
    public static int maxProfitNaive(int weights[], int profits[], int capacity, int n) {
        // Base Case
        if (n == 0 || capacity == 0) {
            return 0;
        }

        // If weight of the nth item is more than Knapsack capacity W,
        // then this item cannot be included in the optimal solution
        if (weights[n - 1] > capacity) {
            return maxProfitNaive(weights, profits, capacity, n - 1);
        } else {
            // Return the maximum of two cases:
            // (1) nth item included
            // (2) not included
            int included = profits[n - 1] + maxProfitNaive(weights, profits, capacity - weights[n - 1], n - 1);
            int notIncluded = maxProfitNaive(weights, profits, capacity, n - 1);
            return Math.max(included, notIncluded);
        }
    }

    /**
     * SOLUTION #2.1.1:
     * BOTTOM-UP (tabulation) manner + iterative approach
     * the same as https://leetcode.com/discuss/study-guide/1152328/01-Knapsack-Problem-and-Dynamic-Programming
     * time complexity ~ O(profits.length*capacity)
     * space complexity ~ O(profits.length*capacity)
     */
    public static int maxProfitOptimizedNP(int[] weights, int[] profits, int capacity) {
        int n = profits.length;
        int[][] dp = new int[n + 1][capacity + 1];
        //0-th row contains only 0s by default
        //It fits the rule: IF i = 0 THEN V[i,w] = 0
        //The other (items.length) rows are for the items

        //NOTE: i + 1 = items.length in the last iteration
        for (int i = 0; i < n; i++) {
            for (int w = 0; w <= capacity; w++) {
                if (weights[i] > w) {
                    dp[i + 1][w] = dp[i][w];
                } else {
                    dp[i + 1][w] = Math.max(dp[i][w], dp[i][w - weights[i]] + profits[i]);
                }
            }
        }

        return dp[n][capacity];
    }

    /**
     * SOLUTION #2.1.1 (just another implementation)
     * BOTTOM-UP (tabulation) manner + iterative approach
     * https://www.geeksforgeeks.org/0-1-knapsack-problem-dp-10/ + my refactoring
     *
     * time complexity ~ O(profits.length*capacity)
     * space complexity ~ O(profits.length*capacity)
     *
     * NOTE: this is the same as SOLUTION 1.1, BUT different approach to indexes!
     */

    //dp[][] =
    //[0, 0, 0, 0, 0, 0]
    //[0, 0, 4, 4, 4, 4]
    //[0, 0, 4, 5, 5, 9]
    //[0, 3, 4, 7, 8, 9]
    //[0, 3, 4, 7, 8, 10]
    public static int knapSackGfgBottomUp(int weights[], int profits[], int capacity) {
        int n = profits.length;
        int dp[][] = new int[n + 1][capacity + 1];

        for (int i = 0; i <= n; i++) {
            for (int w = 0; w <= capacity; w++) {
                if (i == 0 || w == 0) {
                    dp[i][w] = 0;
                } else if (weights[i - 1] > w) {
                    dp[i][w] = dp[i - 1][w];
                } else {
                    dp[i][w] = Math.max(profits[i - 1] + dp[i - 1][w - weights[i - 1]], dp[i - 1][w]);
                }
            }
        }

        return dp[n][capacity];
    }

    /**
     * SOLUTION #2.1.1 + Space optimization
     * BOTTOM-UP (tabulation) manner
     * https://www.geeksforgeeks.org/0-1-knapsack-problem-dp-10/ + my refactoring
     *
     * + Space optimization. Idea:
     * For calculating the current row of the dp[] array we require only previous row,
     * but if we start traversing the rows from right to left then it can be done with a single row only
     *
     * time complexity ~ O(profits.length*capacity)
     * space complexity ~ O(capacity)
     */
    public static int knapSackGfgBottomUpSpaceOptimization(int weights[], int profits[], int capacity) {
        int n = profits.length;
        int dp[] = new int[capacity + 1];

        for (int i = 1; i <= n; i++) {
            for (int w = capacity; w >= 0; w--) {
                if (weights[i - 1] <= w) {
                    dp[w] = Math.max(profits[i - 1] + dp[w - weights[i - 1]], dp[w]);
                }
            }
        }

        return dp[capacity];
    }

    /**
     * SOLUTION #2.2.1
     * TOP-DOWN (memoization) manner + recursive approach
     * https://youtu.be/Q2vDTam9qMQ?t=491 - BE CAREFUL! Code from video has error since validation "capacity < 0" should be BEFORE "currentItem == weights.length"
     *
     * time complexity ~ O(profits.length*capacity)
     * space complexity ~ O(profits.length*capacity)
     */
    public static int knapSackGfgTopDown(int weights[], int profits[], int capacity) {
        int[][] dp = new int[weights.length + 1][capacity + 1];

        int result = knapSackGfgTopDownHelper(weights, profits, capacity, 0, dp);
        return result;

    }

    //   Weights: { 2, 3, 1, 4 }
    // * Profits: { 4, 5, 3, 7 }
    // * Knapsack capacity: 5

//    knapSackGfgTopDownHelper(5, 0)
//        inc = 4 + knapSackGfgTopDownHelper(3, 1) = 4 + 5 = 9
//                    inc = 5 + knapSackGfgTopDownHelper(0, 2) = 5 + 0 = 5
//                                inc = 3 + knapSackGfgTopDownHelper(-1, 3) = 3 + -inf = -inf
//                                not = knapSackGfgTopDownHelper(0, 3) = knapSackGfgTopDownHelper(0, 4) = 0
//                    not = knapSackGfgTopDownHelper(3, 2) = 3
//                                inc = 3 + knapSackGfgTopDownHelper(1, 3) = 3 + 0 = 3
//                                            inc = 7 + knapSackGfgTopDownHelper(-3, 4) = -inf
//                                            not = knapSackGfgTopDownHelper(1, 4) = 0
//                                not = knapSackGfgTopDownHelper(3, 3) = 0
//                                        inc = 7 + knapSackGfgTopDownHelper(-1, 4) = -inf
//                                        not = knapSackGfgTopDownHelper(3, 4) = 0
//        not = knapSackGfgTopDownHelper(5, 1)
//                inc = 5 + knapSackGfgTopDownHelper(2, 2)
//                            inc = 3 + knapSackGfgTopDownHelper(1, 3) = 0
//                                        inc = 7 + knapSackGfgTopDownHelper(-3, 4) = -inf
//                                        not = knapSackGfgTopDownHelper(1, 4) = 0
//                            not = knapSackGfgTopDownHelper(2, 3) =
//                                    inc = 7 + knapSackGfgTopDownHelper(-2, 4) = -inf ===========> !!!! NOTE: condition capacity < 0 should be BEFORE currentItem == weights.length! Otherwise the function returns 0 rather than -INF
//                not =
//    .. etc...
    //dp[][] =
    //[0, 0, 0, 0, 0, 10]
    //[0, 0, 0, 5, 0, 10]
    //[0, 0, 3, 3, 0, 10]
    //[0, 0, 0, 0, 7, 7]
    //[0, 0, 0, 0, 0, 0]
    public static int knapSackGfgTopDownHelper(int weights[], int profits[], int capacity, int currentItem, int[][] dp) {
        if (capacity < 0) {
            return Integer.MIN_VALUE;
        }

        if (currentItem == weights.length) {
            return 0;
        } else {
            if (dp[currentItem][capacity] != 0) return dp[currentItem][capacity];

            int included = profits[currentItem] + knapSackGfgTopDownHelper(weights, profits, capacity - weights[currentItem], currentItem + 1, dp);
            int notIncluded = knapSackGfgTopDownHelper(weights, profits, capacity, currentItem + 1, dp);

            dp[currentItem][capacity] = Math.max(included, notIncluded);
            return dp[currentItem][capacity];
        }
    }
}