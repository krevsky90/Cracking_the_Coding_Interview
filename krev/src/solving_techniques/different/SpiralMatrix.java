package solving_techniques.different;

import java.util.ArrayList;
import java.util.List;

/**
 * 54. Spiral Matrix (medium)
 * https://leetcode.com/problems/spiral-matrix
 * <p>
 * #Company: 0 - 3 months Amazon 13 Google 7 Cisco 7 Capital One 6 Meta 5 Microsoft 5 Oracle 3 Epic Systems 3 Uber 3 Bloomberg 2 0 - 6 months Zoho 15 TikTok 3 IBM 2 Apple 2 Walmart Labs 2 Nordstrom 2 Intuit 2 Lenskart 2 Databricks 2 Roblox 2 6 months ago Adobe 27 Accenture 7 Yahoo 7 tcs 5 Goldman Sachs 3
 * <p>
 * Given an m x n matrix, return all elements of the matrix in spiral order.
 * <p>
 * Example 1:
 * Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * Output: [1,2,3,6,9,8,7,4,5]
 * <p>
 * Example 2:
 * Input: matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
 * Output: [1,2,3,4,8,12,11,10,9,5,6,7]
 * <p>
 * Constraints:
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 10
 * -100 <= matrix[i][j] <= 100
 */
public class SpiralMatrix {
    /**
     * KREVSKY SOLUTION:
     * idea:
     * 1) change direction, traverse and save the element until we reach the bound
     * and move bounds once we reach them
     * 2) stop condition - when left > right || top > bottom
     * <p>
     * time to solve ~ 20 mins
     * <p>
     * time ~ O(N*M)
     * space ~ O(1) if we don't count memory for given matrix
     * <p>
     * 1 attempt
     * <p>
     * BEATS ~ 100%
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        int direction = 0;
        int i = 0;
        int j = 0;
        int left = 0;
        int right = matrix[0].length - 1;
        int top = 0;
        int bottom = matrix.length - 1;
        List<Integer> result = new ArrayList<>();

        while (left <= right && top <= bottom) {
            if (direction % 4 == 0) {
                while (j < right) {
                    result.add(matrix[i][j]);
                    j++;
                }
                top++;
            } else if (direction % 4 == 1) {
                while (i < bottom) {
                    result.add(matrix[i][j]);
                    i++;
                }
                right--;
            } else if (direction % 4 == 2) {
                while (j > left) {
                    result.add(matrix[i][j]);
                    j--;
                }
                bottom--;
            } else if (direction % 4 == 3) {
                while (i > top) {
                    result.add(matrix[i][j]);
                    i--;
                }
                left++;
            }

            direction++;
        }

        result.add(matrix[i][j]);

        return result;
    }
}
