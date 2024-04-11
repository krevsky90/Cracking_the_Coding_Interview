package solving_techniques.DP_MinOrMaxPathToReachTarget;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 279. Perfect Squares
 * https://leetcode.com/problems/perfect-squares
 *
 * Given an integer n, return the least number of perfect square numbers that sum to n.
 *
 * A perfect square is an integer that is the square of an integer; in other words, it is the product of some integer with itself.
 * For example, 1, 4, 9, and 16 are perfect squares while 3 and 11 are not.
 *
 * Example 1:
 * Input: n = 12
 * Output: 3
 * Explanation: 12 = 4 + 4 + 4.
 *
 * Example 2:
 * Input: n = 13
 * Output: 2
 * Explanation: 13 = 4 + 9.
 *
 * Constraints:
 * 1 <= n <= 10000
 */
public class PerfectSquares {
    public static void main(String[] args) {
        int n1 = 12;
        int n2 = 13;
        System.out.println(new PerfectSquares().numSquares(n1));
        System.out.println(new PerfectSquares().numSquares(n2));
    }
    /**
     * KREVSKY SOLUTION #1: top-down
     * idea #1: collect squares
     * idea #2: DP problem like "src/solving_techniques/p16_UnboundedKnapsack/MinimumCoinChange.java"
     *
     * time to solve ~ 25 mins
     *
     * 5 attempts:
     * - problems with MAX_VALUE
     * - incorrect arguments in Math.min(...)
     * -forgot dp argument several times
     *
     * BEATS = 12%
     */
    public int numSquares(int n) {
        List<Integer> squares = new ArrayList<>();
        for (int i = 1; i * i <= n; i++) {
            squares.add(i * i);
        }

        int[] dp = new int[n + 1];
        Arrays.fill(dp, 100000);   //aka MAX_VALUE

        return numSquaresBottomUp(squares, n);
//        return numSquaresTopDown(squares, n, dp);
    }

    // numSquaresTopDown({1,4,9}, 12)
//     sq = 1:
//         result = 1 + min(MAX, numSquaresTopDown({1,4,9}, 11))
//             sq = 1:
//                 result = 1 + min(MAX, numSquaresTopDown({1,4,9}, 10))
//             sq = 4:
//                 result = 1 + min(MAX, numSquaresTopDown({1,4,9}, 7))
//             sq = 9:
//                 result = 1 + min(MAX, numSquaresTopDown({1,4,9}, 9))
//         ...
//     sq = 4:
//         result = 1 + min(MAX, numSquaresTopDown({1,4,9}, 8)) = 1 + 2 = 3
//             ..
//             sq = 4:
//                 result = 1 + min(MAX, numSquaresTopDown({1,4,9}, 4)) = 1 + 1 = 2
//                     sq = 4:
//                         result = 1 + min(MAX, numSquaresTopDown({1,4,9}, 0)) = 1 + 0 = 1

    //             ..
//     sq = 9:
//         result = 1 + min(MAX, numSquaresTopDown({1,4,9}, 3))

    /**
     * KREVSKY SOLUTION #2: bottom-up
     *
     * BEATS = 13%
     */
    private int numSquaresTopDown(List<Integer> squares, int target, int[] dp) {
        if (target == 0) return 0;

        if (dp[target] != 100000) return dp[target];

        for (int sq : squares) {
            if (target - sq >= 0) {
                dp[target] = Math.min(dp[target], 1 + numSquaresTopDown(squares, target - sq, dp));
            }
        }

        return dp[target];
    }

    public int numSquaresBottomUp(List<Integer> squares, int target) {
        int[] dp = new int[target + 1];
        Arrays.fill(dp, 100000);    //aka MAX_VALUE
        dp[0] = 0;  //base case

        for (int i = 0; i < target + 1; i++) {
            for (int sq : squares) {
                if (i - sq >= 0) {
                    dp[i] = Math.min(dp[i], 1 + dp[i - sq]);
                }
            }
        }

        return dp[target];
    }

}


