package solving_techniques.prefixSum;

import java.util.HashMap;
import java.util.Map;

/**
 * 974. Subarray Sums Divisible by K (medium)
 * https://leetcode.com/problems/subarray-sums-divisible-by-k/
 *
 * #Company: 0 - 3 months Uber 5 Google 3 0 - 6 months TikTok 2 6 months ago Amazon 16 Citadel 8 Meta 6 Microsoft 6 Bloomberg 6  Apple 5 Adobe 4 Tinkoff 3 PayPal 2 thoughtspot 2
 *
 * Given an integer array nums and an integer k, return the number of non-empty subarrays that have a sum divisible by k.
 *
 * A subarray is a contiguous part of an array.
 *
 * Example 1:
 * Input: nums = [4,5,0,-2,-3,1], k = 5
 * Output: 7
 * Explanation: There are 7 subarrays with a sum divisible by k = 5:
 * [4, 5, 0, -2, -3, 1], [5], [5, 0], [5, 0, -2, -3], [0], [0, -2, -3], [-2, -3]
 *
 * Example 2:
 * Input: nums = [5], k = 9
 * Output: 0
 *
 * Constraints:
 * 1 <= nums.length <= 3 * 10^4
 * -104 <= nums[i] <= 10^4
 * 2 <= k <= 10^4
 */
public class SubarraySumsDivisibleByK {
    /**
     * KREVSKY SOLUTION:
     * idea:
     * 1) prefix sum
     * 2) store Map: remainder -> num of occurrences
     * 3) each time when remainder occurs => increase the result by 'num of occurrences' and then increment 'num of occurrences'
     * <p>
     * time to solve ~ 25 mins
     * time ~ O(N)
     * space ~ O(N)
     * <p>
     * 2 attempts:
     * - did not handle negative remainders
     * <p>
     * BEATS ~ 24%
     */
    // 4,5,0,-2,-3,1 5
    // 4 4 4  2  4 0 0
    // map:
    // 0 - 3
    // 4 - 4
    // 2 - 1
    // result = 1+2+3+1+2
    public int subarraysDivByK(int[] nums, int k) {
        int prefixSum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);  //for cases like [0,0]
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            prefixSum += nums[i];
            int part = prefixSum % k;
            //to handle negative prefix sum
            if (part < 0) part += k;

            if (map.containsKey(part)) {
                result += map.get(part);
            }
            map.put(part, map.getOrDefault(part, 0) + 1);
        }

        return result;
    }
}
