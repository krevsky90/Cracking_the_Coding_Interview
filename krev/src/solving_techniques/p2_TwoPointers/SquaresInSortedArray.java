package solving_techniques.p2_TwoPointers;

import java.util.Arrays;

/**
 * 977. Squares of a Sorted Array (easy)
 * https://leetcode.com/problems/squares-of-a-sorted-array/
 *
 * #Company: Adobe Amazon Apple eBay Electronic Arts Facebook Google Microsoft Nutanix Twitch Uber VMware Walmart Labs Yandex
 *
 * Given an integer array nums sorted in non-decreasing order, return an array of the squares of each number sorted in non-decreasing order.
 *
 * Example 1:
 * Input: nums = [-4,-1,0,3,10]
 * Output: [0,1,9,16,100]
 *
 * Explanation: After squaring, the array becomes [16,1,0,9,100].
 * After sorting, it becomes [0,1,9,16,100].
 *
 * Example 2:
 * Input: nums = [-7,-3,2,3,11]
 * Output: [4,9,9,49,121]
 *
 * Constraints:
 * 1 <= nums.length <= 10000
 * -10000 <= nums[i] <= 10000
 * nums is sorted in non-decreasing order.
 *
 * Follow up: Squaring each element and sorting the new array is very trivial, could you find an O(n) solution using a different approach?
 *
 */
public class SquaresInSortedArray {
    /**
     * Idea: https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/63ddacd4fcc4ca873d5fbfbc
     *
     * One easier approach could be to first locate the index of the first positive number in the input array.
     * After that, we can utilize the Two Pointers technique to iterate over the array,
     *  with one pointer moving forward to scan positive numbers,
     *  and the other pointer moving backward to scan negative numbers.
     * At each step, we can compare the squares of the numbers pointed by both pointers and append the smaller square to the output array.
     */
    public static void main(String[] args) {
        int[] arr1 = new int[]{-4,-1,0,3,10};
        int[] arr2 = new int[]{-7,-3,2,3,11};
        int[] arr3 = new int[]{-4, -3, -1};

        int[] res1 = sortedSquares(arr1);
        int[] res2 = sortedSquares(arr2);
        int[] res3 = sortedSquares(arr3);

        System.out.println(Arrays.toString(res1));
        System.out.println(Arrays.toString(res2));
        System.out.println(Arrays.toString(res3));
    }

    //15 min to implement
    //10 min to debug and fix (set int idx = nums.length;)
    //-2 -1
    //idx = 2
    //negIdx = -1
    //posIdx = 0

    //-7,-3,2,3,11
    //idx = 2
    //neg = -1
    //pos = 5
    //result = {4, 9, 9, 49, 121}


    public static int[] sortedSquares(int[] nums) {
        if (nums == null || nums.length == 0) return new int[0];

        //find first non-negative number:
        int idx = nums.length;  //points to the end of array + 1 (i.e. out of right bound)
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= 0) {
                idx = i;
                break;
            }
        }

        int negIdx = idx - 1;
        int posIdx = idx;

        int[] result = new int[nums.length];
        int resIdx = 0;
        while (negIdx >= 0 && posIdx < nums.length) {
            if (nums[negIdx]*nums[negIdx] < nums[posIdx]*nums[posIdx]) {
                result[resIdx] = nums[negIdx]*nums[negIdx];
                negIdx--;
            } else {
                result[resIdx] = nums[posIdx]*nums[posIdx];
                posIdx++;
            }
            resIdx++;
        }

        //if all positive numbers are handled, but negative - not handled
        while (negIdx >= 0) {
            result[resIdx] = nums[negIdx]*nums[negIdx];
            negIdx--;
            resIdx++;
        }

        //if all negative numbers are handled, but positive - not handled
        while (posIdx < nums.length) {
            result[resIdx] = nums[posIdx]*nums[posIdx];
            posIdx++;
            resIdx++;
        }

        return result;
    }
}
