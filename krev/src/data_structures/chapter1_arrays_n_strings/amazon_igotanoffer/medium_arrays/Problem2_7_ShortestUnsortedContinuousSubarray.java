package data_structures.chapter1_arrays_n_strings.amazon_igotanoffer.medium_arrays;

/**
 * https://igotanoffer.com/blogs/tech/array-interview-questions
 * https://leetcode.com/problems/shortest-unsorted-continuous-subarray/description/
 * <p>
 * Given an integer array nums, you need to find one continuous subarray that if you only sort this subarray in ascending order, then the whole array will be sorted in ascending order.
 * Return the shortest such subarray and output its length.
 * <p>
 * Example 1:
 * Input: nums = [2,6,4,8,10,9,15]
 * Output: 5
 * Explanation: You need to sort [6, 4, 8, 10, 9] in ascending order to make the whole array sorted in ascending order.
 * <p>
 * Example 2:
 * Input: nums = [1,2,3,4]
 * Output: 0
 * <p>
 * Example 3:
 * Input: nums = [1]
 * Output: 0
 * <p>
 * Constraints:
 * 1 <= nums.length <= 10^4
 * -10^5 <= nums[i] <= 10^5
 * <p>
 * Follow up: Can you solve it in O(n) time complexity?
 */
public class Problem2_7_ShortestUnsortedContinuousSubarray {
    /**
     * KREVSKY SOLUTION
     * time to solve ~ 1h
     * time complexity ~ O(n)
     * INCORRECT! but the idea +- similar to the official
     * works incorrectly at least in case of duplicate numbers
     */
    public int findUnsortedSubarrayKREVIncorrect(int[] nums) {
        // 2,4,6,8,10,9,15 -> 2,4,6,8,10,9 or 10,9  => answer is 10,9 => 2

        // 2,4,6,8,10,9,7,16,15,17,18 -> 10,9,7,16,15 => 5

        // 2,4,6,8,20,9,11 => 20,9,11 => 3 = 6 - 4 + 1
        // left = 4
        // right = 0

        //2,4,6,20,9 => 20,9 => 2
        // left = 3
        // right = 4

        //10 5 11 12 13
        //left = 0
        //right = 1

        //1,3,2,3,3
        //left = 1
        //right = 2

        //1,8,2,3,8,9
        //left = 1
        //right = 3


        int left = 0;
        int right = nums.length - 1;
        while (left + 1 < nums.length && nums[left] <= nums[left + 1]) {
            left++;
        }

        while (right > left && nums[right] >= nums[left] && nums[right - 1] <= nums[right]) {
            right--;
        }

        if (left == right) return 0;

        return right - left + 1;
    }

    /**
     * Official Solution
     * https://leetcode.com/problems/shortest-unsorted-continuous-subarray/solutions/127627/shortest-unsorted-continous-subarray/
     */
    public int findUnsortedSubarray(int[] nums) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        boolean flag = false;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i - 1])
                flag = true;
            if (flag)
                min = Math.min(min, nums[i]);
        }
        flag = false;
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] > nums[i + 1])
                flag = true;
            if (flag)
                max = Math.max(max, nums[i]);
        }
        int l, r;
        for (l = 0; l < nums.length; l++) {
            if (min < nums[l])
                break;
        }
        for (r = nums.length - 1; r >= 0; r--) {
            if (max > nums[r])
                break;
        }
        return r - l < 0 ? 0 : r - l + 1;
    }
}
