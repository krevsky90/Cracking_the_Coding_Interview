package solving_techniques.prefixSum;

import java.util.HashMap;
import java.util.Map;

/**
 * 325. Maximum Size Subarray Sum Equals k (medium)
 * https://leetcode.com/problems/maximum-size-subarray-sum-equals-k
 * <p>
 * #Company (5.02.2025): 0 - 3 months Meta 5 0 - 6 months Goldman Sachs 2 6 months ago Amazon 3 Microsoft 2 Palantir Technologies 2
 * <p>
 * Given an integer array nums and an integer k, return the maximum length of a
 * subarray that sums to k. If there is not one, return 0 instead.
 * <p>
 * Example 1:
 * Input: nums = [1,-1,5,-2,3], k = 3
 * Output: 4
 * Explanation: The subarray [1, -1, 5, -2] sums to 3 and is the longest.
 * <p>
 * Example 2:
 * Input: nums = [-2,-1,2,1], k = 1
 * Output: 2
 * Explanation: The subarray [-1, 2] sums to 1 and is the longest.
 * <p>
 * Constraints:
 * 1 <= nums.length <= 2 * 10^5
 * -10^4 <= nums[i] <= 10^4
 * -10^9 <= k <= 10^9
 */
public class MaximumSizeSubarraySumEqualsK {
    /**
     * KREVSKY SOLUTION:
     * idea:
     * 1) prefix sum
     * 2) hashmap: sum -> min index when we face this prefix sum
     * 2) map.put(0, -1) to handle case when prefixSum = k
     * <p>
     * time to solve ~ 20 mins
     * time ~ O(n)
     * space ~ (n)
     * <p>
     * a lot of attempts:
     * <p>
     * BEATS ~ 6%
     */
    public int maxSubArrayLen(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();    //sum -> min index when we got this sum
        map.put(0, -1); //to handle case when prefixSum = k
        int sum = 0;
        int result = 0;

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];

            if (map.containsKey(sum - k)) {
                result = Math.max(result, i - map.get(sum - k));
            }

            //since we want to find the longest subarray
            if (!map.containsKey(sum)) {
                map.put(sum, i);
            }
        }

        return result;
    }
}
