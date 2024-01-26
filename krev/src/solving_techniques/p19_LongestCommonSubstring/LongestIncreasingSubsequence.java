package solving_techniques.p19_LongestCommonSubstring;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * https://www.designgurus.io/course-play/grokking-dynamic-programming/doc/637f610cf8306ce7e23a5e0e
 * OR
 * 300. Longest Increasing Subsequence
 * https://leetcode.com/problems/longest-increasing-subsequence/description/
 *
 * Given an integer array nums, return the length of the longest strictly increasing subsequence
 *
 * Example 1:
 * Input: nums = [10,9,2,5,3,7,101,18]
 * Output: 4
 * Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
 *
 * Example 2:
 * Input: nums = [0,1,0,3,2,3]
 * Output: 4
 *
 * Example 3:
 * Input: nums = [7,7,7,7,7,7,7]
 * Output: 1
 *
 * Constraints:
 *
 * 1 <= nums.length <= 2500
 * -10^4 <= nums[i] <= 10^4
 *
 *
 * Follow up: Can you come up with an algorithm that runs in O(n log(n)) time complexity?
 */
public class LongestIncreasingSubsequence {
    public static void main(String[] args) {
        int[] arr1 = {4,1,2,6,10,1,12};
        System.out.println(lengthOfLI_2(arr1)); //5
    }
    /**
     * KREVSKY SOLUTION:
     * idea:
     * time to solve ~ 35 mins
     * 2 attempts:
     * - did not remember java methods to sort of Set and to get value from it => had to create unique array
     *
     * PROBLEM: time ~ O(n^2) - since I don't know how to avoid using of 2d dp array
     * space ~ O(n^2)
     */
    public int lengthOfLIS(int[] nums) {
        Set<Integer> set = new HashSet();
        for (int n : nums) {
            set.add(n);
        }

        Integer[] unique = set.toArray(new Integer[0]);

        Arrays.sort(unique);    //O(n*logn)

        int result = 0;
        int[][] dp = new int[nums.length + 1][unique.length + 1];

        //don't know how to avoid using 2d dp array
        for (int i = 1; i < nums.length + 1; i++) {
            for (int j = 1; j < unique.length + 1; j++) {
                if (nums[i-1] == unique[j-1]) {
                    dp[i][j] = 1 + dp[i-1][j-1];
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
                result = Math.max(result, dp[i][j]);
            }
        }

        return result;
    }

    /**
     * https://leetcode.com/problems/longest-increasing-subsequence/submissions/1144317586/
     * and the same is here https://youtu.be/fV-TF4OvZpk?t=463
     * time ~ O(n^2)
     * space ~ O(n)
     * But without sorting
     */
    public static int lengthOfLI_2(int[] nums) {
        // Initialize an array dp with the same length as nums and fill it with 1s
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);

        // Iterate through each element in nums
        for (int i = 1; i < nums.length; i++) {
            // Iterate through all previous elements
            for (int j = 0; j < i; j++) {
                // If nums[j] < nums[i], update dp[i]
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }
            }
        }

        // Find the maximum value in the dp array
        int maxLength = 0;
        for (int len : dp) {
            maxLength = Math.max(maxLength, len);
        }

        // Return the maximum value
        return maxLength;
    }

    /**
     * idea: use Binary Search to reach time ~ O(n*logn)
     * https://www.youtube.com/watch?v=on2hvxBXJH4 - replace the closest value in temporary array with arr[i] if arr[i] > the latest element of thie temp arrsy.
     * VERY STRANGE SOLUTION
     */

    //looks like smth as MinimumDeletionsToMakeSequenceSorted # minDeletionsGFG, but still do not understand
}
