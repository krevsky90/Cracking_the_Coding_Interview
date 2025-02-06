package solving_techniques.prefixSum;

import java.util.HashMap;
import java.util.Map;

/**
 * 525. Contiguous Array (medium)
 * https://leetcode.com/problems/contiguous-array
 * #Company (5.02.2025): 0 - 3 months Amazon 6 Google 2 Microsoft 2 Bloomberg 2 0 - 6 months Meta 5 Oracle 3 6 months ago Yahoo 5 Apple 4 Uber 3 Adobe 2 TikTok 2 StackAdapt 2
 * <p>
 * Given a binary array nums, return the maximum length of a contiguous subarray with an equal number of 0 and 1.
 * <p>
 * Example 1:
 * Input: nums = [0,1]
 * Output: 2
 * Explanation: [0, 1] is the longest contiguous subarray with an equal number of 0 and 1.
 * <p>
 * Example 2:
 * Input: nums = [0,1,0]
 * Output: 2
 * Explanation: [0, 1] (or [1, 0]) is a longest contiguous subarray with equal number of 0 and 1.
 * <p>
 * Constraints:
 * 1 <= nums.length <= 10^5
 * nums[i] is either 0 or 1.
 */
public class ContiguousArray {
    /**
     * NOT SOLVED by myself!
     * info: https://www.youtube.com/watch?v=Xkl4EknqW8Y&list=PLUPSMCjQ-7oeenUxyrJFDFqd40PokIqdO&index=29
     * idea:
     * 1) keep track counter (increase if 1, decrease if 0)
     * 2) keep map: counter -> index when we got this value of counter
     * using this map, we can say, that subarray between i and j positions which have the same counter value, has the same amount of 1 b 0
     * 3) for case when counter = 0 it is worth to prefill the map by (0, -1) element
     *
     * NOTE: the idea is similar to the problem when we find the number of subarrays that has sum divided by K
     *
     * time ~ O(n)
     * space ~ O(n), if all keys in the map are different (example: 1 1 1 1 1)
     *
     * 2 attempts:
     * - forgot to add (0, -1) to map
     *
     * BEATS ~ 85%
     */
    public int findMaxLength(int[] nums) {
        int counter = 0;
        Map<Integer, Integer> counterToLastIndex = new HashMap<>();
        counterToLastIndex.put(0, -1);
        int res = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                counter--;
            } else {
                counter++;
            }

            if (counterToLastIndex.containsKey(counter)) {
                int delta = i - counterToLastIndex.get(counter);
                if (res < delta) res = delta;
            } else {
                counterToLastIndex.put(counter, i);
            }
        }

        return res;
    }
    //i = 5
    //map = (-1, 0), (-2, 1)
    //res = 4
    //-1 -2 -1 -2 -1 0 -1 -2 -1
    // 0  0  1  0  1 1  0  0  1
}
