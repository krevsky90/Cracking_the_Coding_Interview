package solving_techniques.p11_ModifiedBinarySearch;

/**
 * https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/639f1fd54e4f288d4a83ab44
 * https://leetcode.com/problems/search-insert-position (easy)
 * <p>
 * Given an array of numbers sorted in an ascending order, find the ceiling of a given number ?key?.
 * The ceiling of the ?key? will be the smallest element in the given array greater than or equal to the ?key?.
 * <p>
 * Write a function to return the index of the ceiling of the ?key?. If there isn?t any ceiling return -1.
 * <p>
 * Example 1:
 * Input: [4, 6, 10], key = 6
 * Output: 1
 * Explanation: The smallest number greater than or equal to '6' is '6' having index '1'.
 * <p>
 * Example 2:
 * Input: [1, 3, 8, 10, 15], key = 12
 * Output: 4
 */
public class CeilingOfNumber {
    public static void main(String[] args) {
        int[] arr1 = {4, 6, 10};
        int key1 = 6;
        System.out.println(searchInsert(arr1, key1));

        int[] arr2 = {1, 3, 8, 10, 15};
        int key2 = 12;
        System.out.println(searchInsert(arr2, key2));

        int[] arr3 = {1, 3};
        int key3 = 12;
        System.out.println(searchInsert(arr3, key3));
    }

    public static int searchInsert(int[] arr, int key) {
        int l = 0;
        int r = arr.length - 1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (arr[mid] == key) {
                return mid;
            } else if (arr[mid] > key) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }

        if (l >= arr.length) return arr.length;
        if (r < 0) return 0;

        return l;
    }
}
