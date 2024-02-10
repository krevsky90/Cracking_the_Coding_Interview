package solving_techniques.p11_ModifiedBinarySearch;

/**
 * https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/639f967aba1e473bb5b8d519
 * Given an array of numbers sorted in ascending order,
 * find the element in the array that has the minimum difference with the given ?key?.
 *
 * Example 1:
 *
 * Input: [4, 6, 10], key = 7
 * Output: 6
 * Explanation: The difference between the key '7' and '6' is minimum than any other number in the array
 * Example 2:
 *
 * Input: [4, 6, 10], key = 4
 * Output: 4
 * Example 3:
 *
 * Input: [1, 3, 8, 10, 15], key = 12
 * Output: 10
 * Example 4:
 *
 * Input: [4, 6, 10], key = 17
 * Output: 10
 */
public class MinimumDifferenceElement {
    public static void main(String[] args) {
        int[] arr1 = {2, 5, 10, 12, 15};
        int key1 = 6;
        System.out.println(findMinDiffElement(arr1, key1)); //expected 5

        int[] arr2 = {2, 5, 10, 12, 15};
        int key2 = 5;
        System.out.println(findMinDiffElement(arr2, key2)); //expected 5

        int[] arr3 = {2, 5, 10, 12, 15};
        int key3 = 20;
        System.out.println(findMinDiffElement(arr3, key3)); //expected 15

        int[] arr4 = {4,6};
        int key4 = 2;
        System.out.println(findMinDiffElement(arr4, key4)); //expected 4
    }

    /**
     * KREVSKY SOLUTION:
     * idea: is the same as for CeilingOfNumber
     * BUT handle 2 corner cases:
     * 1) left is out of upper bound
     * 2) right is out of lower bound
     *
     * time to solve ~ 8 mins
     * time ~ O(logN)
     * space ~ O(1)
     *
     * 2 attempts:
     * - forgot to handle corner cases
     */
    public static int findMinDiffElement(int[] arr, int key) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = (left + right)/2;
            if (arr[mid] == key) {
                return arr[mid];
            } else if (arr[mid] < key) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        if (left > arr.length - 1) {
            return arr[arr.length - 1];
        }

        if (right < 0) {
            return arr[0];
        }

        return arr[left] - key <= key - arr[right] ? arr[left] : arr[right];
    }
}
