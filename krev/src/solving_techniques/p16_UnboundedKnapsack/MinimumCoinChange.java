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
        System.out.println(coinChange(coins1, amount1));   //3

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
     * ~5 attempts:
     * - it is bad idea to set MAX_VALUE as default value for memo array!!! because we should distinct 2 cases:
     * a) we have not tried to fill i-th cell (i.e. -1)
     * b) it is impossible to change the amount = i => we set MAX_VALUE. One we come to this cell again - we return MAX_VALUE immediately!
     * NOTE: yes, finally we return -1 if we can't change the amount. But during the soluton "-1" means not touched cell/amount
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
}
