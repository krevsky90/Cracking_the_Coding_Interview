package solving_techniques.p15_0or1_Knapsack;

import java.util.Arrays;

/**
 * 474. Ones and Zeroes
 * https://leetcode.com/problems/ones-and-zeroes
 * <p>
 * You are given an array of binary strings strs and two integers m and n.
 * Return the size of the largest subset of strs such that there are at most m 0's and n 1's in the subset.
 * A set x is a subset of a set y if all elements of x are also elements of y.
 * <p>
 * Example 1:
 * Input: strs = ["10","0001","111001","1","0"], m = 5, n = 3
 * Output: 4
 * Explanation: The largest subset with at most 5 0's and 3 1's is {"10", "0001", "1", "0"}, so the answer is 4.
 * Other valid but smaller subsets include {"0001", "1"} and {"10", "1", "0"}.
 * {"111001"} is an invalid subset because it contains 4 1's, greater than the maximum of 3.
 * <p>
 * Example 2:
 * Input: strs = ["10","0","1"], m = 1, n = 1
 * Output: 2
 * Explanation: The largest subset is {"0", "1"}, so the answer is 2.
 * <p>
 * Constraints:
 * 1 <= strs.length <= 600
 * 1 <= strs[i].length <= 100
 * strs[i] consists only of digits '0' and '1'.
 * 1 <= m, n <= 100
 */
public class OnesAndZeroes {
    /**
     * KREVSKY SOLUTION #1.1:
     * time to solve ~ 32 mins
     * <p>
     * idea: knapsack 0/1 - i.e. to take or not to take
     * + memo. we can't set 2D-array, since n, m  and strs.length are independent => we need 3D-array
     * <p>
     * base cases:
     * 1) capacity = 0, i.e. n = 0 AND m = 0
     * 2) we reach end of strs, i.e. idx = strs.length
     * NOTE: to prevent n or m < 0, we validate it before 'include' case.
     *
     * <p>
     * 3 attempts:
     * - forgot ".toCharArray()"
     * - forgot "+1" to each size of memo array
     * <p>
     * BEATS = 11%
     */
    public int findMaxForm(String[] strs, int m, int n) {
        int[][][] memo = new int[m + 1][n + 1][strs.length + 1];
        return getMaxlength(strs, m, n, 0, memo);
    }

    public int getMaxlength(String[] strs, int m, int n, int idx, int[][][] memo) {
        if (m == 0 && n == 0) {
            return 0;
        }

        if (idx == strs.length) {
            return 0;
        }

        if (memo[m][n][idx] > 0) return memo[m][n][idx];

        //case1: do not include idx-th element  to subset
        int notIncludeResult = getMaxlength(strs, m, n, idx + 1, memo);

        //case 2: include idx-th element to subset
        int includeResult = -1;
        //check if we can include it:
        int count0 = 0;
        int count1 = 0;
        for (char c : strs[idx].toCharArray()) {
            if (c == '0') count0++;
            if (c == '1') count1++;
        }

        if (m >= count0 && n >= count1) {
            includeResult = 1 + getMaxlength(strs, m - count0, n - count1, idx + 1, memo);
        }

        return memo[m][n][idx] = Math.max(notIncludeResult, includeResult);
    }

    /**
     * SOLUTION #1.2
     * info:
     * https://leetcode.com/problems/ones-and-zeroes/solutions/3814032/top-down-approach-java-o-s-n-m/
     * the idea is the same, but it handles n <0 || m < 0 as base case
     *
     * BEATS = 24%
     */
    public int findMaxForm2(String[] strs, int m, int n) {
        int[][][] memo = new int[strs.length + 1][m + 1][n + 1];
        for (int[][] arr : memo) for (int[] row : arr) Arrays.fill(row, -1);
        return getMaxSubstring(strs.length, m, n, strs, memo);
    }

    private int getMaxSubstring(int index, int m, int n, String[] nums, int[][][] memo) {
        if (m < 0 || n < 0) return -1;
        if (index == 0) return 0;
        if (memo[index][m][n] != -1) return memo[index][m][n];

        int numOnes = countOnes(nums[index - 1]);
        int numZeros = nums[index - 1].length() - numOnes;
        memo[index][m][n] = Math.max(
                1 + getMaxSubstring(index - 1, m - numZeros, n - numOnes, nums, memo),
                getMaxSubstring(index - 1, m, n, nums, memo)
        );
        return memo[index][m][n];
    }

    private int countOnes(String str) {
        int count = 0;
        for (char c : str.toCharArray()) if (c == '1') count++;
        return count;
    }

    /**
     * SOLUTION #2: botton-up
     * info:
     * https://leetcode.com/problems/ones-and-zeroes/solutions/2704404/java-solution-very-intuitive/
     * IMHO it is difficult to imagine 3D-array
     */
}
