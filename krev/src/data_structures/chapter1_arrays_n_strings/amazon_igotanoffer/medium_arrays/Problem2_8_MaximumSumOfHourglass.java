package data_structures.chapter1_arrays_n_strings.amazon_igotanoffer.medium_arrays;

/**
 * https://igotanoffer.com/blogs/tech/array-interview-questions
 * https://leetcode.com/problems/maximum-sum-of-an-hourglass/description/
 * <p>
 * You are given an m x n integer matrix grid.
 * <p>
 * We define an hourglass as a part of the matrix with the following form:
 * <p>
 * <p>
 * Return the maximum sum of the elements of an hourglass.
 * <p>
 * Note that an hourglass cannot be rotated and must be entirely contained within the matrix.
 * <p>
 * Example 1:
 * Input: grid = [[6,2,1,3],[4,2,1,5],[9,2,8,7],[4,1,2,9]]
 * Output: 30
 * Explanation: The cells shown above represent the hourglass with the maximum sum: 6 + 2 + 1 + 2 + 9 + 2 + 8 = 30.
 * <p>
 * Constraints:
 * <p>
 * m == grid.length
 * n == grid[i].length
 * 3 <= m, n <= 150
 * 0 <= grid[i][j] <= 10^6
 */
public class Problem2_8_MaximumSumOfHourglass {
    /**
     * KREVSKY SOLUTION
     * idea - NO IDEA! just for inside for loop!
     * time complexity ~ O(c*r)
     * space complexity ~ O(1)
     * time to solve ~ 15 mins
     * 1 attempt
     */
    public int maxSum(int[][] grid) {
        int result = Integer.MIN_VALUE;
        int r = grid.length;
        int c = grid[0].length;
        if (r < 3 || c < 3) return result;

        //i - row, j - column
        for (int i = 0; i < r - 2; i++) {
            for (int j = 0; j < c - 2; j++) {
                int tempSum = grid[i][j] + grid[i][j + 1] + grid[i][j + 2] +
                        grid[i + 1][j + 1] +
                        grid[i + 2][j] + grid[i + 2][j + 1] + grid[i + 2][j + 2];
                result = Math.max(result, tempSum);
            }
        }

        return result;
    }

}
