package solving_techniques.p1_SlidingWindow;

/**
 * 1004. Max Consecutive Ones III (medium)
 * https://leetcode.com/problems/max-consecutive-ones-iii
 * <p>
 * #Company: Amazon Facebook Microsoft Yandex
 * <p>
 * Given a binary array nums and an integer k,
 * return the maximum number of consecutive 1's in the array if you can flip at most k 0's.
 * <p>
 * Example 1:
 * Input: nums = [1,1,1,0,0,0,1,1,1,1,0], k = 2
 * Output: 6
 * Explanation: [1,1,1,0,0,1,1,1,1,1,1]
 * Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.
 * <p>
 * Example 2:
 * Input: nums = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], k = 3
 * Output: 10
 * Explanation: [0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
 * Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.
 * <p>
 * Constraints:
 * 1 <= nums.length <= 10^5
 * nums[i] is either 0 or 1.
 * 0 <= k <= nums.length
 */
public class MaxConsecutiveOnes3 {
    /**
     * KREVSKY SOLUTION:
     * time to solve ~ 10 mins
     * time ~ O(n)
     * space ~ O(1)
     * <p>
     * 1 attempt
     * <p>
     * BEATS ~ 87%
     */
    public int longestOnes(int[] nums, int k) {
        int start = 0;
        int result = -1;
        int counter = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                counter++;
            }

            while (counter > k) {
                if (nums[start] == 0) {
                    counter--;
                }
                start++;
            }

            result = Math.max(result, i - start + 1);
        }

        return result;
    }
}
