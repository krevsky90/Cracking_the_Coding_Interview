package data_structures.chapter10_sorting_n_searching;

/**
 * p.162
 * 10.3 Search in Rotated Array:
 * Given a sorted array of n integers that has been rotated an unknown
 * number of times, write code to find an element in the array. You may assume that the array was
 * originally sorted in increasing order.
 * EXAMPLE
 * Input:find 5 in {15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14}
 * Output: 8 (the index of 5 in the array)
 * Hints: #298, #310
 * <p>
 * ASSUMPTION/VALIDATION:
 */
public class Problem10_3 {
    public static void main(String[] args) {
        int[] arr = new int[]{15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14};
        int k = 5;
        int result = search(arr, k);
        System.out.println(result);
    }

    /**
     * KREVSKY SOLUTION:
     * time ~ O(2*logN) ~ O(logN)
     */
    public static int search(int[] arr, int k) {
        int start = 0;
        int end = arr.length - 1;
        int index = findBoundIndex(arr, start, end);

        if (index != -1) {
            if (arr[0] <= k && k <= arr[index]) {
                end = index;
            } else {
                start = index + 1;
            }
        }

        int result = binarySearch(arr, start, end, k);
        return result;
    }

    // time ~ O(logN), where N = arr.length
    public static int findBoundIndex(int[] arr, int start, int end) {
        if (end - start == 1) {
            return start;
        }

        int middle = (start + end)/2;
        if (arr[start] <= arr[middle] && arr[middle] <= arr[end]) {
            //it means that the whole array is sorted in increasing order (there is no rotation)
            return -1;
        } else if (arr[start] >= arr[middle]) {
            return findBoundIndex(arr, start, middle);
        } else {
            return findBoundIndex(arr, middle, end);
        }
    }

    // time ~ O(logN), where N = arr.length
    public static int binarySearch(int[] arr, int start, int end, int k) {
        if (start > end) {
            return -1;
        }
        int mid = (start + end)/2;
        if (arr[mid] == k) {
            return mid;
        } else if (arr[mid] < k) {
            return binarySearch(arr, mid + 1, end, k);
        } else {
            return binarySearch(arr, start, mid - 1, k);
        }
    }
}
