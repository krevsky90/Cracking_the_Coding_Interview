package data_structures.chapter1_arrays_n_strings.amazon_igotanoffer.easy_arrays;

import java.util.HashSet;
import java.util.Set;

/**
 * https://igotanoffer.com/blogs/tech/array-interview-questions
 * https://leetcode.com/problems/single-number/description/
 *
 * Given a non-empty array of integers nums, every element appears twice except for one. Find that single one.
 * You must implement a solution with a linear runtime complexity and use only constant extra space.
 *
 * Example 1:
 * Input: nums = [2,2,1]
 * Output: 1
 *
 * Example 2:
 * Input: nums = [4,1,2,1,2]
 * Output: 4
 *
 * Example 3:
 * Input: nums = [1]
 * Output: 1
 *
 * Constraints:
 * 1 <= nums.length <= 3 * 10^4
 * -3 * 10^4 <= nums[i] <= 3 * 10^4
 * Each element in the array appears twice except for one element which appears only once.
 */
public class Problem1_12_SingleNumber {
    /**
     * KREVSKY SOLUTION
     * NOTE! it is NOT space O(1)! it is O(n)
     */
    public int singleNumberKREV(int[] nums) {
        Set<Integer> s = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (s.contains(nums[i])) {
                s.remove(nums[i]);
            } else {
                s.add(nums[i]);
            }
        }

        return s.iterator().next().intValue();
    }

    /**
     * OFFICIAL SOLUTION (see 4th):
     * https://dev.to/akhilpokle/single-number-166f
     * idea - BIT MANIPULATION
     * since XOR:
     * 1) a XOR a = 0
     * 2) a XOR 0 = a
     * then the answer is to XOR all numbers
     */
    public int singleNumberBitManipulation(int[] nums) {
        int result = nums[0];

        for (int i = 1; i < nums.length; i++) {
            result ^= nums[i];
        }

        return result;
    }
}
