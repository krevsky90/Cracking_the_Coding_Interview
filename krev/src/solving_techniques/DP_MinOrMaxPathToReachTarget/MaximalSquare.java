package solving_techniques.DP_MinOrMaxPathToReachTarget;

import java.util.Arrays;

/**
 * 221. Maximal Square
 * https://leetcode.com/problems/maximal-square/
 *
 * Example 1:
 * Given an m x n binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.
 * Input: matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
 * Output: 4
 *
 * Example 2:
 * Input: matrix = [["0","1"],["1","0"]]
 * Output: 1
 *
 * Example 3:
 * Input: matrix = [["0"]]
 * Output: 0
 *
 * Constraints:
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 300
 * matrix[i][j] is '0' or '1'.
 */
public class MaximalSquare {
    public static void main(String[] args) {
        char[][] matrix = {
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}
        };
        System.out.println(new MaximalSquare().maximalSquareRecursive1(matrix));
    }

    /**
     * SOLUTION #1: NOT solved by myself
     * info: https://www.youtube.com/watch?v=6X7Ha2PrDmM
     * idea #0: for each [i][j]-th cell count maximum length of square that can be obtained if [i][j]-th cell is top left corner of this square
     * idea #1: each memo[i][j] stores max length of square's edge that has [i][j]-th cell as left top corner
     * idea #2: if matrix[i][j] == 1 then memo[i][j] = 1 + minimum(memo[i + 1][j], memo[i][j + 1], memo[i + 1][j + 1])
     * <p>
     * approach: iterative top-down + memoization
     * <p>
     * time to implement ~ 13 mins
     * <p>
     * time ~ O(n*m)
     * space  ~ O(n*m)
     * <p>
     * 2 attempts:
     * incorrect "return result;" instead of "return result*result;"
     * <p>
     * BEATS = 61%
     */

    //fill from [m-1[n-1] to [0][0]
    public int maximalSquareIterative1(char[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        //idea: stores max length of square's edge that has [i][j]-th cell as TOP LEFT corner
        int[][] memo = new int[m][n];
        int result = 0;

        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                //base cases
                if (i == m - 1 || j == n - 1) {
                    memo[i][j] = matrix[i][j] == '0' ? 0 : 1;
                } else {
                    if (matrix[i][j] == '1') {
                        int way1 = memo[i + 1][j];
                        int way2 = memo[i][j + 1];
                        int way3 = memo[i + 1][j + 1];
                        int minWay = Math.min(way1, Math.min(way2, way3));
                        memo[i][j] = 1 + minWay;    //1 = matrix[i][j]
                    }
                    //if matrix[i][j] = 0, then do nothing, since memo[i][j] = 0 from the beginning
                }

                result = Math.max(result, memo[i][j]);  //optimization: to avoid traversing memo again
            }
        }

        return result * result;
    }

    //fill from [0][0] to [m-1[n-1]
    public int maximalSquareIterative2(char[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        //idea: stores max length of square's edge that has [i][j]-th cell as BOTTOM RIGHT corner
        int[][] memo = new int[m][n];
        int result = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0) {
                    //base case
                    memo[i][j] = matrix[i][j] == '0' ? 0 : 1;
                } else {
                    if (matrix[i][j] == '1') {
                        int way1 = memo[i - 1][j];
                        int way2 = memo[i][j - 1];
                        int way3 = memo[i - 1][j - 1];
                        int minWay = Math.min(way1, Math.min(way2, way3));
                        memo[i][j] = 1 + minWay;    //1 = matrix[i][j]
                    }
                }

                result = Math.max(result, memo[i][j]);  //optimization: to avoid traversing memo again
            }
        }

        return result * result;
    }

    // BEATS = 96%
    public int maximalSquareRecursive1(char[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        //idea: stores max length of square's edge that has [i][j]-th cell as BOT right corner
        int[][] memo = new int[m][n];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        int res[] = new int[1];
        maximalSquareRecursive(matrix, 0, 0, memo, res);

        return res[0] * res[0];
    }

    //fill from [m-1][n-1] to [0][0], but start with top [0][0] and move down to [m-1][n-1]
    private int maximalSquareRecursive(char[][] matrix, int i, int j, int[][] memo, int[] res) {
        if (i == matrix.length || j == matrix[0].length) {
            return 0;
        }

        if (memo[i][j] != -1) {
            return memo[i][j];
        }

        //NOTE: we need to go into all 3 directions! independently from the value of matrix[i][j]!
        //because otherwise we will not traverse all matrix.
        // Case:
        // [1,0,1,1]
        // [0,0,1,1]

        //but we collect the result of the 'ways' if it makes sense, i.e. matrix[i][j]. Otherwise store 0
        int way1 = maximalSquareRecursive(matrix, i + 1, j, memo, res);
        int way2 = maximalSquareRecursive(matrix, i, j + 1, memo, res);
        int way3 = maximalSquareRecursive(matrix, i + 1, j + 1, memo, res);
        if (matrix[i][j] == '1') {
            int minWay = Math.min(way1, Math.min(way2, way3));
            memo[i][j] = 1 + minWay;    //1 = matrix[i][j]
        } else {
            memo[i][j] = 0;
        }
        res[0] = Math.max(res[0], memo[i][j]);  //update max length of square's edge

        return memo[i][j];
    }

    /**
     * Recursive solution from
     * https://leetcode.com/problems/maximal-square/solutions/4918596/recursive-and-iterative-approach/?envType=list&envId=55ac4kuc
     * <p>
     * Idea: the same as for 'maximalSquareRecursive1', BUT execute recursive call only for cases when matrix[i][j] = '1'
     */
    public int maximalSquareRecursive2(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        int m = matrix.length;
        int n = matrix[0].length;
        int[][] memo = new int[m][n];
        for (int[] row : memo) {
            Arrays.fill(row, -1); // Initialize memoization array with -1
        }

        int maxSide = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    maxSide = Math.max(maxSide, maximalSquareRecursive2(matrix, i, j, memo));
                }
            }
        }

        return maxSide * maxSide;
    }

    private int maximalSquareRecursive2(char[][] matrix, int i, int j, int[][] memo) {
        if (i == matrix.length || j == matrix[0].length || matrix[i][j] == '0') {
            return 0;
        }

        if (memo[i][j] != -1) {
            return memo[i][j];
        }

        int right = maximalSquareRecursive2(matrix, i, j + 1, memo);
        int down = maximalSquareRecursive2(matrix, i + 1, j, memo);
        int diagonal = maximalSquareRecursive2(matrix, i + 1, j + 1, memo);

        memo[i][j] = 1 + Math.min(Math.min(right, down), diagonal);
        return memo[i][j];
    }
}
