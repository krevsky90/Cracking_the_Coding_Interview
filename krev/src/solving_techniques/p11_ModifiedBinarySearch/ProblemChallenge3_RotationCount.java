package solving_techniques.p11_ModifiedBinarySearch;

/**
 * https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/63a096ffba30a80b3d62a94c
 * OR
 * https://www.geeksforgeeks.org/find-rotation-count-rotated-sorted-array/
 *
 * Given an array arr[] of size N having distinct numbers sorted in increasing order and the array has been right rotated
 * (i.e, the last element will be cyclically shifted to the starting position of the array) k number of times,
 * the task is to find the value of k.
 *
 * Example 1:
 * Input: arr[] = {15, 18, 2, 3, 6, 12}
 * Output: 2
 * Explanation: Initial array must be {2, 3, 6, 12, 15, 18}.
 * We get the given array after rotating the initial array twice.
 *
 * Example 2:
 * Input: arr[] = {7, 9, 11, 12, 5}
 * Output: 4
 *
 * Example 3:
 * Input: arr[] = {7, 9, 11, 12, 15};
 * Output: 0
 */
public class ProblemChallenge3_RotationCount {
    public static void main(String[] args) {
        int[] arr1 = {15, 18, 2, 3, 6, 12};
        int[] arr2 = {7, 9, 11, 12, 5};
        int[] arr3 = {7, 9, 11, 12, 15};

        ProblemChallenge3_RotationCount obj = new ProblemChallenge3_RotationCount();
        System.out.println(obj.findCountOfRotations(arr1));     //expected 2
        System.out.println(obj.findCountOfRotations(arr2));     //expected 4
        System.out.println(obj.findCountOfRotations(arr3));     //expected 0
    }

    /**
     * KREVSKY SOLUTION:
     * idea is the same as in FindMinimumInRotatedSortedArray, but we return index of minimum element, but not this element itself
     *
     * time to solve ~ 11 mins
     *
     * time ~ O(logN), where N = arr.length
     * space ~ O(1)
     *
     * 1 attempt
     */
    public int findCountOfRotations(int[] arr) {
        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {
            if (arr[low] <= arr[high]) {
                //no rotation
                //it also stops the program in case when low = high
                break;
            }

            int mid = (low + high)/2;
            if (arr[low] <= arr[mid]) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }

        return low;
    }
}
