package solving_techniques.p12_Bitwise_XOR;

/**
 * https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/63a0a26aef4e913a2e2bf245
 * OR
 * 136. Single Number
 * https://leetcode.com/problems/single-number/
 * <p>
 * In a non-empty array of integers, every number appears twice except for one, find that single number.
 * <p>
 * Example 1:
 * Input: 1, 4, 2, 1, 3, 2, 3
 * Output: 4
 * <p>
 * Example 2:
 * Input: 7, 9, 7
 * Output: 9
 */
public class SingleNumber {
    /**
     * KREVSKY SOLUTION:
     * idea: use property that x^x = 0
     */
    public int singleNumber(int[] nums) {
        int result = nums[0];

        for (int i = 1; i < nums.length; i++) {
            result ^= nums[i];
        }

        return result;
    }
}
