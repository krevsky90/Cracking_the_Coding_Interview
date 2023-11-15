package solving_techniques.p5_CyclicSort;

/**
 * https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/6393aee5ba7c985340679287
 * OR
 * 287. Find the Duplicate Number
 * https://leetcode.com/problems/find-the-duplicate-number/description/
 *
 *
 * Problem Statement
 * We are given an unsorted array containing n+1 numbers taken from the range 1 to n.
 * The array has only one duplicate but it can be repeated multiple times.
 * Find that duplicate number without using any extra space.
 * You are, however, allowed to modify the input array.
 *
 * Example 1:
 * Input: [1, 4, 4, 3, 2]
 * Output: 4
 *
 * Example 2:
 * Input: [2, 1, 3, 3, 5, 4]
 * Output: 3
 *
 * Example 3:
 * Input: [2, 4, 1, 4, 4]
 * Output: 4
 */
public class FindDuplicateNumber {
    //copy my solution from leetcode

    /**
     * time to solve ~ 3 mins
     * time ~ O(N)
     * space ~ O(1)
     * 1 attempt
     */
    public int findDuplicate(int[] nums) {
        int start = 0;

        while (start < nums.length) {
            if (start + 1 == nums[start]) {
                start++;
            } else {
                int temp = nums[nums[start] - 1];
                if (temp == nums[start]) {
                    //found duplicate
                    return temp;
                } else {
                    //swap
                    nums[nums[start] - 1] = nums[start];
                    nums[start] = temp;
                }
            }
        }

        return -100500; //error, this row should not be reached if there is at least one duplicate
    }
}
