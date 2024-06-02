package solving_techniques.p5_CyclicSort;

/**
 * https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/6393aee5ba7c985340679287
 * OR
 * 287. Find the Duplicate Number
 * https://leetcode.com/problems/find-the-duplicate-number/description/
 * <p>
 * <p>
 * Problem Statement
 * We are given an unsorted array containing n+1 numbers taken from the range 1 to n.
 * The array has only one duplicate but it can be repeated multiple times.
 * Find that duplicate number without using any extra space.
 * You are, however, allowed to modify the input array.
 * <p>
 * Example 1:
 * Input: [1, 4, 4, 3, 2]
 * Output: 4
 * <p>
 * Example 2:
 * Input: [2, 1, 3, 3, 5, 4]
 * Output: 3
 * <p>
 * Example 3:
 * Input: [2, 4, 1, 4, 4]
 * Output: 4
 */
public class FindDuplicateNumber {
    /**
     * time to solve ~ 3 mins
     * time ~ O(N)
     * space ~ O(1)
     * 1 attempt
     *
     * BEATS = 64%
     */
    public int findDuplicate(int[] nums) {
        int start = 0;
        while (start < nums.length) {
            int correctIdx = nums[start] - 1;
            //check if the place of value is correct
            if (start == correctIdx) {
                start++;
            } else {
                int temp = nums[start];
                if (temp == nums[correctIdx]) {
                    return temp;
                } else {
                    nums[start] = nums[correctIdx];
                    nums[correctIdx] = temp;
                }
            }
        }

        return -100500; //error, this row should not be reached if there is at least one duplicate
    }
}