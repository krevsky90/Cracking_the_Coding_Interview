package solving_techniques.p19_LongestCommonSubstring;

/**
 * 674. Longest Continuous Increasing Subsequence (easy)
 * https://leetcode.com/problems/longest-continuous-increasing-subsequence
 * <p>
 * Given an unsorted array of integers nums, return the length of the longest continuous increasing subsequence
 * (i.e. subarray). The subsequence must be strictly increasing.
 * <p>
 * A continuous increasing subsequence is defined by two indices l and r (l < r) such that
 * it is [nums[l], nums[l + 1], ..., nums[r - 1], nums[r]] and for each l <= i < r, nums[i] < nums[i + 1].
 * <p>
 * Example 1:
 * Input: nums = [1,3,5,4,7]
 * Output: 3
 * Explanation: The longest continuous increasing subsequence is [1,3,5] with length 3.
 * Even though [1,3,5,7] is an increasing subsequence, it is not continuous as elements 5 and 7 are separated by element 4.
 * <p>
 * Example 2:
 * Input: nums = [2,2,2,2,2]
 * Output: 1
 * Explanation: The longest continuous increasing subsequence is [2] with length 1. Note that it must be strictly increasing.
 * <p>
 * Constraints:
 * 1 <= nums.length <= 10^4
 * -10^9 <= nums[i] <= 10^9
 */
public class LongestContinuousIncreasingSubsequence {
    /**
     * KREVSKY SOLUTION:
     * time to solve ~ 11 mins
     * <p>
     * 2 attempts:
     * - tried to return "Math.max(result, end - start)" out of for-loop
     *
     * BEATS = 99%
     */
    public int findLengthOfLCIS(int[] nums) {
        int result = 1;
        int start = 0;
        for (int end = 1; end < nums.length; end++) {
            if (nums[end] <= nums[end - 1]) {
                result = Math.max(result, end - start);
                start = end;
            }
        }

        return Math.max(result, nums.length - start);
    }
}