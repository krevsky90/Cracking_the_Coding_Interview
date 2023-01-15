package data_structures.chapter1_arrays_n_strings.amazon_igotanoffer.medium_arrays;

/**
 * https://igotanoffer.com/blogs/tech/array-interview-questions
 * https://leetcode.com/problems/move-zeroes/description/
 *
 * Given an integer array nums, move all 0's to the end of it while maintaining the relative order of the non-zero elements.
 *
 * Note that you must do this in-place without making a copy of the array.
 *
 * Example 1:
 * Input: nums = [0,1,0,3,12]
 * Output: [1,3,12,0,0]
 *
 * Example 2:
 * Input: nums = [0]
 * Output: [0]
 *
 * Constraints:
 * 1 <= nums.length <= 104
 * -231 <= nums[i] <= 231 - 1
 *
 * Follow up: Could you minimize the total number of operations done?
 *
 */
public class Problem1_2_MoveZerosToEndOfArray {
    //time of solution ~ 20 mins
    //beats 99.9%
    public void moveZeroes(int[] nums) {
        int amountOfNonZeroNums = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                int temp = nums[amountOfNonZeroNums];
                nums[amountOfNonZeroNums] = nums[i];
                nums[i] = temp;
                amountOfNonZeroNums++;
            }
        }
    }
}
