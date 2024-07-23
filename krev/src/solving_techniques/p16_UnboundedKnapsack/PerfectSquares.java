package solving_techniques.p16_UnboundedKnapsack;

/**
 * 279. Perfect Squares (medium)
 * https://leetcode.com/problems/perfect-squares
 * <p>
 * #Company: Yandex
 * <p>
 * Given an integer n, return the least number of perfect square numbers that sum to n.
 * A perfect square is an integer that is the square of an integer; in other words,
 * it is the product of some integer with itself.
 * For example, 1, 4, 9, and 16 are perfect squares while 3 and 11 are not.
 * <p>
 * Example 1:
 * Input: n = 12
 * Output: 3
 * Explanation: 12 = 4 + 4 + 4.
 * <p>
 * Example 2:
 * Input: n = 13
 * Output: 2
 * Explanation: 13 = 4 + 9.
 * <p>
 * Constraints:
 * 1 <= n <= 10^4
 */
public class PerfectSquares {
    /**
     * KREVSKY SOLUTION:
     * idea:
     * 1) set list of numbers that are squares of som int
     * 2) solve the problem like MinimumCoinChange # coinChange22
     * <p>
     * time to solve ~ 22 mins
     * time ~ O(n)
     * space ~ O(n)
     * <p>
     * 3 attempts:
     * - forgot to cast math.sqrt to (int)
     * - forgot to set dp size = n+1
     */
    public int numSquares(int n) {
        int k = (int) Math.sqrt(n);

        //we have problem like exchange sum = n by minimum amount of coins, where coins = [1^2, ...k^2]
        int[] coins = new int[k];
        for (int i = 1; i <= k; i++) {
            coins[i - 1] = i * i;
        }

        Integer[] dp = new Integer[n + 1];

        return minCoins(n, coins, dp);
    }

    private int minCoins(int n, int[] coins, Integer[] dp) {
        if (n < 0) return 100000;
        if (n == 0) return 0;
        if (dp[n] != null) return dp[n];

        int result = Integer.MAX_VALUE;

        for (int i = 0; i < coins.length; i++) {
            result = Math.min(result, 1 + minCoins(n - coins[i], coins, dp));
        }

        return dp[n] = result;
    }
}
