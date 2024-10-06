package solving_techniques.different;

import java.util.HashMap;
import java.util.Map;

/**
 * 523. Continuous Subarray Sum (medium)
 * https://leetcode.com/problems/continuous-subarray-sum
 * <p>
 * #Company: Amazon Facebook Google Microsoft Samsung Yandex
 * <p>
 * Given an integer array nums and an integer k, return true if nums has a good subarray or false otherwise.
 * <p>
 * A good subarray is a subarray where:
 * <p>
 * its length is at least two, and
 * the sum of the elements of the subarray is a multiple of k.
 * Note that:
 * <p>
 * A subarray is a contiguous part of the array.
 * An integer x is a multiple of k if there exists an integer n such that x = n * k. 0 is always a multiple of k.
 * <p>
 * Example 1:
 * Input: nums = [23,2,4,6,7], k = 6
 * Output: true
 * Explanation: [2, 4] is a continuous subarray of size 2 whose elements sum up to 6.
 * <p>
 * Example 2:
 * Input: nums = [23,2,6,4,7], k = 6
 * Output: true
 * Explanation: [23, 2, 6, 4, 7] is an continuous subarray of size 5 whose elements sum up to 42.
 * 42 is a multiple of 6 because 42 = 7 * 6 and 7 is an integer.
 * <p>
 * Example 3:
 * Input: nums = [23,2,6,4,7], k = 13
 * Output: false
 * <p>
 * Constraints:
 * 1 <= nums.length <= 10^5
 * 0 <= nums[i] <= 10^9
 * 0 <= sum(nums[i]) <= 2^31 - 1
 * 1 <= k <= 2^31 - 1
 */
public class ContinuousSubarraySum {
    /**
     * NOT SOLVED by myself
     * info: https://www.youtube.com/watch?v=OKcrLfR-8mE
     * time to spend ~ 45 mins
     * idea:
     * 1) keep prefix sum
     * 2) keep map: remainder (preSum % k) -> index
     * 3.1) if remainder = 0 and index > 0 => we've found the solution => true
     * 3.2) if remainder already exists in map => the sum of elements between these indexes is multiple k => true
     * <p>
     * time to implement ~ 15 mins
     * <p>
     * 1 attempt
     * <p>
     * BEATS ~ 91%
     */
    public boolean checkSubarraySum(int[] nums, int k) {
        Map<Integer, Integer> remainderToIndexMap = new HashMap<>();
        int prefixSum = 0;
        for (int i = 0; i < nums.length; i++) {
            prefixSum += nums[i];
            int key = prefixSum % k;
            if (key == 0 && i != 0) {
                return true;
            } else if (remainderToIndexMap.containsKey(key)) {
                int prevIdx = remainderToIndexMap.get(key);
                if (i - prevIdx > 1) {
                    return true;
                }
            } else {
                remainderToIndexMap.put(key, i);
            }
        }

        return false;
    }
}
