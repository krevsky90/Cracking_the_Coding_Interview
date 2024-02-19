package solving_techniques.DP_MinOrMaxPathToReachTarget;

import java.util.Arrays;

/**
 * 64. Minimum Path Sum
 * https://leetcode.com/problems/minimum-path-sum
 *
 * Given a m x n grid filled with non-negative numbers,
 * find a path from top left to bottom right, which minimizes the sum of all numbers along its path.
 *
 * Note: You can only move either down or right at any point in time.
 */
public class MinimumPathSum {
    /**
     * KREVSKY SOLUTION:
     * top-down
     * time to solve ~ 19 mins
     * 1 attempt
     * time ~ O(m*n)
     * space ~ O(m*n)
     */
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        for (int[] a : dp) {
            Arrays.fill(a, -1);
        }

        dp[0][0] = grid[0][0];
        return pathFinder(grid, m - 1, n - 1, dp);
    }

    private int pathFinder(int[][] grid, int i, int j, int[][] dp) {
        if (dp[i][j] != -1) {
            return dp[i][j];
        }

        int result = Integer.MAX_VALUE;
        if (i == 0) {
            result = grid[i][j] + pathFinder(grid, i, j - 1, dp);
        } else if (j == 0) {
            result = grid[i][j] + pathFinder(grid, i - 1, j, dp);
        } else {
            result = grid[i][j] + Math.min(pathFinder(grid, i, j - 1, dp), pathFinder(grid, i - 1, j, dp));
        }

        dp[i][j] = result;
        return dp[i][j];
    }

    /**
     * ALTERNATIVE bottom-up solution
     * https://leetcode.com/problems/minimum-path-sum/solutions/4716229/bottom-up-dynamic-programming-approach/
     */
    public int minPathSumBottomUp(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        int[][] dp = new int[row + 1][col + 1];
        for (int i = 0; i <= row; i++) {
            Arrays.fill(dp[i], 10000);
        }
        dp[0][1] = dp[1][0] = 0;
        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= col; j++) {
                dp[i][j] = grid[i - 1][j - 1] + Math.min(dp[i - 1][j], dp[i][j - 1]);
            }
        }
        return dp[row][col];
    }
}
