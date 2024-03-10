package solving_techniques.DP_MinOrMaxPathToReachTarget;

import java.util.Arrays;
import java.util.List;

/**
 * 120. Triangle
 * https://leetcode.com/problems/triangle
 *
 * Given a triangle array, return the minimum path sum from top to bottom.
 *
 * For each step, you may move to an adjacent number of the row below.
 * More formally, if you are on index i on the current row,
 * you may move to either index i or index i + 1 on the next row.
 *
 * Example 1:
 *
 * Input: triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
 * Output: 11
 * Explanation: The triangle looks like:
 *    2
 *   3 4
 *  6 5 7
 * 4 1 8 3
 * The minimum path sum from top to bottom is 2 + 3 + 5 + 1 = 11 (underlined above).
 *
 * Example 2:
 * Input: triangle = [[-10]]
 * Output: -10
 *
 * Constraints:
 * 1 <= triangle.length <= 200
 * triangle[0].length == 1
 * triangle[i].length == triangle[i - 1].length + 1
 * -10^4 <= triangle[i][j] <= 10^4
 *
 * Follow up: Could you do this using only O(n) extra space, where n is the total number of rows in the triangle?
 */
public class Triangle {
    /**
     * KREVSKY SOLUTION:
     * time to solve 40 mins
     *
     * 3 attempts:
     * - incorrect logic (not fit general logic described in readme
     * - TLE (without memo)
     * - forget to remove row dp[0][0] = ..
     *
     * time ~ O(n*n), where n - amount of levels (or max amount of numbers in the latest level)
     * space O(n*n)
     *
     * I EVEN DID NOT TRY to invent the solution where space ~ O(n)
     */
    // minimumTotal(0,0)
    //     return -1 + Min: -1 + 0 = -1
    //                 minimumTotal(1,0)
    //                     return 2 + Min: = 2 + (-1) = 1
    //                                 minimumTotal(2,0) = 1
    //                                 minimumTotal(2,1) = -1
    //                 minimumTotal(1,1)
    //                     return 3 + Min: = 3 + (-3) = 0
    //                                 minimumTotal(2,1) = -1
    //                                 minimumTotal(2,2) = -3
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[][] dp = new int[n][n];
        for (int[] row : dp) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        int result = minimumTotal(triangle, 0, 0, dp);
        return result;
    }

    private int minimumTotal(List<List<Integer>> triangle, int level, int idx, int[][] dp) {
        if (level == triangle.size() - 1) {
            return triangle.get(level).get(idx);
        }

        if (dp[level][idx] != Integer.MAX_VALUE) {
            return dp[level][idx];
        }

        return dp[level][idx] = triangle.get(level).get(idx) +
                Math.min(minimumTotal(triangle, level + 1, idx, dp),
                        minimumTotal(triangle, level + 1, idx + 1, dp));
    }
}
