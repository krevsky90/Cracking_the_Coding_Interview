package solving_techniques.p12_Bitwise_XOR;

/**
 * 2401. Longest Nice Subarray (medium)
 * https://leetcode.com/problems/longest-nice-subarray/
 * <p>
 * #Company (15.04.2025): 0 - 3 months Google 5 Microsoft 5 Meta 3 0 - 6 months Amazon 2 Bloomberg 2 6 months ago Paytm 2
 * <p>
 * You are given an array nums consisting of positive integers.
 * We call a subarray of nums nice if the bitwise AND of every pair of elements that are in different positions in the subarray is equal to 0.
 * Return the length of the longest nice subarray.
 * A subarray is a contiguous part of an array.
 * Note that subarrays of length 1 are always considered nice.
 * <p>
 * Example 1:
 * Input: nums = [1,3,8,48,10]
 * Output: 3
 * Explanation: The longest nice subarray is [3,8,48]. This subarray satisfies the conditions:
 * - 3 AND 8 = 0.
 * - 3 AND 48 = 0.
 * - 8 AND 48 = 0.
 * It can be proven that no longer nice subarray can be obtained, so we return 3.
 * <p>
 * Example 2:
 * Input: nums = [3,1,5,11,13]
 * Output: 1
 * Explanation: The length of the longest nice subarray is 1. Any subarray of length 1 can be chosen.
 * <p>
 * Constraints:
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^9
 */
public class LongestNiceSubarray {
    /**
     * KREVSKY SOLUTION:
     * idea:
     * loop in loop
     * BUT internal loop will be no longer than 30 since <= 10^9 number means <= 30 bits
     * <p>
     * time to solve ~ 20 mins
     * time ~ O(30*n) ~ O(n)
     * space ~ O(1)
     * <p>
     * 2 attempts:
     * - forgot brackets for temp & nums[j]
     * <p>
     * BEATS ~ 98%
     */
    public int longestNiceSubarray(int[] nums) {
        int n = nums.length;
        int res = 1;
        for (int i = 0; i < n; i++) {
            int temp = nums[i];
            int j = i + 1;
            //it will be no longer than 30 since <= 10^9 number means <= 30 bits
            while (j < n) {
                if ((temp & nums[j]) == 0) {
                    temp = temp | nums[j];
                    j++;
                } else {
                    break;
                }
            }

            res = Math.max(res, j - i);
        }

        return res;
    }
}
