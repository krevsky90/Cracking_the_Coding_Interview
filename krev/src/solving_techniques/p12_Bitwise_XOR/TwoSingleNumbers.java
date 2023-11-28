package solving_techniques.p12_Bitwise_XOR;

import java.util.Arrays;

/**
 * https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/63a0a41ebc8d0eddc47362f7
 * OR
 * 260. Single Number III
 * https://leetcode.com/problems/single-number-iii/
 *
 * Given an integer array nums, in which exactly two elements appear only once and all the other elements appear exactly twice.
 * Find the two elements that appear only once. You can return the answer in any order.
 * You must write an algorithm that runs in linear runtime complexity and uses only constant extra space.
 *
 * Example 1:
 * Input: nums = [1,2,1,3,2,5]
 * Output: [3,5]
 * Explanation:  [5, 3] is also a valid answer.
 *
 * Example 2:
 * Input: nums = [-1,0]
 * Output: [-1,0]
 *
 * Example 3:
 * Input: nums = [0,1]
 * Output: [1,0]
 *
 * Constraints:
 * 2 <= nums.length <= 3 * 10^4
 * -2^31 <= nums[i] <= 2^31 - 1
 * Each integer in nums will appear twice, only two integers will appear once.
 *
 */
public class TwoSingleNumbers {
    public static void main(String[] args) {
        int[] nums1 = new int[]{1,2,1,3,2,5};
        int[] nums2 = new int[]{-1,0};
        int[] nums3 = new int[]{0,1};

        int[] res1 = singleNumber(nums1);
        int[] res2 = singleNumber(nums2);
        int[] res3 = singleNumber(nums3);
        System.out.println("");
    }
    /**
     * idea: https://www.techiedelight.com/find-two-odd-occurring-element-array-without-extra-space/
     * Let's name unique elements as x and y
     * XOR of all elements = x XOR y. or x^y
     * since x^y != 0 => there is bit equals 1 => this bit is set to 1 in, for example, x, and set to 0 in y
     *
     * Let's find the rightmost bit that equals 1
     * And split the initial array of numbers into 2 groups:
     * a) i-th bit  = 1
     * b) i-th bit  = 0
     * since x and y belong to different groups, XOR or each group will be equal x and y
     */
    public static int[] singleNumber(int[] nums) {
        int xor = 0;    //since 0^A = A
        for (int i = 0; i < nums.length; i++) {
            xor ^= nums[i];
        }

        //find rightmost bit = 1
        int k = 0;
        while ((xor & (1 << k)) == 0) {
            k++;
        }

        //k-th bit = 1.
        int x = 0;
        int y = 0;
        String sX = "";
        String sY = "";

        for (int i = 0; i < nums.length; i++) {
            if (((nums[i] >> k) & 1) == 0) {
                x ^= nums[i];
                sX += nums[i];
            } else {
                y ^= nums[i];
                sY += nums[i];
            }
        }

        System.out.println("sX= " + sX);
        System.out.println("sY= " + sY);

        return new int[]{x, y};
    }



}
