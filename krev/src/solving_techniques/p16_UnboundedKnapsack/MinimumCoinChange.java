package solving_techniques.p16_UnboundedKnapsack;

import java.util.Arrays;

/**
 * https://www.designgurus.io/course-play/grokking-dynamic-programming/doc/637f41c73a01f75ebb45677a
 * OR
 * 322. Coin Change
 * https://leetcode.com/problems/coin-change
 * <p>
 * You are given an integer array coins representing coins of different denominations and an integer amount representing a total amount of money.
 * <p>
 * Return the fewest number of coins that you need to make up that amount.
 * If that amount of money cannot be made up by any combination of the coins, return -1.
 * <p>
 * You may assume that you have an infinite number of each kind of coin.
 * <p>
 * Example 1:
 * Input: coins = [1,2,5], amount = 11
 * Output: 3
 * Explanation: 11 = 5 + 5 + 1
 * <p>
 * Example 2:
 * Input: coins = [2], amount = 3
 * Output: -1
 * <p>
 * Example 3:
 * Input: coins = [1], amount = 0
 * Output: 0
 * <p>
 * Constraints:
 * 1 <= coins.length <= 12
 * 1 <= coins[i] <= 2^31 - 1
 * 0 <= amount <= 10^4
 */
public class MinimumCoinChange {
    public static void main(String[] args) {
        int[] coins1 = {1, 2, 5};
        int amount1 = 11;
        System.out.println("coinChange = " + coinChange(coins1, amount1));   //3
        System.out.println("coinCount = " + coinCount(coins1, amount1));    //3
        System.out.println("coinCountDP = " + coinCountDP(coins1, amount1));    //3

        int[] coins2 = {2};
        int amount2 = 3;
//        System.out.println(coinChange2(coins2, amount2));   //-1

        int[] coins3 = {1};
        int amount3 = 0;
//        System.out.println(coinChange2(coins3, amount3));   //0

        //Time Limit Exceeded
        int[] coins4 = {186, 419, 83, 408};
        int amount4 = 6249;
        System.out.println(coinChange2(coins4, amount4));   //???
//        System.out.println(coinChangeLeetcode(coins4, amount4));
    }

    /**
     * KREVSKY SOLUTION #1:
     * idea: top-down DP
     * time to solve + debug ~ 14 + 4 = 18 mins
     * time ~ O(coins.length^amount)
     * space ~ O(1)
     */
    public static int coinChange(int[] coins, int amount) {
        int result = coinChangeHelper(coins, amount);
        return result == Integer.MAX_VALUE ? -1 : result;
    }

    // 1 2 5
    // 11

    // coinChange(11, 0)
    //     c = 1:
    //         coinChange(10, 1)
    //             ...
    //     c = 2:
    //         coinChange(9, 1)
    //             ...
    //     c = 5:
    //         coinChange(6, 1)
    //             c = 1:
    //                 coinChange(5, 2)
    //                     ...
    //             c = 2:
    //                 coinChange(4, 2)
    //             c = 5:
    //                 coinChange(1, 2)
    //                     c = 1:
    //                         coinChange(0, 3)
    //                             result[0] = min(max, 3) = 3

    private static int coinChangeHelper(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }

        int tempMin = Integer.MAX_VALUE;
        for (int c : coins) {
            if (c <= amount) {
                int subResult = coinChangeHelper(coins, amount - c);
                if (subResult != Integer.MAX_VALUE) {
                    tempMin = Math.min(tempMin, 1 + subResult);
                }
            }
        }

        return tempMin;
    }

    /**
     * KREVSKY SOLUTION:
     * the top down + memoization (may be it would be simpler to use bottom-up since it is more visual)
     * time to solve ~ 50 mins
     * time ~ O(coins.length*amount)
     * space ~ O(amount)
     * NOTE: it is also BAD idea to use MAX_VALUE! since we need to handle case 1 + MAX_VALUE (which = MIN_VALUE).
     *       it is quite better to use, for example, 100000 instead of MAX_VALUE - see coinCountDP(..) method
     * ~5 attempts:
     * - it is bad idea to set MAX_VALUE as default value for memo array!!! because we should distinct 2 cases:
     * a) we have not tried to fill i-th cell (i.e. -1)
     * b) it is impossible to change the amount = i => we set MAX_VALUE. One we come to this cell again - we return MAX_VALUE immediately!
     * NOTE: yes, finally we return -1 if we can't change the amount. But during the solution "-1" means not touched cell/amount
     * - need to add condition "if (tempMin != Integer.MAX_VALUE)"
     * - need to add condition "if (subResult != -1)"
     */
    public static int coinChange2(int[] coins, int amount) {
        int[] memo = new int[amount + 1];
        Arrays.fill(memo, -1);
        memo[0] = 0;

        int result = coinChange2(coins, amount, memo);
        return result == Integer.MAX_VALUE ? -1 : result;
    }

    //coins = {3}
    //amount = 6
    // m= 0 m m m m m m
    // ?(6) = 2
    //     temp = 1 + 1 = 2
    //     ?(3) = 1
    //         temp = 1 + 0 = 1
    //         c(0) = 0


    // coins = {2}
    // amount = 3
    // m = 0 m m m
    // c(3)
    private static int coinChange2(int[] coins, int amount, int[] memo) {
        if (memo[amount] != -1) {
            return memo[amount];
        }

        int tempMin = Integer.MAX_VALUE;
        for (int c : coins) {
            if (c <= amount) {
                int subResult = coinChange2(coins, amount - c, memo);
                if (subResult != Integer.MAX_VALUE) {
                    tempMin = Math.min(tempMin, 1 + subResult);
                }
            }
        }

        memo[amount] = tempMin;
        return memo[amount];
    }

    /**
     * SOLUTION using APPROACH #1 - i.e. the logic "include or exclude" the element https://www.youtube.com/watch?v=ZI17bgz07EE
     * idea:
     * result = min(1 + count(coins, amount - coins[i], i), count(coins, amount, i + 1))
     */
    public static int coinCount(int[] coins, int amount) {
        return coinCount(coins, amount, 0);
    }

    private static int coinCount(int[] coins, int amount, int startIdx) {
        if (amount == 0) {
            return 0;
        }

        //
        if (startIdx == coins.length) {
            return 100000;   //reflect that this path of denomination is impossible
        }

        if (coins[startIdx] > amount) {
            //we can't use 'include' option => result = notTake option
            return coinCount(coins, amount, startIdx + 1);
        } else {
            int takeCoin = 1 + coinCount(coins, amount - coins[startIdx], startIdx);
            int notTakeCoin = coinCount(coins, amount, startIdx + 1);
            return Math.min(takeCoin, notTakeCoin);
        }
    }

    /**
     * SOLUTION #3:
     * https://youtu.be/ZI17bgz07EE?t=932
     * the same idea as for SOLUTION #2, BUT using dp table
     * dp = [coins.len + 1][amount + 1]
     * dp[i][j] - min number of coins needed to form amount = j, using first i coins
     *      to exclude: dp[i][j] = dp[i-1][j]
     *      to include: dp[i][j] = 1 + dp[i][j - coins[i-1]] - NOTE! be careful with case when coins[i-1] > j! then we cannot include the element!
     * dp[i][j] = min(exclude, include)
     * bound cases:
     *      all dp[i][0] = MAX_VALUE
     *      dp[0][j] = 0
     *
     * time ~ O(coins.length*amount)
     * space ~ O(coins.length*amount)
     */
    public static int coinCountDP(int[] coins, int amount) {
        int[][] dp = new int[coins.length + 1][amount + 1];

        //starting from 0 to fill base cases
        for (int i = 0; i < coins.length + 1; i++) {
            for (int j = 0; j < amount + 1; j++) {
                if (i == 0) {
                    dp[i][j] = Integer.MAX_VALUE;
                } else if (j == 0) {
                    dp[i][j] = 0;
                } else if (coins[i-1] > j) {
                    //we can not include i-1-th coin. we can only use 'exclude' option
                    dp[i][j] = dp[i-1][j];
                } else {
                    dp[i][j] = Math.min(1 + dp[i][j - coins[i-1]], dp[i-1][j]);
                }
            }
        }

        return dp[coins.length][amount] != Integer.MAX_VALUE ? dp[coins.length][amount] : -1;
    }

}
