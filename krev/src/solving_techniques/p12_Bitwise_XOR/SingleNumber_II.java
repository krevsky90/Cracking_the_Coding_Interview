package solving_techniques.p12_Bitwise_XOR;

/**
 * 137. Single Number II
 * https://leetcode.com/problems/single-number-ii/
 *
 * Given an integer array nums where every element appears three times except for one, which appears exactly once.
 * Find the single element and return it.
 * You must implement a solution with a linear runtime complexity and use only constant extra space.
 *
 * Example 1:
 * Input: nums = [2,2,3,2]
 * Output: 3
 *
 * Example 2:
 * Input: nums = [0,1,0,1,0,1,99]
 * Output: 99
 *
 * Constraints:
 * 1 <= nums.length <= 3 * 10^4
 * -2^31 <= nums[i] <= 2^31 - 1
 *
 * Each element in nums appears exactly three times except for one element which appears once.
 */
public class SingleNumber_II {
    /**
     * main idea is from https://leetcode.com/problems/single-number-ii/solutions/3714928/bit-manipulation-c-java-python-beginner-friendly/
     */
    public static void main(String[] args) {
        int[] nums1 = {2,2,3,2};
        int res1 = singleNumber(nums1);
        System.out.println(res1);

    }

    public static int singleNumber(int[] nums) {
        int result = 0;

        //idea #1: count bits that equals 1 for each position in nums
        //idea #2: since we have Integer => max amount of position = 31
        for (int i = 0; i < 32; i++) {
            int sum = 0;
            for (int num : nums) {
                //check if i-th bit of num equals 1
                if (((num >> i) & 1) == 1) {
                    sum++;
                }
            }

            //idea #3:
            //IF sum % 3 == 0 THEN result does not have 1 in i-th bit, i.e. i-th bit of result equals 0
            //IF sum % 3 == 1 THEN i-th bit of result equals 1
            if (sum % 3 == 1) {
                result += 1 << i;
            }
        }

        return result;
    }
}