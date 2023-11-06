package solving_techniques.twoPointers;

/**
 * https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/638f9b3e239cfbfb10ee82f3
 * OR
 * 75. Sort Colors
 * https://leetcode.com/problems/sort-colors/description/
 *
 * Given an array nums with n objects colored red, white, or blue, sort them in-place so that objects of the same color are adjacent,
 * with the colors in the order red, white, and blue.
 *
 * We will use the integers 0, 1, and 2 to represent the color red, white, and blue, respectively.
 * You must solve this problem without using the library's sort function.
 * Example 1:
 * Input: nums = [2,0,2,1,1,0]
 * Output: [0,0,1,1,2,2]
 *
 * Example 2:
 * Input: nums = [2,0,1]
 * Output: [0,1,2]
 *
 * Constraints:
 * n == nums.length
 * 1 <= n <= 300
 * nums[i] is either 0, 1, or 2.
 *
 * Follow up: Could you come up with a one-pass algorithm using only constant extra space
 *
 * NOTE: the idea is similar to Segregate0and1s problem
 */
public class DutchNationalFlagProblem {
    public static void main(String[] args) {
        int[] arr = new int[]{2,0,1};
        sortColors(arr);
    }
    /**
     * SOLUTION: https://www.geeksforgeeks.org/segregate-0s-and-1s-in-an-array-by-traversing-array-once/
     * Idea: 3 pointers (low, mid, high)
     * while mid <= high
     * 1) if nums[mid] == 0 then
     *  a) swap nums[low] and nums[mid]
     *  b) low++ and mid++
     * 2) if nums[mid] == 1 then mid++
     * 3) if nums[mid] == 2 then
     *  a) swap nums[mid] and nums[high]
     *  b) high--
     *
     *  time ~ O(n)
     *  space ~ O(1)
     */
    public static void sortColors(int[] nums) {
        int low = 0;
        int mid = 0;
        int high = nums.length - 1;

        while (mid <= high) {
            if (nums[mid] == 0) {
                //swap mid and low elements
                int temp = nums[low];
                nums[low] = nums[mid];
                nums[mid] = temp;

                mid++;
                low++;
            } else if (nums[mid] == 1) {
                mid++;
            } else {// if (nums[mid] == 2) {
                //swap mid and high elements
                int temp = nums[high];
                nums[high] = nums[mid];
                nums[mid] = temp;
                high--;
            }
        }
        System.out.println("");
    }
}
