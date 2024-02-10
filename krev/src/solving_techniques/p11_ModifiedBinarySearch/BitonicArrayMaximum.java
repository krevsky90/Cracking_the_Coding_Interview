package solving_techniques.p11_ModifiedBinarySearch;

/**
 * https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/639f977fa24c2a2e65e84c02
 * OR
 * 852. Peak Index in a Mountain Array
 * https://leetcode.com/problems/peak-index-in-a-mountain-array/
 * <p>
 * An array arr is a mountain if the following properties hold:
 * <p>
 * arr.length >= 3
 * There exists some i with 0 < i < arr.length - 1 such that:
 * arr[0] < arr[1] < ... < arr[i - 1] < arr[i]
 * arr[i] > arr[i + 1] > ... > arr[arr.length - 1]
 * Given a mountain array arr, return the index i such that
 * arr[0] < arr[1] < ... < arr[i - 1] < arr[i] > arr[i + 1] > ... > arr[arr.length - 1].
 * <p>
 * You must solve it in O(log(arr.length)) time complexity.
 * <p>
 * Example 1:
 * Input: arr = [0,1,0]
 * Output: 1
 * <p>
 * Example 2:
 * Input: arr = [0,2,1,0]
 * Output: 1
 * <p>
 * Example 3:
 * Input: arr = [0,10,5,2]
 * Output: 1
 * <p>
 * Constraints:
 * 3 <= arr.length <= 10^5
 * 0 <= arr[i] <= 10^6
 * arr is guaranteed to be a mountain array.
 */
public class BitonicArrayMaximum {
    /**
     * KREVSKY SOLUTION:
     * time to solve ~ 10 mins
     * <p>
     * time ~ O(logN)
     * <p>
     * 2 attempts:
     * - compare not left, but mid - 1
     * not right, but mid + 1
     */
    public int peakIndexInMountainArray(int[] arr) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr[mid - 1] < arr[mid] && arr[mid] > arr[mid + 1]) {
                return mid;
            } else if (arr[mid - 1] < arr[mid] && arr[mid] < arr[mid + 1]) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return Integer.MIN_VALUE;   //like error-code
    }
}
