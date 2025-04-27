package solving_techniques.p28_MonotonicStack;

import java.util.Stack;

/**
 * 122. Best Time to Buy and Sell Stock II (medium)
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii
 * <p>
 * #Company (27.04.2025): 0 - 3 months Amazon 9 Google 6 Meta 6 Bloomberg 3 Nike 2 0 - 6 months Goldman Sachs 3 Microsoft 2 Geico 2 6 months ago Apple 11 Adobe 10 TikTok 10 Uber 6 Zoho 4 DE Shaw 4 Citadel 4 Yandex 4 Yahoo 4 J.P. Morgan 3
 * <p>
 * You are given an integer array prices where prices[i] is the price of a given stock on the ith day.
 * <p>
 * On each day, you may decide to buy and/or sell the stock. You can only hold at most one share of the stock at any time. However, you can buy it then immediately sell it on the same day.
 * <p>
 * Find and return the maximum profit you can achieve.
 * <p>
 * Example 1:
 * Input: prices = [7,1,5,3,6,4]
 * Output: 7
 * Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
 * Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.
 * Total profit is 4 + 3 = 7.
 * <p>
 * Example 2:
 * Input: prices = [1,2,3,4,5]
 * Output: 4
 * Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
 * Total profit is 4.
 * <p>
 * Example 3:
 * Input: prices = [7,6,4,3,1]
 * Output: 0
 * Explanation: There is no way to make a positive profit, so we never buy the stock to achieve the maximum profit of 0.
 * <p>
 * Constraints:
 * 1 <= prices.length <= 3 * 10^4
 * 0 <= prices[i] <= 10^4
 */
public class BestTimeToBuyAndSellStock2 {
    /**
     * KREVSKY SOLUTION:
     * idea: use Monotonic stack
     * <p>
     * time to solve ~ 42 mins
     * <p>
     * time ~ O(n)
     * space ~ O(n)
     * <p>
     * 2 attempts:
     * - incorrect logic of setting sellIdx
     * <p>
     * BEATS ~ 10%
     */
    public int maxProfit(int[] prices) {
        Stack<Integer> stack = new Stack<>();
        int idxSell = -1;
        int result = 0;

        for (int i = prices.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && prices[stack.peek()] < prices[i]) {
                if (idxSell != -1) {
                    result += prices[idxSell] - prices[stack.peek()];
                    idxSell = -1;
                }
                stack.pop();
            }

            if (idxSell == -1) {
                idxSell = i;
            }

            stack.add(i);
        }

        if (idxSell != -1) {
            //means the latest element that was added to stack is idxBuy
            result += prices[idxSell] - prices[stack.peek()];
        }

        return result;
    }
}
