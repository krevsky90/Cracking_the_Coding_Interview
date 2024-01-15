package solving_techniques.p16_UnboundedKnapsack;

/**
 * https://www.designgurus.io/course-play/grokking-dynamic-programming/doc/637f3eca3a01f75ebb455762
 * OR
 * 518. Coin Change II
 * https://leetcode.com/problems/coin-change-ii/description/
 *
 * Given an infinite supply of ?n? coin denominations and a total money amount,
 * we are asked to find the total number of distinct ways to make up that amount.
 * <p>
 * Example:
 * <p>
 * Denominations: {1,2,3}
 * Total amount: 5
 * Output: 5
 * Explanation: There are five ways to make the change for '5', here are those ways:
 * 1. {1,1,1,1,1}
 * 2. {1,1,1,2}
 * 3. {1,2,2}
 * 4. {1,1,3}
 * 5. {2,3}
 */
public class CoinChange {
    public static void main(String[] args) {
        int[] coins1 = {1, 2, 3};
        int amount1 = 5;

        System.out.println(getAllCoinCombinations(coins1, amount1)); //5
    }

    /**
     * KREVSKY SOLUTION
     * explanation: https://www.youtube.com/watch?v=ruMqWViJ2_U
     * idea: dp table (see similar idea in MinimumCoinChange#coinCountDP)
     * dp = [coins.len + 1][amount + 1]
     * exclude = dp[i-1][j]
     * include = dp[i][j - coins[i-1]]
     * IF coins[i-1] > j THEN dp[i][j] = include + exclude
     * ELSE dp[i][j] = exclude
     *
     * base cases:
     * dp[0][j] = 0
     * dp[i][0] = 1  - not obvious! but it is like "how many ways exist if path length = 0? the answer is 1"
     *
     * time to solve ~ 20 min of thinking + 8 mins of implementation = 28 mins
     * time ~ O(coins.length * amount)
     * space ~ O(coins.length * amount)
     *
     * 3 attempts:
     * - wrote 'coins[i-1]' instead of 'coins[i-1]' in if condition
     * - wrote 'j < amount' instead of 'j < amount + 1'
     */
    public int change(int amount, int[] coins) {
        int[][] dp = new int[coins.length + 1][amount + 1];

        //starting from 0 to fill base cases
        for (int i = 0; i < coins.length + 1; i++) {
            for (int j = 0; j < amount + 1; j++) {
                if (i == 0) {
                    dp[i][j] = 0;
                } else if (j == 0) {
                    dp[i][j] = 1;
                } else if (coins[i-1] > j) {
                    //use only exclude option
                    dp[i][j] = dp[i-1][j];
                } else {
                    int include = dp[i][j - coins[i-1]];
                    int exclude = dp[i-1][j];
                    dp[i][j] = include + exclude;
                }
            }
        }

        return dp[coins.length][amount];
    }


    /**
     * INCORRECT solution!
     * yes, it is similar to src/data_structures/chapter8_recursion_and_dynamic_programming/Coderbyte_DP_course/resursive_approach/P7_CountConstruct.java
     * BUT in CoinChange problem the order of coins is NOT important!!! and (1,2) and (2,1) are considered as the same!
     */
    public static int getAllCoinCombinations(int[] coins, int amount) {
        return getAllCoinCombinationsHelper(coins, amount);
    }

    private static int getAllCoinCombinationsHelper(int[] coins, int amount) {
        if (amount == 0) {
            return 1;
        }

        int result = 0;
        for (int c : coins) {
            if (c <= amount) {
                result += getAllCoinCombinationsHelper(coins, amount - c);
            }
        }

        return result;
    }
}