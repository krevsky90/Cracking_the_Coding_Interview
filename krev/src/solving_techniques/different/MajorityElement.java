package solving_techniques.different;

/**
 * 169. Majority Element (easy)
 * https://leetcode.com/problems/majority-element/
 * <p>
 * #Company: Apple
 * <p>
 * Given an array nums of size n, return the majority element.
 * The majority element is the element that appears more than ⌊n / 2⌋ times.
 * You may assume that the majority element always exists in the array.
 * <p>
 * Example 1:
 * Input: nums = [3,2,3]
 * Output: 3
 * <p>
 * Example 2:
 * Input: nums = [2,2,1,1,1,2,2]
 * Output: 2
 * <p>
 * Constraints:
 * n == nums.length
 * 1 <= n <= 5 * 10^4
 * -10^9 <= nums[i] <= 10^9
 * <p>
 * <p>
 * Follow-up: Could you solve the problem in linear time and in O(1) space? IMPORTANT CONDITION!!!
 */
public class MajorityElement {
    /**
     * NOT SOLVED in space ~ O(1) by myself
     * info: https://www.youtube.com/watch?v=91aSh12EU4Q&list=PLUPSMCjQ-7oeyhZZ7xjXPQmWEn1EQUiME&index=16
     * idea:
     * keep track candidate and its counter
     * increase or decrease counter.
     * If counter <= 0 => change => change candidate to nums[i]
     * <p>
     * spand time ~ 15 mins
     * <p>
     * time ~ O(n)
     * space ~ O(1)
     * <p>
     * 1 attempt
     * <p>
     * BEATS = 99%
     */
    public int majorityElement(int[] nums) {
        int candidate = nums[0];
        int counter = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == candidate) {
                counter++;
            } else {
                counter--;
                if (counter < 0) {//or counter == 0
                    candidate = nums[i];
                    counter = 1;
                }
            }
        }

        return candidate;
    }
}
