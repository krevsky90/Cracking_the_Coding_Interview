package solving_techniques.different;

/**
 * 498. Diagonal Traverse (medium)
 * https://leetcode.com/problems/diagonal-traverse
 * <p>
 * #Company: Amazon eBay Facebook Google Microsoft Quora Splunk Walmart Labs
 * <p>
 * Given an m x n matrix mat, return an array of all the elements of the array in a diagonal order.
 * <p>
 * Example 1:
 * Input: mat = [[1,2,3],[4,5,6],[7,8,9]]
 * Output: [1,2,4,7,5,3,6,8,9]
 * <p>
 * Example 2:
 * Input: mat = [[1,2],[3,4]]
 * Output: [1,2,3,4]
 * <p>
 * Constraints:
 * m == mat.length
 * n == mat[i].length
 * 1 <= m, n <= 10^4
 * 1 <= m * n <= 10^4
 * -10^5 <= mat[i][j] <= 10^5
 */
public class DiagonalTraverse {
    /**
     * KREVSKY SOLUTION:
     * time to solve ~ 30 mins
     * time ~ O(mat.length * mat[0].length)
     * space ~ O(mat.length * mat[0].length) - space for result array
     *
     * 2 attempts:
     * - wrote if (direction) and if (!direction). Then fixed as if (direction) else ...
     *
     * BEATS ~ 70%
     */
    public int[] findDiagonalOrder(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        int[] result = new int[n * m];

        int i = 0;
        int j = 0;
        boolean direction = true;
        int k = 0;
        while (i < m && j < n) {
            if (direction) {
                while (i > 0 && j < n - 1) {
                    result[k] = mat[i][j];
                    k++;
                    i--;
                    j++;
                }

                direction = !direction;
                result[k] = mat[i][j];
                k++;

                if (i == 0 && j < n - 1) {
                    //reach only upper bound
                    j++;
                } else if (i > 0 && j == n - 1) {
                    i++;
                    //also might be in the right bottom corner. in this case we will go out of bounds and stop traversing
                } else {
                    //i.e. i == 0 && j == n - 1 (right upper corner)
                    i++;
                }
            } else {
                while (i < m - 1 && j > 0) {
                    result[k] = mat[i][j];
                    k++;
                    i++;
                    j--;
                }

                direction = !direction;
                result[k] = mat[i][j];
                k++;

                if (j == 0 && i < m - 1) {
                    //reach left bound
                    i++;
                } else if (i == m - 1 && j > 0) {
                    //reach bottom bound
                    j++;
                    //also might be in the right bottom corner. in this case we will go out of bounds and stop traversing
                } else {
                    //i.e. i = m - 1 && j = 0 (left bottom corner)
                    j++;
                }
            }
        }

        return result;
    }
}
