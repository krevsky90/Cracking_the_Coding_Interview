package solving_techniques.p30_GreedyAlgorithms;

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
     * Close to official solution:
     * idea: use Greedy approach to find all valleys and peaks => count result
     * <p>
     * time to implement ~ 8 mins
     * <p>
     * time ~ O(n)
     * space ~ O(1)
     * <p>
     * BEATS ~ 77%
     */
    public int maxProfit(int[] prices) {
        int result = 0;
        int valleyIdx = -1;
        for (int i = 0; i < prices.length; i++) {
            if (isValley(prices, i)) {
                valleyIdx = i;
            } else if (isPeak(prices, i) && valleyIdx != -1) {
                result += prices[i] - prices[valleyIdx];
                valleyIdx = -1;
            }
        }

        return result;
    }

    private boolean isPeak(int[] prices, int i) {
        if (i == prices.length - 1) {
            if (i - 1 >= 0 && prices[i - 1] < prices[i]) return true;
        } else {
            if (i - 1 >= 0 && prices[i - 1] < prices[i] && prices[i] >= prices[i + 1]) return true;
        }
        return false;
    }

    private boolean isValley(int[] prices, int i) {
        if (i == 0) {
            if (i + 1 < prices.length && prices[i] < prices[i + 1]) return true;
        } else {
            //i.e. i > 0
            if (i + 1 < prices.length && prices[i - 1] >= prices[i] && prices[i] < prices[i + 1]) return true;
        }

        return false;
    }

    public static void main(String[] args) {
        BestTimeToBuyAndSellStock2 obj = new BestTimeToBuyAndSellStock2();
        int[] prices = {1,4,3,2};
        obj.maxProfitOfficial(prices);
    }

    /**
     * Official solution
     */
    public int maxProfitOfficial(int[] prices) {
        int i = 0;
        int valley = prices[0];
        int peak = prices[0];
        int maxprofit = 0;
        while (i < prices.length - 1) {
            //skip all which is not valley
            while (i < prices.length - 1 && prices[i] >= prices[i + 1]) i++;

            valley = prices[i];
            //skip all which is not peak
            while (i < prices.length - 1 && prices[i] <= prices[i + 1]) i++;

            peak = prices[i];
            maxprofit += peak - valley;

            //NOTE: finally (if the latest point is not valley/peak, this algorithm will set length-1-th element to both vars: valley and peak! but is won't affect maxprofit, since they are equal
        }
        return maxprofit;
    }
}
