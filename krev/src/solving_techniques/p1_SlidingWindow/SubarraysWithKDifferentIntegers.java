package solving_techniques.p1_SlidingWindow;

import java.util.HashMap;
import java.util.Map;

/**
 * 992. Subarrays with K Different Integers (hard)
 * https://leetcode.com/problems/subarrays-with-k-different-integers
 * <p>
 * #Company (8.06.2025): 0 - 3 months Google 2 0 - 6 months Amazon 7 Microsoft 3 Bloomberg 3 6 months ago Oracle 5 Adobe 4 TikTok 4 Uber 4 Meta 3 Roblox 3 Yahoo 3 Squarepoint Capital 2
 * <p>
 * Given an integer array nums and an integer k, return the number of good subarrays of nums.
 * <p>
 * A good array is an array where the number of different integers in that array is exactly k.
 * <p>
 * For example, [1,2,3,1,2] has 3 different integers: 1, 2, and 3.
 * A subarray is a contiguous part of an array.
 * <p>
 * Example 1:
 * Input: nums = [1,2,1,2,3], k = 2
 * Output: 7
 * Explanation: Subarrays formed with exactly 2 different integers: [1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2]
 * <p>
 * Example 2:
 * Input: nums = [1,2,1,3,4], k = 3
 * Output: 3
 * Explanation: Subarrays formed with exactly 3 different integers: [1,2,1,3], [2,1,3], [1,3,4].
 * <p>
 * Constraints:
 * 1 <= nums.length <= 2 * 10^4
 * 1 <= nums[i], k <= nums.length
 */
public class SubarraysWithKDifferentIntegers {
    /**
     * NOT SOLVED by myself:
     * idea 1:  required value = atMostK(nums, k) - atMostK(nums, k - 1)
     * idea 2: when we count all subarrays between end and start, that have at most k different integers,
     * we increment counter by (end - start + 1) each iteration of sliding window
     * Because, if we have [1,2,1] subarray and we add nums[end] = 2,
     * the new '2' can form 4 new valid subarrays: {1,2,1,2}, {2,1,2}, {1,2}, {2}
     * <p>
     * time to implement ~ 10 mins
     * <p>
     * time ~ O(n)
     * space ~ O(n)
     * <p>
     * BEATS ~ 52%
     */
    public int subarraysWithKDistinct(int[] nums, int k) {
        //idea #1
        return atMostK(nums, k) - atMostK(nums, k - 1);
    }

    private int atMostK(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap<>();

        int count = 0;
        int start = 0;
        for (int end = 0; end < nums.length; end++) {
            freq.put(nums[end], freq.getOrDefault(nums[end], 0) + 1);

            while (freq.size() > k) {
                freq.put(nums[start], freq.get(nums[start]) - 1);
                if (freq.get(nums[start]) == 0) {
                    freq.remove(nums[start]);
                }

                start++;
            }

            count += end - start + 1;   //idea #2
        }

        return count;
    }
}
