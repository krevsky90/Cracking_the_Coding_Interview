package solving_techniques.p11_BinarySearch;

/**
 * 74. Search a 2D Matrix (medium)
 * https://leetcode.com/problems/search-a-2d-matrix/
 * #Company (19.04.2025): 0 - 3 months Google 8 Meta 6 Microsoft 6 TikTok 2 0 - 6 months Amazon 9 Bloomberg 5 Goldman Sachs 2 6 months ago Apple 8 Oracle 8 Adobe 6 Tinkoff 4 Walmart Labs 4 Cisco 4 Yahoo 4 Yandex 3 Coupang 3 Infosys 2
 * <p>
 * You are given an m x n integer matrix matrix with the following two properties:
 * <p>
 * Each row is sorted in non-decreasing order.
 * The first integer of each row is greater than the last integer of the previous row.
 * Given an integer target, return true if target is in matrix or false otherwise.
 * <p>
 * You must write a solution in O(log(m * n)) time complexity.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
 * Output: true
 * <p>
 * Example 2:
 * Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
 * Output: false
 * <p>
 * Constraints:
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 100
 * -104 <= matrix[i][j], target <= 104
 */
public class Search2DMatrix {
    /**
     * Original solution:
     * idea: consider 2D array as 1D array with idx for all cells from 0 to n*m - 1
     * <p>
     * time ~ O(logmn)
     */
    public boolean searchMatrixOfficial(int[][] matrix, int target) {
        int m = matrix.length;
        if (m == 0) return false;
        int n = matrix[0].length;

        // binary search
        int left = 0, right = m * n - 1;
        int pivotIdx, pivotElement;
        while (left <= right) {
            pivotIdx = (left + right) / 2;
            pivotElement = matrix[pivotIdx / n][pivotIdx % n];
            if (target == pivotElement) return true;
            else {
                if (target < pivotElement) right = pivotIdx - 1;
                else left = pivotIdx + 1;
            }
        }
        return false;
    }

    /**
     * KREVSKY SOLUTION:
     * not optimal
     * <p>
     * time to solve ~ 14 mins
     * time ~ O(logn + logm)
     * space ~ O(1)
     * <p>
     * 1 attempt
     * <p>
     * BEATS ~ 100%
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        int n = matrix.length;
        int m = matrix[0].length;
        //1. find row that contains target
        int low = 0;
        int high = n - 1;
        int targetRow = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (matrix[mid][0] < target) {
                if (target <= matrix[mid][m - 1]) {
                    targetRow = mid;
                    break;
                } else {
                    low = mid + 1;
                }
            } else if (matrix[mid][0] == target) {
                return true;
            } else {
                high = mid - 1;
            }
        }

        if (targetRow == -1) return false;

        //find target in 'targetRow' row
        low = 0;
        high = m - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (target == matrix[targetRow][mid]) {
                return true;
            } else if (target < matrix[targetRow][mid]) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return false;
    }
}
