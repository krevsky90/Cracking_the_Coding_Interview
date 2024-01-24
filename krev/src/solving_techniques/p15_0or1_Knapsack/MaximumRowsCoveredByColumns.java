package solving_techniques.p15_0or1_Knapsack;

/**
 * 2397. Maximum Rows Covered by Columns
 * https://leetcode.com/problems/maximum-rows-covered-by-columns
 * <p>
 * You are given a 0-indexed m x n binary matrix matrix and an integer numSelect,
 * which denotes the number of distinct columns you must select from matrix.
 * <p>
 * Let us consider s = {c1, c2, ...., cnumSelect} as the set of columns selected by you. A row row is covered by s if:
 * 1) For each cell matrix[row][col] (0 <= col <= n - 1) where matrix[row][col] == 1, col is present in s or,
 * 2) No cell in row has a value of 1.
 * <p>
 * You need to choose numSelect columns such that the number of rows that are covered is maximized.
 * Return the maximum number of rows that can be covered by a set of numSelect columns.
 * <p>
 * Example 1:
 * Input: matrix = [[0,0,0],[1,0,1],[0,1,1],[0,0,1]], numSelect = 2
 * Output: 3
 * Explanation: One possible way to cover 3 rows is shown in the diagram above.
 * We choose s = {0, 2}.
 * - Row 0 is covered because it has no occurrences of 1.
 * - Row 1 is covered because the columns with value 1, i.e. 0 and 2 are present in s.
 * - Row 2 is not covered because matrix[2][1] == 1 but 1 is not present in s.
 * - Row 3 is covered because matrix[2][2] == 1 and 2 is present in s.
 * Thus, we can cover three rows.
 * Note that s = {1, 2} will also cover 3 rows, but it can be shown that no more than three rows can be covered.
 * <p>
 * Example 2:
 * Input: matrix = [[1],[0]], numSelect = 1
 * Output: 2
 * Explanation: Selecting the only column will result in both rows being covered since the entire matrix is selected.
 * Therefore, we return 2.
 * <p>
 * Constraints:
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 12
 * matrix[i][j] is either 0 or 1.
 * 1 <= numSelect <= n
 */
public class MaximumRowsCoveredByColumns {
    /**
     * KREVSKY SOLUTION:
     * idea: recursion + check 2 cases each time (as for Knapsack0/1): include current (j-th) column or not
     * base case 1: amount of selected columns = numSelected. In this case we count the amount of 0s rows and return
     * base case 2: we reach n-th column (i.e. index out of bound, but the amount of selected columns still < numSelected. Then return 0
     * <p>
     * main logic:
     * include = func(currentColumnIdx + 1, numSelected - 1)
     * exclude = func(currentColumnIdx + 1, numSelected)
     * result = max(include, exclude)
     * <p>
     * NOTE: for include case we use backracking to set 0s to currentColumnIdx-th column of matrix and go recursively. BUT after that we have to restore the matrix's state
     * <p>
     * time to solve ~ 110 mins
     * <p>
     * a lot of attempts, because tried to use dp-table. But it works fast without it
     */
    public int maximumRows(int[][] matrix, int numSelect) {
    }
        return

    maximumRows(matrix, numSelect, 0,dp);

}


    // matrix:
    //     0 0 1
    //     0 1 0
    //     0 1 0
    // max(2, 0) = 3
    //     tempCol = [0 0 0]
    //     include = max(1, 1) = 2
    //                 tempCol = 0 1 1
    //                 include = max(0, 2) = 2
    //                 exclude = max(1, 2) = 1
    //                             tempCol = 1 0 0
    //                             include = max(0, 3) = 1
    //                             exclude = max(1, 3) = 0
    //     exclude = max(2, 1) = 3
    //                 tempCol = 0 1 1
    //                 include = max(1, 2) = 3
    //                             tempCol = 1 0 0
    //                             include = max(0, 3) = 3
    //                             exclude = max(1, 3) = 0
    //                 exclude = max(2, 2) = 0
    //
    public int maximumRows(int[][] matrix, int numSelect, int j, int[] dp) {
        if (numSelect == 0) {
            int result = 0;
            //calculate amount of rows that contains only 0s
            for (int i = 0; i < matrix.length; i++) {
                int tempSum = 0;
                for (int k = 0; k < matrix[0].length; k++) {
                    tempSum += matrix[i][k];
                }
                if (tempSum == 0) {
                    result++;
                }
            }
            return result;
        }

        //if we are here => numSelect > 0
        if (j == matrix[0].length) {
            return 0;   //we select less than numSelect columns went out of matrix's bound
        }

        //choose j-th column:
        //1) change matrix:
        int[] tempCol = new int[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            tempCol[i] = matrix[i][j];
            matrix[i][j] = 0;
        }
        //2) recursive call
        int include = maximumRows(matrix, numSelect - 1, j + 1, dp);
        //3) restore the j-th column of matrix
        for (int i = 0; i < matrix.length; i++) {
            matrix[i][j] = tempCol[i];
        }

        //do not choose j-th column:
        int exclude = maximumRows(matrix, numSelect, j + 1, dp);
        int result = Math.max(include, exclude);
        return result;
    }

    /**
     * https://leetcode.com/problems/maximum-rows-covered-by-columns/solutions/2536364/simple-backtracking-java-solution/
     * the same idea, BUT without backtracking
     */
    public int calculateNumberOfRows(int[][] mat, Set<Integer> cols) {
        int n = mat.length, m = mat[0].length;
        int cnt = 0;
        int i, j;
        for (i = 0; i < n; i++) {
            for (j = 0; j < m; j++) {
                if (mat[i][j] == 1 && !cols.contains(j)) break;
            }
            if (j == m) cnt += 1;
        }
        return cnt;
    }

    public int maximumRows(int[][] mat, int cols, Set<Integer> col, int colNum, int n) {
        if (cols == 0) {
            return calculateNumberOfRows(mat, col);
        }
        if (colNum >= n) return Integer.MIN_VALUE;

        //pick
        col.add(colNum);
        int pick = maximumRows(mat, cols - 1, col, colNum + 1, n);
        col.remove(colNum);

        //not pick
        int notPick = maximumRows(mat, cols, col, colNum + 1, n);
        return Math.max(pick, notPick);
    }

    public int maximumRows(int[][] mat, int cols) {
        Set<Integer> col = new HashSet<>();
        return maximumRows(mat, cols, col, 0, mat[0].length);
    }
}
