package solving_techniques.different;

/**
 * 766. Toeplitz Matrix (easy)
 * https://leetcode.com/problems/toeplitz-matrix
 *
 * #Company (18.02.2025): 0 - 3 months Meta 10 6 months ago tcs 4 Google 3
 *
 * Given an m x n matrix, return true if the matrix is Toeplitz. Otherwise, return false.
 *
 * A matrix is Toeplitz if every diagonal from top-left to bottom-right has the same elements.
 *
 * Example 1:
 * Input: matrix = [[1,2,3,4],[5,1,2,3],[9,5,1,2]]
 * Output: true
 * Explanation:
 * In the above grid, the diagonals are:
 * "[9]", "[5, 5]", "[1, 1, 1]", "[2, 2, 2]", "[3, 3]", "[4]".
 * In each diagonal all elements are the same, so the answer is True.
 *
 * Example 2:
 * Input: matrix = [[1,2],[2,2]]
 * Output: false
 * Explanation:
 * The diagonal "[1, 2]" has different elements.
 *
 * Constraints:
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 20
 * 0 <= matrix[i][j] <= 99
 *
 * Follow up:
 *
 * What if the matrix is stored on disk, and the memory is limited such that you can only load at most one row of the matrix into the memory at once?
 * What if the matrix is so large that you can only load up a partial row into the memory at once?
 *
 */
public class ToeplitzMatrix {
    /**
     * time ~ O(n*m)
     * space ~ O(1)
     *
     * BEATS ~ 26%
     */
    public boolean isToeplitzMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return true;

        int n = matrix.length;
        int m = matrix[0].length;
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                if (r > 0 && c > 0 && matrix[r][c] != matrix[r-1][c-1]) return false;
            }
        }

        return true;
    }
}
