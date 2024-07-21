package data_structures.chapter1_arrays_n_strings.amazon_igotanoffer.medium_arrays;

/**
 * https://igotanoffer.com/blogs/tech/array-interview-questions
 * https://leetcode.com/problems/move-zeroes (easy)
 *
 * #Company: Yandex
 *
 * <p>
 * Given an integer array nums, move all 0's to the end of it while maintaining the relative order of the non-zero elements.
 * <p>
 * Note that you must do this in-place without making a copy of the array.
 * <p>
 * Example 1:
 * Input: nums = [0,1,0,3,12]
 * Output: [1,3,12,0,0]
 * <p>
 * Example 2:
 * Input: nums = [0]
 * Output: [0]
 * <p>
 * Constraints:
 * 1 <= nums.length <= 10^4
 * -2^31 <= nums[i] <= 2^31 - 1
 * <p>
 * Follow up: Could you minimize the total number of operations done?
 */
public class Problem2_1_MoveZerosToEndOfArray {
    //time of solution ~ 20 mins
    //beats 99.9%
    public void moveZeroesKREV(int[] nums) {
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

    //Official
    //beats 87.91%
    public void moveZeroes(int[] nums) {
        int lastNonZeroFoundAt = 0;
        // If the current element is not 0, then we need to
        // append it just in front of last non 0 element we found.
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[lastNonZeroFoundAt] = nums[i];
                lastNonZeroFoundAt++;
            }
        }

        // After we have finished processing new elements,
        // all the non-zero elements are already at beginning of array.
        // We just need to fill remaining array with 0's.
        for (int i = lastNonZeroFoundAt; i < nums.length; i++) {
            nums[i] = 0;
        }
    }

    /**
     * KREVSKY SOLUTION #2: 20/07/2024
     * time to solve ~ 12 mins
     * 1 attempt
     */
    public void moveZeroesKrev2(int[] nums) {
        int vacantId = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                if (vacantId == -1) {
                    vacantId = i;
                }
            } else {
                if (vacantId >= 0) {
                    //swap
                    nums[vacantId] = nums[i];
                    nums[i] = 0;
                    vacantId++;
                }
            }
        }
    }
}
