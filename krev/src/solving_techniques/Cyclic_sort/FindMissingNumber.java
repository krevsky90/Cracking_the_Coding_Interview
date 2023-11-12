package solving_techniques.Cyclic_sort;

/**
 * https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/6393ab5cd8a93f4bff961bc7
 * OR
 * 268. Missing Number
 * https://leetcode.com/problems/missing-number/
 *
 * We are given an array containing n distinct numbers taken from the range 0 to n.
 * Since the array has only n numbers out of the total n+1 numbers, find the missing number.
 *
 * Example 1:
 * Input: [4, 0, 3, 1]
 * Output: 2
 *
 * Example 2:
 * Input: [8, 3, 5, 2, 4, 6, 0, 1]
 * Output: 7
 */
public class FindMissingNumber {
    public static void main(String[] args) {
        int[] arr1 = {4, 0, 3, 1};
        System.out.println(findMissingNumber(arr1));

        int[] arr2 = {8, 3, 5, 2, 4, 6, 0, 1};
        System.out.println(findMissingNumber(arr2));

        int[] arr3 = {0, 1};
        System.out.println(findMissingNumber(arr3));    //should return 2
    }

    /**
     * time to solve ~ 40 mins with tips
     * time ~ O(N)
     * space ~ O(1)
     * 2 attempts
     */
    public static int findMissingNumber(int[] arr) {
        //1. sort the array by cyclic sorting
        int start = 0;
        while (start < arr.length) {
            if (arr[start] == start) {
                start++;
            } else {
                int j = arr[start];
                if (j == arr.length) {
                    //the idea!!! ignore this number
                    start++;
                } else {
                    int temp = arr[j];
                    arr[j] = arr[start];
                    arr[start] = temp;
                }
            }
        }

        //2. now find the position where i != arr[i] and return i
        for (int i = 0; i < arr.length; i++) {
            if (i != arr[i]) {
                return i;
            }
        }

        //all numbers exist => sorted => missing number in the range = n, since it does not appear in nums.
        return arr.length;
    }
}
