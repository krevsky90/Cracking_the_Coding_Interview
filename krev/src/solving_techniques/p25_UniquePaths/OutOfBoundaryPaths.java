package solving_techniques.p25_UniquePaths;

/**
 * 576. Out of Boundary Paths
 * https://leetcode.com/problems/out-of-boundary-paths
 *
 * There is an m x n grid with a ball.
 * The ball is initially at the position [startRow, startColumn].
 * You are allowed to move the ball to one of the four adjacent cells in the grid (possibly out of the grid crossing the grid boundary).
 * You can apply at most maxMove moves to the ball.
 *
 * Given the five integers m, n, maxMove, startRow, startColumn,
 * return the number of paths to move the ball out of the grid boundary.
 * Since the answer can be very large, return it modulo 10^9 + 7.
 *
 * Input: m = 2, n = 2, maxMove = 2, startRow = 0, startColumn = 0
 * Output: 6
 *
 * Example 2:
 * Input: m = 1, n = 3, maxMove = 3, startRow = 0, startColumn = 1
 * Output: 12
 *
 * Constraints:
 * 1 <= m, n <= 50
 * 0 <= maxMove <= 50
 * 0 <= startRow < m
 * 0 <= startColumn < n
 */
public class OutOfBoundaryPaths {
    /**
     * KREVSKY SOLUTION:
     * similar to https://leetcode.com/problems/out-of-boundary-paths/solutions/4630339/beats-100-users-java-explained/?envType=list&envId=55ajm50i
     * time to solve ~ 34 mins
     *
     * idea: top-down (memoization)
     * base cases:
     * 1) out of bounds => return 1
     * 2) reach limit of the steps, but still inside the bounds => return 0
     *
     * collect the total num of ways from all 4 directions
     *
     * 4 attempts:
     * - incorrect condition reg bounds: wrote > instead of >=
     * - forgot memoization
     * - forgot to return memo is it is NOT null
     *
     * BEATS = 58%
     */
    int[][] dirs = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

    public int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
        Integer[][][] memo = new Integer[maxMove + 1][m + 1][n + 1];
        return findPaths(m, n, maxMove, startRow, startColumn, 0, memo);
    }

    private int findPaths(int m, int n, int maxMove, int startRow, int startColumn, int stepNum, Integer[][][] memo) {
        if (startRow < 0 || startRow >= m || startColumn < 0 || startColumn >= n) return 1;
        if (stepNum == maxMove) return 0;

        if (memo[stepNum][startRow][startColumn] != null) return memo[stepNum][startRow][startColumn];

        long result = 0;
        for (int i = 0; i < dirs.length; i++) {
            result += findPaths(m, n, maxMove, startRow + dirs[i][0], startColumn + dirs[i][1], stepNum + 1, memo);
        }

        return memo[stepNum][startRow][startColumn] = (int) (result % (Math.pow(10, 9) + 7));
    }

    /**
     * SOLUTION #2:
     * info:
     * https://leetcode.com/problems/out-of-boundary-paths/solutions/4626756/beats-99-users-c-java-python-javascript-explained/?envType=list&envId=55ajm50i
     *
     * bottom-up
     * idea - to store the number of ways to each cell inside the bounds.
     * if cell is boundary => add its nums of ways to the result
     * memo[i][j] - stores nums of ways from start coordinates to [i,j] cell
     */
    public int findPaths2(int m, int n, int N, int x, int y) {
        final int M = 1000000000 + 7;
        int[][] dp = new int[m][n];
        dp[x][y] = 1;
        int count = 0;

        for (int moves = 1; moves <= N; moves++) {
            int[][] temp = new int[m][n];

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (i == m - 1) count = (count + dp[i][j]) % M;
                    if (j == n - 1) count = (count + dp[i][j]) % M;
                    if (i == 0) count = (count + dp[i][j]) % M;
                    if (j == 0) count = (count + dp[i][j]) % M;
                    temp[i][j] = (
                            ((i > 0 ? dp[i - 1][j] : 0) + (i < m - 1 ? dp[i + 1][j] : 0)) % M +
                                    ((j > 0 ? dp[i][j - 1] : 0) + (j < n - 1 ? dp[i][j + 1] : 0)) % M
                    ) % M;
                }
            }
            dp = temp;
        }

        return count;
    }

}
