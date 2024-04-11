package solving_techniques.DP_MinOrMaxPathToReachTarget;

import java.util.Arrays;

/**
 * 931. Minimum Falling Path Sum
 * https://leetcode.com/problems/minimum-falling-path-sum/
 * <p>
 * Given an n x n array of integers matrix, return the minimum sum of any falling path through matrix.
 * A falling path starts at any element in the first row and chooses the element in the next row that is either directly below or diagonally left/right.
 * Specifically, the next element from position (row, col) will be (row + 1, col - 1), (row + 1, col), or (row + 1, col + 1).
 */
public class MinimumFallingPathSum {
    public static void main(String[] args) {
        int[][] matrix = {
                {2, 1, 3},
                {6, 5, 4},
                {7, 8, 9}
        };

        System.out.println(new MinimumFallingPathSum().minFallingPathSum(matrix));
    }

    public int minFallingPathSum(int[][] matrix) {
        return minFallingPathSumBottomUp(matrix);
    }

    /**
     * KREVSKY SOLUTION #1: top-down
     * time to solve ~ 25 mins
     * <p>
     * <p>
     * 3 attempts:
     * forgot "matrix[i][j] + "
     * forgot "if (dp[i][j] != Integer.MAX_VALUE) return dp[i][j];"
     * <p>
     * BEATS ~ 99%
     */
    public int minFallingPathSumTopDown(int[][] matrix) {
        int[][] dp = new int[matrix.length][matrix[0].length];
        for (int[] row : dp) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        int result = Integer.MAX_VALUE;
        for (int j = 0; j < matrix[0].length; j++) {
            result = Math.min(result, minFallingPathSum(matrix, dp, 0, j));
        }
        return result;
    }

    public int minFallingPathSum(int[][] matrix, int[][] dp, int i, int j) {
        if (j < 0 || j == matrix[0].length) return Integer.MAX_VALUE;
        if (i == matrix.length) return 0;

        if (dp[i][j] != Integer.MAX_VALUE) return dp[i][j];

        int way1 = minFallingPathSum(matrix, dp, i + 1, j - 1);
        int way2 = minFallingPathSum(matrix, dp, i + 1, j);
        int way3 = minFallingPathSum(matrix, dp, i + 1, j + 1);
        dp[i][j] = matrix[i][j] + Math.min(way1, Math.min(way2, way3));

        return dp[i][j];
    }

    /**
     * KREVSKY SOLUTION #2: bottom-up
     *
     * BEATS = 36%
     */
    public int minFallingPathSumBottomUp(int[][] matrix) {
        int[][] dp = new int[matrix.length + 1][matrix[0].length];  //0-th row should always contain 0s
        int result = Integer.MAX_VALUE;
        for (int i = 1; i < matrix.length + 1; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                int way1 = j > 0 ? dp[i - 1][j - 1] : Integer.MAX_VALUE;
                int way2 = dp[i - 1][j];
                int way3 = j < matrix[0].length - 1 ? dp[i - 1][j + 1] : Integer.MAX_VALUE;
                dp[i][j] = matrix[i - 1][j] + Math.min(way1, Math.min(way2, way3));
            }
        }

        //check the latest row of dp and find min value
        for (int j = 0; j < matrix[0].length; j++) {
            result = Math.min(result, dp[matrix.length][j]);
        }

        return result;
    }
}
