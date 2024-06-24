package solving_techniques.p16_UnboundedKnapsack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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
        MinimumCoinChange obj = new MinimumCoinChange();
        System.out.println("coins1 = ");
        System.out.println("coinChange1 = " + obj.coinChange1(coins1, amount1));   //3
        System.out.println("coinChange2 = " + obj.coinChange2(coins1, amount1));    //3
        System.out.println("coinChange3 = " + obj.coinChange3(coins1, amount1));    //3
        System.out.println("coinChange4 = " + obj.coinChange4(coins1, amount1));    //3
        System.out.println("coinChange5 = " + obj.coinChange5(coins1, amount1));    //3
        System.out.println("coinChange52 = " + obj.coinChange52(coins1, amount1));    //3
        System.out.println("coinChange6 = " + obj.coinChange6(coins1, amount1));    //3


        int[] coins2 = {2};
        int amount2 = 3;
//        System.out.println(coinChange2(coins2, amount2));   //-1

        int[] coins3 = {1};
        int amount3 = 0;
//        System.out.println(coinChange2(coins3, amount3));   //0

        //Time Limit Exceeded
        int[] coins4 = {186, 419, 83, 408};
        int amount4 = 6249;
//        System.out.println("coinChange5 = " + obj.coinChange5(coins4, amount4));    //20
//        System.out.println("coinChange52 = " + obj.coinChange52(coins4, amount4));    //TLE
        System.out.println("coinChange6 = " + obj.coinChange6(coins4, amount4));    //TLE
    }

    /**
     * KREVSKY SOLUTION #1:
     * idea: top-down DP
     * time to solve + debug ~ 14 + 4 = 18 mins
     * time ~ O(coins.length^amount)
     * space ~ O(1)
     */
    public int coinChange1(int[] coins, int amount) {
        int result = coinChangeHelper1(coins, amount);
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

    private int coinChangeHelper1(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }

        int tempMin = Integer.MAX_VALUE;
        for (int c : coins) {
            if (c <= amount) {
                int subResult = coinChangeHelper1(coins, amount - c);
                if (subResult != Integer.MAX_VALUE) {
                    tempMin = Math.min(tempMin, 1 + subResult);
                }
            }
        }

        return tempMin;
    }

    /**
     * KREVSKY SOLUTION #2:
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
    public int coinChange2(int[] coins, int amount) {
        int[] memo = new int[amount + 1];
        Arrays.fill(memo, -1);
        memo[0] = 0;

        int result = coinChangeHelper2(coins, amount, memo);
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
    private int coinChangeHelper2(int[] coins, int amount, int[] memo) {
        if (memo[amount] != -1) {
            return memo[amount];
        }

        int tempMin = Integer.MAX_VALUE;
        for (int c : coins) {
            if (c <= amount) {
                int subResult = coinChangeHelper2(coins, amount - c, memo);
                if (subResult != Integer.MAX_VALUE) {
                    tempMin = Math.min(tempMin, 1 + subResult);
                }
            }
        }

        memo[amount] = tempMin;
        return memo[amount];
    }

    /**
     * !!!!!! BEST !!!!!!
     * SOLUTION #2.2 top-down, approach #2 from readme.txt
     * the same, BUT
     * MAX = just integer number more than 10^4
     * if really simplifies the solution => you do not need to handle corner cases reg Integer.MAX_VALUE and +1
     */
    public int coinChange22(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, -1);
        int result = coinChangeHelper22(coins, amount, dp);
        return result == 100000 ? -1 : result;
    }

    private int coinChangeHelper22(int[] coins, int amount, int[] dp) {
        if (amount < 0) return 100000;  //NOTE: we add it here and commented if (coins[i] <= amount) condition
        if (amount == 0) return 0;
        if (dp[amount] != -1) return dp[amount];

        int result = 100000;    //it is maximum value instead of Integer.MAX_VALUE
        for (int i = 0; i < coins.length; i++) {
            // if (coins[i] <= amount) {
            result = Math.min(result, 1 + coinChangeHelper22(coins, amount - coins[i], dp));
            // }
        }
        return dp[amount] = result;
    }


    /**
     * SOLUTION #3 top-down
     * using APPROACH #1 - i.e. the logic "include or exclude" the element https://www.youtube.com/watch?v=ZI17bgz07EE
     * idea:
     * result = min(1 + count(coins, amount - coins[i], i), count(coins, amount, i + 1))
     */
    public int coinChange3(int[] coins, int amount) {
        return coinChangeHelper3(coins, amount, 0);
    }

    private int coinChangeHelper3(int[] coins, int amount, int startIdx) {
        if (amount == 0) {
            return 0;
        }

        //
        if (startIdx == coins.length) {
            return 100000;   //reflect that this path of denomination is impossible
        }

        if (coins[startIdx] > amount) {
            //we can't use 'include' option => result = notTake option
            return coinChangeHelper3(coins, amount, startIdx + 1);
        } else {
            int takeCoin = 1 + coinChangeHelper3(coins, amount - coins[startIdx], startIdx);
            int notTakeCoin = coinChangeHelper3(coins, amount, startIdx + 1);
            return Math.min(takeCoin, notTakeCoin);
        }
    }

    /**
     * SOLUTION #4: bottom-up
     * https://youtu.be/ZI17bgz07EE?t=932
     * the same idea as for SOLUTION #2, BUT using dp table
     * dp = [coins.len + 1][amount + 1]
     * dp[i][j] - min number of coins needed to form amount = j, using first i coins
     * to exclude: dp[i][j] = dp[i-1][j]
     * to include: dp[i][j] = 1 + dp[i][j - coins[i-1]] - NOTE! be careful with case when coins[i-1] > j! then we cannot include the element!
     * dp[i][j] = min(exclude, include)
     * base cases:
     * all dp[i][0] = MAX_VALUE
     * dp[0][j] = 0
     * <p>
     * time ~ O(coins.length*amount)
     * space ~ O(coins.length*amount)
     */
    public int coinChange4(int[] coins, int amount) {
        int[][] dp = new int[coins.length + 1][amount + 1];

        //starting from 0 to fill base cases
        for (int i = 0; i < coins.length + 1; i++) {
            for (int j = 0; j < amount + 1; j++) {
                if (i == 0) {
                    dp[i][j] = Integer.MAX_VALUE;
                } else if (j == 0) {
                    dp[i][j] = 0;
                } else if (coins[i - 1] > j) {
                    //we can not include i-1-th coin. we can only use 'exclude' option
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.min(1 + dp[i][j - coins[i - 1]], dp[i - 1][j]);
                }
            }
        }

        return dp[coins.length][amount] != Integer.MAX_VALUE ? dp[coins.length][amount] : -1;
    }

    /**
     * SOLUTION #5:
     * based on src/data_structures/chapter8_recursion_and_dynamic_programming/Coderbyte_DP_course/resursive_approach/P5_BestSum# bestSumMemo
     * time to solve ~ 11 mins
     * 1 attempt
     */
    public int coinChange5(int[] coins, int amount) {
        Map<Integer, Integer> memo = new HashMap<>();
        int result = coinChangeHelper5(coins, amount, memo);
        //since max amount = 10000 and min potential coin = 1 => max amount of different coins that form total amount = 10000,
        //so let's take the number > 10000, for example, 100000
        return result >= 100000 ? -1 : result;
    }

    private int coinChangeHelper5(int[] coins, int amount, Map<Integer, Integer> memo) {
        if (amount < 0) return 100000;
        if (amount == 0) return 0;
        if (memo.containsKey(amount)) return memo.get(amount);

        int shortest = 100000;
        for (int c : coins) {
            int subRes = 1 + coinChangeHelper5(coins, amount - c, memo);
            shortest = Math.min(shortest, subRes);
        }

        memo.put(amount, shortest);
        return memo.get(amount);
    }

    /**
     * SOLUTION #5.2
     * the same as SOLUTION #5
     * BUT array instead of Map - NOTE: gives Time Limit Exception!
     * todo: why?? this is the same as solution with Map!
     */
    public int coinChange52(int[] coins, int amount) {
        int[] memo = new int[amount + 1];
        //since max amount = 10000 and min potential coin = 1 => max amount of different coins that form total amount = 10000,
        //so let's take the number > 10000, for example, 100000
        Arrays.fill(memo, 100000);
        int result = coinChangeHelper52(coins, amount, memo);
        return result >= 100000 ? -1 : result;
    }

    private int coinChangeHelper52(int[] coins, int amount, int[] memo) {
        if (amount < 0) return 100000;
        if (amount == 0) return 0;
        if (memo[amount] < 100000) {
            return memo[amount];
        }

        for (int c : coins) {
            int subRes = 1 + coinChangeHelper52(coins, amount - c, memo);
            memo[amount] = Math.min(memo[amount], subRes);
        }

        return memo[amount];
    }

    /**
     * SOLUTION #6:
     * copy of idea: src/solving_techniques/p16_UnboundedKnapsack/MaximumRibbonCut # maximumRibbonCutSimpleLogic
     * idea: for each amount (i) run through the coins and find min sequence of coins
     */
    public int coinChange6(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int i = 1; i < amount + 1; i++) {
            int tempMin = Integer.MAX_VALUE;
            for (int c : coins) {
                if (c <= i) {
                    if (dp[i - c] < Integer.MAX_VALUE) {
                        tempMin = Math.min(tempMin, 1 + dp[i - c]);
                    }
                }
            }

            dp[i] = tempMin;
        }

        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }


}
