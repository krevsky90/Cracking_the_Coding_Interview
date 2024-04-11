package solving_techniques.p25_UniquePaths;

/**
 * 63. Unique Paths II
 * https://leetcode.com/problems/unique-paths-ii
 *
 * You are given an m x n integer array grid. There is a robot initially located at the top-left corner (i.e., grid[0][0]).
 * The robot tries to move to the bottom-right corner (i.e., grid[m - 1][n - 1]).
 * The robot can only move either down or right at any point in time.
 *
 * An obstacle and space are marked as 1 or 0 respectively in grid.
 * A path that the robot takes cannot include any square that is an obstacle.
 *
 * Return the number of possible unique paths that the robot can take to reach the bottom-right corner.
 *
 * The testcases are generated so that the answer will be less than or equal to 2 * 10^9.
 *
 * Example 1:
 * Input: obstacleGrid = [[0,0,0],[0,1,0],[0,0,0]]
 * Output: 2
 * Explanation: There is one obstacle in the middle of the 3x3 grid above.
 * There are two ways to reach the bottom-right corner:
 * 1. Right -> Right -> Down -> Down
 * 2. Down -> Down -> Right -> Right
 *
 * Example 2:
 * Input: obstacleGrid = [[0,1],[0,0]]
 * Output: 1
 *
 * Constraints:
 * m == obstacleGrid.length
 * n == obstacleGrid[i].length
 * 1 <= m, n <= 100
 * obstacleGrid[i][j] is 0 or 1.
 */
public class UniquePath2 {
    /**
     * KREVSKY SOLUTION: bottom-up
     * idea #1: if [0][j] has obstacle, then all [0][j+k] cells will have 0 ways. The same for [i][0] and [i+k][0]
     * idea #2: if [i][j] has obstacle, then dp[o][j] = 0
     * idea #3 (the same as in Unique Paths): dp[i][j] = dp[i-1][j] + dp[i][j-1]
     *
     * time to solve ~ 12 mins
     *
     * 1 attempt
     *
     * BEATS = 100%
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

        int[][] dp = new int[m][n];

        int row = 0;
        while (row < m) {
            if (obstacleGrid[row][0] == 1) break;

            dp[row][0] = 1;
            row++;
        }

        //set 0 to the cells that lower then the row-th cell with obstacle (if such cells exist)
        while (row < m) {
            dp[row][0] = 0;
            row++;
        }

        int col = 0;
        while (col < n) {
            if (obstacleGrid[0][col] == 1) break;

            dp[0][col] = 1;
            col++;
        }

        //the same for columns
        while (col < n) {
            dp[0][col] = 0;
            col++;

        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = dp[i-1][j] + dp[i][j-1];
                }
            }
        }

        return dp[m-1][n-1];
    }
}
