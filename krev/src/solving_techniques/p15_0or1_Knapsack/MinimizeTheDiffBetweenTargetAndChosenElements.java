package solving_techniques.p15_0or1_Knapsack;

import java.util.Arrays;

/**
 * 1981. Minimize the Difference Between Target and Chosen Elements
 * https://leetcode.com/problems/minimize-the-difference-between-target-and-chosen-elements/
 *
 * You are given an m x n integer matrix mat and an integer target.
 * Choose one integer from each row in the matrix
 * such that the absolute difference between target and the sum of the chosen elements is minimized.
 *
 * Return the minimum absolute difference.
 * The absolute difference between two numbers a and b is the absolute value of a - b.
 *
 * Example 1:
 * Input: mat = [[1,2,3],[4,5,6],[7,8,9]], target = 13
 * Output: 0
 * Explanation: One possible choice is to:
 * - Choose 1 from the first row.
 * - Choose 5 from the second row.
 * - Choose 7 from the third row.
 * The sum of the chosen elements is 13, which equals the target, so the absolute difference is 0.
 *
 * Constraints:
 *
 * m == mat.length
 * n == mat[i].length
 * 1 <= m, n <= 70
 * 1 <= mat[i][j] <= 70
 * 1 <= target <= 800
 */
public class MinimizeTheDiffBetweenTargetAndChosenElements {
    /**
     * NOT SOLVED by myself!
     * https://leetcode.com/problems/minimize-the-difference-between-target-and-chosen-elements/solutions/1418693/java-short-and-concise-dp-memoize-solution/
     * idea:
     * dp - [mat.length][maxSum] - I reached it by myself
     * how to fill this dp - I failed to do this, trying to apply bottom-up approach and find any dependencise between the cells of dp.
     * BUT the right way is the like top-down approach with finding minumum every time, BUT storing it into dp!
     * <p>
     * time to solve ~ 90 mins
     * time ~ O(mat.length*maxSum)
     * space ~ O(mat.length*maxSum)
     * 1 attempt
     */
    public int minimizeTheDifference(int[][] mat, int target) {
        int maxSum = 5000;  //70*70 + 70 is maximum that should be considered. NOT SURE!
        int[][] dp = new int[mat.length][5000];
        for (int i = 0; i < mat.length; i++) {
            Arrays.fill(dp[i], -1);
        }

        return minimizeTheDifferenceHelper(mat, target, 0, 0, dp);
    }

    public int minimizeTheDifferenceHelper(int[][] mat, int target, int i, int tempSum, int[][] dp) {
        //base case:
        if (i == mat.length) {
            return Math.abs(target - tempSum);
        }

        //memoization
        if (dp[i][tempSum] != -1) {
            return dp[i][tempSum];
        }

        int result = Integer.MAX_VALUE;
        for (int j = 0; j < mat[0].length; j++) {
            result = Math.min(result, minimizeTheDifferenceHelper(mat, target, i + 1, tempSum + mat[i][j], dp));
        }

        //memoization
        dp[i][tempSum] = result;
        return dp[i][tempSum];
    }
}

