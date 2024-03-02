package solving_techniques.p17_FibonacciNumbers;

/**
 * 55. Jump Game
 * https://leetcode.com/problems/jump-game/
 *
 * You are given an integer array nums.
 * You are initially positioned at the array's first index, and each element in the array represents your maximum jump length at that position.
 * Return true if you can reach the last index, or false otherwise.
 *
 * Example 1:
 * Input: nums = [2,3,1,1,4]
 * Output: true
 * Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
 *
 * Example 2:
 * Input: nums = [3,2,1,0,4]
 * Output: false
 * Explanation: You will always arrive at index 3 no matter what. Its maximum jump length is 0, which makes it impossible to reach the last index.
 *
 * Constraints:
 * 1 <= nums.length <= 10^4
 * 0 <= nums[i] <= 10^5
 */
public class JumpGame {
    public static void main(String[] args) {
        int[] nums1 = {3,2,1,0,4};
        System.out.println(canJump1(nums1));

    }
    /**
     * KREVSKY SOLUTION:
     * idea: the same as src/data_structures/chapter1_arrays_n_strings/amazon_igotanoffer/medium_arrays/Problem2_10_JumpGame # canJump1
     * time to solve ~ 10 mins
     * time ~ O(n)
     * space ~ O(1)
     *
     * 2 attempts:
     * - forgot "if (nums.length <= 1) return true"
     */
    public static boolean canJump1(int[] nums) {
        if (nums.length <= 1) return true;

        int maxLength = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0 && maxLength <= i) {
                return false;
            }
            maxLength = Math.max(maxLength, i + nums[i]);
            if (maxLength >= nums.length - 1) {
                return true;
            }
        }

        return false;
    }
}
