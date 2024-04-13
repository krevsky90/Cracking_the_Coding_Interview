package solving_techniques.p19_LongestCommonSubstring;

import java.util.Arrays;

/**
 * 673. Number of Longest Increasing Subsequence
 * https://leetcode.com/problems/number-of-longest-increasing-subsequence
 * <p>
 * Given an integer array nums, return the number of longest increasing subsequences.
 * <p>
 * Notice that the sequence has to be strictly increasing.
 * <p>
 * Example 1:
 * Input: nums = [1,3,5,4,7]
 * Output: 2
 * Explanation: The two longest increasing subsequences are [1, 3, 4, 7] and [1, 3, 5, 7].
 * <p>
 * Example 2:
 * Input: nums = [2,2,2,2,2]
 * Output: 5
 * Explanation: The length of the longest increasing subsequence is 1,
 * and there are 5 increasing subsequences of length 1, so output 5.
 * <p>
 * Constraints:
 * 1 <= nums.length <= 2000
 * -10^6 <= nums[i] <= 10^6
 */
public class NumberOfLongestIncreasingSubsequence {
    /**
     * NOT SOLVED by myself
     * got only some pieces of the ideas
     * full explanation is in 'Editorial' section: https://leetcode.com/problems/number-of-longest-increasing-subsequence/editorial/
     */
    public int findNumberOfLIS(int[] nums) {
        int[] dp = new int[nums.length];    //stores LIS for subsequence that starts at 0-th element and ends with i-th element
        int[] ways = new int[nums.length];  //stores number of ways to track the amount of LIS that end at nums[i]
        Arrays.fill(dp, 1);
        Arrays.fill(ways, 1);
        int maxLisLength = 1;

        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    if (dp[i] < dp[j] + 1) {
                        //idea: it means we have found a longer subsequence ending at index i.
                        // In this case, we update dp[i] to dp[j]+1.
                        // Additionally, we discard any subsequences we saw earlier since they are no longer LIS: reset ways[i] to zero.
                        dp[i] = dp[j] + 1;
                        ways[i] = 0;
                        maxLisLength = Math.max(maxLisLength, dp[i]);
                    }

                    if (dp[i] == dp[j] + 1) {
                        //idea: it implies that we can extend every LIS ending at index j with the element nums[i]
                        // to create new longest increasing subsequences ending at index i.
                        //Therefore, we add ways[j] to ways[i] to count all subsequences that include both indices j and i
                        //Note that dp[i] might have just become dp[j]+1 during the previous step.
                        ways[i] += ways[j];
                    }
                }
            }
        }

        int result = 0;
        //we calculate total amount of LISs with length = maxLisLength
        for (int i = 0; i < dp.length; i++) {
            if (dp[i] == maxLisLength) {
                result += ways[i];
            }
        }

        return result;
    }
}
