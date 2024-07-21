package solving_techniques.prefixSum;

import java.util.HashMap;
import java.util.Map;

/**
 * 560. Subarray Sum Equals K (medium)
 * https://leetcode.com/problems/subarray-sum-equals-k/
 * <p>
 * #Company: Yandex, Meta
 * <p>
 * Given an array of integers nums and an integer k,
 * return the total number of subarrays whose sum equals to k.
 * <p>
 * A subarray is a contiguous non-empty sequence of elements within an array.
 * <p>
 * Example 1:
 * Input: nums = [1,1,1], k = 2
 * Output: 2
 * <p>
 * Example 2:
 * Input: nums = [1,2,3], k = 3
 * Output: 2
 * <p>
 * Constraints:
 * 1 <= nums.length <= 2 * 10^4
 * -1000 <= nums[i] <= 1000
 * -10^7 <= k <= 10^7
 */
public class SubarraySumEqualsK {
    /**
     * NOT SOLVED by myself!
     * Sliding window DOES NOT work! since elements can be negative!
     */

    /**
     * SOLUTION:
     * idea: prefixSum:
     * info: https://www.youtube.com/watch?v=fFVZt-6sgyo
     * <p>
     * total spent time ~ 1+ hour
     * time to implement ~ 6 mins
     * time ~ O(n)
     * space ~ O(n)
     *
     * 1 attempt
     *
     * BEATS ~ 38%
     */
    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> prefixSumToCounterMap = new HashMap<>();
        prefixSumToCounterMap.put(0, 1);    //for conveniency: case when [1,-1] and k = 0 and case when prefixSum = k

        int prefixSum = 0;
        int result = 0;

        for (int i = 0; i < nums.length; i++) {
            prefixSum += nums[i];
            //1. find if the map contains 'prefixSum - k'
            int delta = prefixSum - k;
            //2. if yes - add counter for thie 'delta' to the final result
            result += prefixSumToCounterMap.getOrDefault(delta, 0);
            //3. increase counter for 'prefixSum' in the map
            prefixSumToCounterMap.put(prefixSum, prefixSumToCounterMap.getOrDefault(prefixSum, 0) + 1);
        }

        return result;
    }
}
