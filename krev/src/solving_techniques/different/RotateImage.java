package solving_techniques.different;

/**
 * 48. Rotate Image (medium)
 * https://leetcode.com/problems/rotate-image
 * <p>
 * #Company: Adobe Akuna Capital Amazon Apple Bloomberg Cisco Facebook Google Groupon Houzz Lyft Microsoft Nvidia Palantir Technologies Quora Salesforce Samsung Wish Yandex
 * <p>
 * You are given an n x n 2D matrix representing an image, rotate the image by 90 degrees (clockwise).
 * <p>
 * You have to rotate the image in-place, which means you have to modify the input 2D matrix directly.
 * DO NOT allocate another 2D matrix and do the rotation.
 * <p>
 * Example 1:
 * Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * Output: [[7,4,1],[8,5,2],[9,6,3]]
 * <p>
 * Example 2:
 * Input: matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
 * Output: [[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
 * <p>
 * Constraints:
 * <p>
 * n == matrix.length == matrix[i].length
 * 1 <= n <= 20
 * -1000 <= matrix[i][j] <= 1000
 */
public class RotateImage {
    /**
     * KREVSKY SOLUTION:
     * time to solve ~ 20 mins
     * <p>
     * time ~ O(n^2)
     * space ~ O(1)
     * <p>
     * BEATS ~ 100%
     * <p>
     * 1 attempt
     */
    public void rotateKrev1(int[][] matrix) {
        int n = matrix.length;
        int cache1 = 0;
        int cache2 = 0;
        for (int i = 0; i < (n % 2 == 0 ? n / 2 : n / 2 + 1); i++) {
            for (int j = i; j < n - 1 - i; j++) {
                cache1 = matrix[j][n - 1 - i];
                matrix[j][n - 1 - i] = matrix[i][j];

                cache2 = matrix[n - 1 - i][n - 1 - j];
                matrix[n - 1 - i][n - 1 - j] = cache1;

                cache1 = matrix[n - 1 - j][i];
                matrix[n - 1 - j][i] = cache2;

                matrix[i][j] = cache1;
            }
        }
    }

    /**
     * KREVSKY SOLUTION (21.06.2025)
     * time to solve ~ 20 mins
     * looks more simple than solution Krev1
     * <p>
     * 2 attempts:
     * - incorrect condition (equals part): i <= high - low
     */
    public void rotateKrev2(int[][] matrix) {
        if (matrix == null || matrix.length <= 1) return;

        int n = matrix.length;
        int low = 0;
        int high = n - 1;
        int cached = 0;
        while (low < high) {
            for (int i = 0; i < high - low; i++) {
                cached = matrix[low][low + i];
                matrix[low][low + i] = matrix[high - i][low];
                matrix[high - i][low] = matrix[high][high - i];
                matrix[high][high - i] = matrix[low + i][high];
                matrix[low + i][high] = cached;
            }
            low++;
            high--;
        }
    }

    /**
     * SOLUTION #2:
     * info: https://leetcode.com/problems/rotate-image/solutions/5835823/most-optimal-java-code-with-transpose-and-reverse-approach-beats-100-tc-o-n-2-sc-o-1/
     * idea: Transpose + vertical reflection
     */
    public void rotate2(int[][] matrix) {
        int n = matrix.length;

        // Transpose the matrix
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        // Reverse each row
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n / 2; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][n - 1 - j];
                matrix[i][n - 1 - j] = temp;
            }
        }
    }

}
