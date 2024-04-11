package solving_techniques.p25_UniquePaths;

/**
 * 62. Unique Paths
 * https://leetcode.com/problems/unique-paths
 * <p>
 * There is a robot on an m x n grid. The robot is initially located at the top-left corner (i.e., grid[0][0]).
 * The robot tries to move to the bottom-right corner (i.e., grid[m - 1][n - 1]).
 * The robot can only move either down or right at any point in time.
 * <p>
 * Given the two integers m and n, return the number of possible unique paths that the robot can take to reach the bottom-right corner.
 * <p>
 * The test cases are generated so that the answer will be less than or equal to 2 * 10^9.
 * <p>
 * Example 1:
 * Input: m = 3, n = 7
 * Output: 28
 * <p>
 * Example 2:
 * Input: m = 3, n = 2
 * Output: 3
 * Explanation: From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
 * 1. Right -> Down -> Down
 * 2. Down -> Down -> Right
 * 3. Down -> Right -> Down
 * <p>
 * Constraints:
 * 1 <= m, n <= 100
 */
public class UniquePaths {
    /**
     * KREVSKY SOLUTION: bottom-up
     * time to solve ~ 4 mins
     * <p>
     * 2 attempts:
     * - incorrect size of dp (m+1, n+1), but we need (m,n)
     * <p>
     * BEATS = 100%
     */
    public int uniquePathsBottomUp(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }

        for (int j = 1; j < n; j++) {
            dp[0][j] = 1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }

        return dp[m - 1][n - 1];
    }

    /**
     * KREVSKY SOLUTION #2: top-down
     * <p>
     * BEATS = 100%
     */
    public int uniquePathsTopDown(int m, int n) {
        int[][] dp = new int[m][n];
        //do not fill by -1, since 0 means not visited

        return uniquePathsTopDown(m - 1, n - 1, dp);
    }

    private int uniquePathsTopDown(int i, int j, int[][] dp) {
        //base case
        if (i == 0 || j == 0) return 1;

        if (dp[i][j] > 0) return dp[i][j];

        return dp[i][j] = uniquePathsTopDown(i - 1, j, dp) + uniquePathsTopDown(i, j - 1, dp);
    }
}
