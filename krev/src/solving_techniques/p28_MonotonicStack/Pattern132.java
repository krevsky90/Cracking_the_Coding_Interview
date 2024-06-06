package solving_techniques.p28_MonotonicStack;

import java.util.Stack;

/**
 * 456. 132 Pattern
 * https://leetcode.com/problems/132-pattern (medium)
 * <p>
 * Given an array of n integers nums, a 132 pattern is a subsequence of three integers nums[i], nums[j] and nums[k]
 * such that i < j < k and nums[i] < nums[k] < nums[j].
 * <p>
 * Return true if there is a 132 pattern in nums, otherwise, return false.
 * <p>
 * Example 1:
 * Input: nums = [1,2,3,4]
 * Output: false
 * Explanation: There is no 132 pattern in the sequence.
 * <p>
 * Example 2:
 * Input: nums = [3,1,4,2]
 * Output: true
 * Explanation: There is a 132 pattern in the sequence: [1, 4, 2].
 * <p>
 * Example 3:
 * Input: nums = [-1,3,2,0]
 * Output: true
 * Explanation: There are three 132 patterns in the sequence: [-1, 3, 2], [-1, 3, 0] and [-1, 2, 0].
 * <p>
 * Constraints:
 * n == nums.length
 * 1 <= n <= 2 * 10^5
 * -10^9 <= nums[i] <= 10^9
 */
public class Pattern132 {
    /**
     * KREVSKY SOLUTION:
     * idea:
     * 1) use monotonic stack
     * 2) track max value that was excluded from stack (it meant that stack contains higher value)
     * if i-th element < this max value => return true
     *
     * time to solve ~ 20 mins
     *
     * time ~ O(n)
     * space ~ O(n)
     *
     * 1 attempt
     *
     * BEATS = 57%
     */
    public boolean find132pattern(int[] nums) {
        int n = nums.length;
        if (n < 3) return false;

        int maxRight = Integer.MIN_VALUE;

        Stack<Integer> stack = new Stack<>();
        for (int i = n - 1; i >= 0; i--) {
            if (nums[i] < maxRight) {
                return true;
            }

            while (!stack.isEmpty() && nums[i] > stack.peek()) {
                maxRight = Math.max(maxRight, stack.pop());
            }

            stack.add(nums[i]);
        }

        return false;
    }
}
