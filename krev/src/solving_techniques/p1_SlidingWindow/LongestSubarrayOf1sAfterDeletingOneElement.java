package solving_techniques.p1_SlidingWindow;

/**
 * 1493. Longest Subarray of 1's After Deleting One Element (medium)
 * https://leetcode.com/problems/longest-subarray-of-1s-after-deleting-one-element
 * <p>
 * #Company: Yandex
 * <p>
 * Given a binary array nums, you should delete one element from it.
 * <p>
 * Return the size of the longest non-empty subarray containing only 1's in the resulting array.
 * Return 0 if there is no such subarray.
 * <p>
 * Example 1:
 * Input: nums = [1,1,0,1]
 * Output: 3
 * Explanation: After deleting the number in position 2, [1,1,1] contains 3 numbers with value of 1's.
 * <p>
 * Example 2:
 * Input: nums = [0,1,1,1,0,1,1,0,1]
 * Output: 5
 * Explanation: After deleting the number in position 4, [0,1,1,1,1,1,0,1] longest subarray with value of 1's is [1,1,1,1,1].
 * <p>
 * Example 3:
 * Input: nums = [1,1,1]
 * Output: 2
 * Explanation: You must delete one element.
 * <p>
 * Constraints:
 * 1 <= nums.length <= 10^5
 * nums[i] is either 0 or 1.
 */
public class LongestSubarrayOf1sAfterDeletingOneElement {
    /**
     * KREVSKY SOLUTION:
     * idea: sliding window
     * <p>
     * time to solve ~ 11 mins
     * <p>
     * 1 attempt
     * <p>
     * BEATS ~ 66%
     */
    public int longestSubarray(int[] nums) {
        int counter = 0;
        int start = 0;
        int maxLength = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                counter++;
            }

            while (counter > 1) {
                if (nums[start] == 0) {
                    counter--;
                }
                start++;
            }

            maxLength = Math.max(maxLength, i - start + 1);
        }

        return maxLength - 1;
    }

    /**
     * SOLUTION #2:
     * info: https://leetcode.com/problems/longest-subarray-of-1s-after-deleting-one-element/solutions/3721403/simple-java-solution-beats-99-9/
     * idea:
     * Calculate the number of 1's before and after 0 and find the maximum length of subarray with 1's.
     * If zero is not found return length-1 (because we must delete one element)
     *
     * prev_count - amount of 1s before we find 0
     * count - amount of 1s after we find 0 first time and second (or reach the end of array)
     * <p>
     * BEATS ~ 99%
     */
    public int longestSubarray2(int[] nums) {
        boolean zero_found = false;
        int prev_count = 0;
        int count = 0;
        int res = 0;
        int total = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                zero_found = true;
                total = prev_count + count;
                res = Math.max(total, res);
                prev_count = count;
                count = 0;
            } else {
                count += 1;
            }
        }
        total = prev_count + count;
        res = Math.max(total, res);
        if (zero_found)
            return res;
        else
            return n - 1;
    }
}
