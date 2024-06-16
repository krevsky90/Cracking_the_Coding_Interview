package solving_techniques.different;

/**
 * 73. Set Matrix Zeroes
 * https://leetcode.com/problems/set-matrix-zeroes
 * #Company: Apple
 * <p>
 * Given an m x n integer matrix matrix, if an element is 0, set its entire row and column to 0's.
 * You must do it in place.
 * <p>
 * Example 1:
 * Input: matrix = [[1,1,1],[1,0,1],[1,1,1]]
 * Output: [[1,0,1],[0,0,0],[1,0,1]]
 * <p>
 * Example 2:
 * Input: matrix = [[0,1,2,0],[3,4,5,2],[1,3,1,5]]
 * Output: [[0,0,0,0],[0,4,5,0],[0,3,1,0]]
 * <p>
 * Constraints:
 * m == matrix.length
 * n == matrix[0].length
 * 1 <= m, n <= 200
 * -2^31 <= matrix[i][j] <= 2^31 - 1
 * <p>
 * Follow up:
 * A straightforward solution using O(mn) space is probably a bad idea.
 * A simple improvement uses O(m + n) space, but still not the best solution.
 * Could you devise a constant space solution?
 */
public class SetMatrixZeroes {
    /**
     * KREVSKY SOLUTION:
     * does not work for all cases. it passed 193 / 196
     * idea: to mark rows and columns that need to fill by 0s.
     * I did it storing info about i-th row in i-th position of some integer number.
     * but looks like this is wrong for matrix with size = 200, since it is too big number for integer.
     * <p>
     * time to solve ~ 25 mins
     * <p>
     * 3 attempts:
     * - forgot how to check i-th bit: like  if (((rowsToChange >> i) & 1) == 0)
     */
    public void setZeroesKrev(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        int columnsToChange = 0;
        int rowsToChange = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    if (((rowsToChange >> i) & 1) == 0) {
                        rowsToChange += 1 << i;
                    }

                    if (((columnsToChange >> j) & 1) == 0) {
                        columnsToChange += 1 << j;
                    }
                }
            }
        }

        for (int i = 0; i < m; i++) {
            if (((rowsToChange >> i) & 1) == 1) {
                for (int j = 0; j < n; j++) {
                    matrix[i][j] = 0;
                }
            }
        }

        for (int j = 0; j < n; j++) {
            if (((columnsToChange >> j) & 1) == 1) {
                for (int i = 0; i < m; i++) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    /**
     * info:
     * https://leetcode.com/problems/set-matrix-zeroes/solutions/5241541/super-easy-approach/
     * idea: store the info if i-th row should have 0s in separate array 'rows'
     * the same - for 'columns'
     * time ~ O(n*m)
     * space ~ O(n + m)
     *
     * BEATS = 77%
     */
    public void setZeroes(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int row[] = new int[n];
        int col[] = new int[m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == 0) {
                    row[i] = 1;
                    col[j] = 1;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (row[i] == 1 || col[j] == 1) {
                    matrix[i][j] = 0;
                }
            }
        }
    }


}
