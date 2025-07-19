package solving_techniques.dynamic_programming;

/**
 * 123. Best Time to Buy and Sell Stock III (hard)
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii
 *
 * #Company (19.07.2025):
 *
 * You are given an array prices where prices[i] is the price of a given stock on the ith day.
 * Find the maximum profit you can achieve. You may complete at most two transactions.
 * Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
 *
 * Example 1:
 * Input: prices = [3,3,5,0,0,3,1,4]
 * Output: 6
 * Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
 * Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 = 3.
 *
 * Example 2:
 * Input: prices = [1,2,3,4,5]
 * Output: 4
 * Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
 * Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are engaging multiple transactions at the same time.
 *      You must sell before buying again.
 *
 * Example 3:
 * Input: prices = [7,6,4,3,1]
 * Output: 0
 * Explanation: In this case, no transaction is done, i.e. max profit = 0.
 *
 * Constraints:
 * 1 <= prices.length <= 10^5
 * 0 <= prices[i] <= 10^5
 */
public class BestTimeToBuyAndSellStock3 {
    /**
     * NOT SOLVED by myself:
     * time to implement ~ 25 mins
     * <p>
     * idea:
     * we divide prices into 2 non-overlapping intervals,
     * for each interval we find max buy & sell transaction (like BestTimeToBuyAndSellStock1)
     * but using DP to store array to keep the best result for each subarray.
     * Then we will form the result for i-th position like "best result of [0, i] subarray + best result of [i + 1, n - 1] subarray"
     * <p>
     * time ~ O(n)
     * space ~ O(n)
     * <p>
     * 2 attempts:
     * - rightProfit[i+1] caused out of bound exception
     * <p>
     * BEATS ~ 88%
     */
    public int maxProfit(int[] prices) {
        int n = prices.length;

        int[] leftProfit = new int[n];  //i-th element contains max profit that we can get in 1 transaction in [0, i] sub-array
        int minPrice = prices[0];
        for (int i = 1; i < n; i++) {
            leftProfit[i] = Math.max(leftProfit[i - 1], prices[i] - minPrice);
            minPrice = Math.min(minPrice, prices[i]);
        }

        int[] rightProfit = new int[n];  //i-th element contains max profit that we can get in 1 transaction in [i + 1, n - 1] sub-array
        //note: we traverse from right to left, and calculate like steps from higher to lower value (like [3, 6] will be handled as 6 - 3 = 3)
        //otherwise we would calculate from left to right and duplicate calculations
        int maxPrice = prices[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightProfit[i] = Math.max(rightProfit[i + 1], maxPrice - prices[i]);
            maxPrice = Math.max(maxPrice, prices[i]);
        }

        //idea: for each i position we consider that transaction #1 happens in [0, i] subarray, transaction #2 - in [i+1, n-1]
        //it means for each i position max profit = leftProfit[i] + rightProfit[i+1];
        int result = 0;
        for (int i = 0; i < n; i++) {
            result = Math.max(result, leftProfit[i] + (i + 1 >= n ? 0 : rightProfit[i + 1]));
        }

        return result;
    }

    /**
     * Naive solution
     * time ~ O(N^2)
     *
     * idea: solve problem like https://leetcode.com/problems/best-time-to-buy-and-sell-stock/description/ for each divider-point
     */
    public int maxProfitNaive(int[] prices) {
        int n = prices.length;
        int result = 0;
        for (int i = 0; i < n; i++) {
            result = Math.max(result, helper(prices, 0, i) +
                    (i + 1 < n ?
                            helper(prices, i + 1, n - 1)
                            : 0)
                );
        }

        return result;
    }

    private int helper(int[] prices, int start, int end) {
        int result = 0;
        int minPrice = prices[start];
        for (int i = start + 1; i <= end; i++) {
            result = Math.max(result, prices[i] - minPrice);
            minPrice = Math.min(minPrice, prices[i]);
        }

        return result;
    }
}
