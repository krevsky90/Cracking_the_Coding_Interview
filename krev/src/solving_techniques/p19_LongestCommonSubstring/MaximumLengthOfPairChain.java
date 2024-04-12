package solving_techniques.p19_LongestCommonSubstring;

import java.util.Arrays;

/**
 * 646. Maximum Length of Pair Chain
 * https://leetcode.com/problems/maximum-length-of-pair-chain
 *
 * You are given an array of n pairs pairs where pairs[i] = [lefti, righti] and lefti < righti.
 *
 * A pair p2 = [c, d] follows a pair p1 = [a, b] if b < c. A chain of pairs can be formed in this fashion.
 *
 * Return the length longest chain which can be formed.
 *
 * You do not need to use up all the given intervals. You can select pairs in any order.
 *
 * Example 1:
 * Input: pairs = [[1,2],[2,3],[3,4]]
 * Output: 2
 * Explanation: The longest chain is [1,2] -> [3,4].
 *
 * Example 2:
 * Input: pairs = [[1,2],[7,8],[4,5]]
 * Output: 3
 * Explanation: The longest chain is [1,2] -> [4,5] -> [7,8].
 *
 * Constraints:
 * n == pairs.length
 * 1 <= n <= 1000
 * -1000 <= lefti < righti <= 1000
 */
public class MaximumLengthOfPairChain {
    public static void main(String[] args) {
        int[][] pairs1 = new int[][]{{1,2},{2,3},{3,4}};
        int[][] pairs2 = new int[][]{{1,2},{7,8},{4,5}};
        System.out.println(new MaximumLengthOfPairChain().findLongestChain(pairs1));
        System.out.println(new MaximumLengthOfPairChain().findLongestChain(pairs2));
    }

    /**
     * KREVSKY SOLUTION:
     * time to think ~ 10-15 mins
     * time to solve ~ 17 mins
     *
     * 2 attempts:
     * - forgot sorting
     */
    public int findLongestChain(int[][] pairs) {
        //sort pairs by left bound
        Arrays.sort(pairs, (a, b) -> a[0] - b[0]);

        int[] dp = new int[pairs.length];
        dp[0] = 1;
        for (int i = 1; i < pairs.length; i++) {
            //if the previous pair does not collide with current pair => we can link them and get longer chain
            if (pairs[i-1][1] < pairs[i][0]) {
                dp[i] = dp[i-1] + 1;
            } else {
                //if the previous pair does not collide with current pair,
                //then we find the closest left pair that does not collide with i-th pair and compare what is longer:
                //1) chunk till closest pair + 1
                //2) chunk till the previous (i.e. i-1-th) pair

                //searching closest left pair that does not collide, BUT it may not exist
                //if it does not exist => we don't consider option 1
                int n = i - 1;
                while (n >= 0) {
                    if (pairs[n][1] < pairs[i][0]) {
                        break;
                    }
                    n--;
                }

                if (n >= 0) {
                    dp[i] = Math.max(dp[i - 1], dp[n] + 1);
                } else {
                    dp[i] = dp[i - 1];
                }
            }
        }

        return dp[pairs.length - 1];
    }
}
