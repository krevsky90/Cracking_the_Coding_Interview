package solving_techniques.p1_SlidingWindow;

/**
 * 2444. Count Subarrays With Fixed Bounds (hard)
 * https://leetcode.com/problems/count-subarrays-with-fixed-bounds
 *
 * #Company: (24.12.2024) 0 - 3 months Morgan Stanley 3 Uber 2 MathWorks 2 0 - 6 months Meesho 2 6 months ago Microsoft 4 Amazon 4 Google 3 Apple 3 Meta 2 Bloomberg 2 Adobe 2 Snowflake 2
 *
 * You are given an integer array nums and two integers minK and maxK.
 * A fixed-bound subarray of nums is a subarray that satisfies the following conditions:
 * The minimum value in the subarray is equal to minK.
 * The maximum value in the subarray is equal to maxK.
 * Return the number of fixed-bound subarrays.
 * A subarray is a contiguous part of an array.
 *
 * Example 1:
 * Input: nums = [1,3,5,2,7,5], minK = 1, maxK = 5
 * Output: 2
 * Explanation: The fixed-bound subarrays are [1,3,5] and [1,3,5,2].
 *
 * Example 2:
 * Input: nums = [1,1,1,1], minK = 1, maxK = 1
 * Output: 10
 * Explanation: Every subarray of nums is a fixed-bound subarray. There are 10 possible subarrays.
 *
 * Constraints:
 * 2 <= nums.length <= 10^5
 * 1 <= nums[i], minK, maxK <= 10^6
 */
public class CountSubarraysWithFixedBounds {
    /**
     * NOT SOLVED by myself:
     * info:  https://www.youtube.com/watch?v=V-uRiEjsItc
     * but I refactored it as sliding window, which is not necessary
     * <p>
     * idea:
     * 1) keep index of start position which exclusive from the window
     * keep the rightmost index of element that = minK
     * keep the rightmost index of element that = maxK
     * <p>
     * 2) for each window that includes at least 1 minK and maxK elements and does NOT include elements out of the range [minK, maxK]
     * calculate the amount of valid subarrays as
     * min(minKpos, maxKpos) - start
     * explanation:
     * degree of freedom = min(maxKpos, minKpos) + 1, if we consider window that starts from 0
     * example: [2,2,1,3,5], minK=1, maxK=5 => min(maxKpos, minKpos) = 2 => [1,3,5], [2,1,3,5], [2,2,1,3,5]
     * if we consider [2,2,1,3,5,1] => minKpos is 5 now! => min(maxKpos, minKpos) = 4 => we add 5 subarrays
     * if we add 4 to the end => [2,2,1,3,5,1,4] => it gives us +5 subarrays, because we will append '4' to all subarrays
     * that we obtained on the previous step, when we added '1' to the end
     * <p>
     * time to spend ~ 1.5h
     * <p>
     * time ~ O(n)
     * space ~ O(1)
     * <p>
     * BEATS ~ 87%
     */
    public long countSubarrays(int[] nums, int minK, int maxK) {
        int start = -1;
        int minKpos = -1;
        int maxKpos = -1;
        long result = 0;

        for (int i = start + 1; i < nums.length; i++) {
            if (nums[i] < minK || nums[i] > maxK) {
                //break window
                start = i;
                minKpos = -1;
                maxKpos = -1;
                continue;
            }

            if (nums[i] == minK) {
                minKpos = i;    //i.e. the same as Math.max(minKpos, i);
            }
            //write separate if's since minK might be = maxK
            if (nums[i] == maxK) {
                maxKpos = i;    //i.e. the same as Math.max(maxKpos, i);
            }

            if (minKpos != -1 && maxKpos != -1) {
                //it means sliding window (start, i] is valid subarray and it contains several valid subarrays
                //idea: https://www.youtube.com/watch?v=V-uRiEjsItc
                //degree of freedom = min(maxKpos, minKpos) + 1, if we consider window that starts from 0
                //example: [2,2,1,3,5], minK=1, maxK=5 => min(maxKpos, minKpos) = 2 => [1,3,5], [2,1,3,5], [2,2,1,3,5]
                //if we consider [2,2,1,3,5,1] => minKpos is 5 now! => min(maxKpos, minKpos) = 4 => we add 5 subarrays
                //if we add 4 to the end => [2,2,1,3,5,1,4] => it gives us +5 subarrays, because we will append '4' to all subarrays
                //that we obtained on the previous step, when we added '1' to the end
                result += Math.min(maxKpos, minKpos) - start;
            }
        }

        return result;
    }
}
