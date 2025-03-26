package solving_techniques.prefixSum;

/**
 * 304. Range Sum Query 2D - Immutable (medium)
 * https://leetcode.com/problems/range-sum-query-2d-immutable
 * <p>
 * #Company (26.03.2025): 0 - 3 months Meta 5 0 - 6 months Bloomberg 3 Microsoft 2 6 months ago Lyft 8 Amazon 7 Google 3 Nvidia 2 Snowflake 2 Applied Intuition 2
 * <p>
 * Given a 2D matrix matrix, handle multiple queries of the following type:
 * <p>
 * Calculate the sum of the elements of matrix inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).
 * Implement the NumMatrix class:
 * <p>
 * NumMatrix(int[][] matrix) Initializes the object with the integer matrix matrix.
 * int sumRegion(int row1, int col1, int row2, int col2) Returns the sum of the elements of matrix inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).
 * You must design an algorithm where sumRegion works on O(1) time complexity.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * Input
 * ["NumMatrix", "sumRegion", "sumRegion", "sumRegion"]
 * [[[[3, 0, 1, 4, 2], [5, 6, 3, 2, 1], [1, 2, 0, 1, 5], [4, 1, 0, 1, 7], [1, 0, 3, 0, 5]]], [2, 1, 4, 3], [1, 1, 2, 2], [1, 2, 2, 4]]
 * Output
 * [null, 8, 11, 12]
 * <p>
 * Explanation
 * NumMatrix numMatrix = new NumMatrix([[3, 0, 1, 4, 2], [5, 6, 3, 2, 1], [1, 2, 0, 1, 5], [4, 1, 0, 1, 7], [1, 0, 3, 0, 5]]);
 * numMatrix.sumRegion(2, 1, 4, 3); // return 8 (i.e sum of the red rectangle)
 * numMatrix.sumRegion(1, 1, 2, 2); // return 11 (i.e sum of the green rectangle)
 * numMatrix.sumRegion(1, 2, 2, 4); // return 12 (i.e sum of the blue rectangle)
 * <p>
 * <p>
 * Constraints:
 * <p>
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 200
 * -104 <= matrix[i][j] <= 104
 * 0 <= row1 <= row2 < m
 * 0 <= col1 <= col2 < n
 * At most 104 calls will be made to sumRegion.
 */
public class RangeSumQuery2D_Immutable {
    /**
     * KREVSKY SOLUTION:
     * idea:
     * 1) pre-calculate memo = new int[m][n];   //contains sums of rectangle: top-left (0, 0), bottom-right (r,c)
     * 2) for given coordinates calculate sum as + and - of pre-calculated sums stored in memo
     * <p>
     * time to solve ~ 16 mins
     * <p>
     * time ~ O(n*m) - for pre-calculation
     * space ~ O(n*m)
     * <p>
     * 1 attempt
     * <p>
     * BEATS ~ 36%
     */
    private int[][] memo;

    public RangeSumQuery2D_Immutable(int[][] matrix) {
        int m = matrix.length;  //num of rows
        int n = matrix[0].length;

        memo = new int[m][n];   //contains sums of rectangle: top-left (0, 0), bottom-right (r,c)

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                memo[r][c] = matrix[r][c];
                if (r > 0) memo[r][c] += memo[r - 1][c];
                if (c > 0) memo[r][c] += memo[r][c - 1];
                if (r > 0 && c > 0) memo[r][c] -= memo[r - 1][c - 1];
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        int res = memo[row2][col2];
        if (row1 > 0) res -= memo[row1 - 1][col2];
        if (col1 > 0) res -= memo[row2][col1 - 1];
        if (row1 > 0 && col1 > 0) res += memo[row1 - 1][col1 - 1];

        return res;
    }

    /**
     * Official solution is the same. but simpler (without if-s)
     */
    class NumMatrix2Official {
        private int[][] dp;

        public NumMatrix2Official(int[][] matrix) {
            if (matrix.length == 0 || matrix[0].length == 0) return;
            dp = new int[matrix.length + 1][matrix[0].length + 1];
            for (int r = 0; r < matrix.length; r++) {
                for (int c = 0; c < matrix[0].length; c++) {
                    dp[r + 1][c + 1] = dp[r + 1][c] + dp[r][c + 1] + matrix[r][c] - dp[r][c];
                }
            }
        }

        public int sumRegionOfficial(int row1, int col1, int row2, int col2) {
            return dp[row2 + 1][col2 + 1] - dp[row1][col2 + 1] - dp[row2 + 1][col1] + dp[row1][col1];
        }
    }

}
