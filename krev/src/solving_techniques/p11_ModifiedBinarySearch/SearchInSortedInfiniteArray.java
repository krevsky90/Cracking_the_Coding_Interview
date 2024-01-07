package solving_techniques.p11_ModifiedBinarySearch;

/**
 * https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/639f2d925efa22c27557acf2
 * OR
 * https://leetcode.com/problems/search-in-a-sorted-array-of-unknown-size/  (blocked)
 *
 * Given an infinite sorted array (or an array with unknown size),
 * find if a given number ?key? is present in the array.
 * Write a function to return the index of the ?key? if it is present in the array, otherwise return -1.
 *
 * Since it is not possible to define an array with infinite (unknown) size,
 * you will be provided with an interface ArrayReader to read elements of the array.
 * ArrayReader.get(index) will return the number at index;
 * if the array?s size is smaller than the index, it will return Integer.MAX_VALUE.
 *
 * the solution and examples are here https://takeuforward.org/data-structure/search-in-an-infinite-sorted-array/
 * BUT it is not said that the array is sorted in ascending order, nevertheless the solution considers this as given fact.
 * My solution fits both types or ordering
 */
public class SearchInSortedInfiniteArray {
    public static void main(String[] args) {
        int[] arr1 = {9, 11, 17, 26, 37, 52, 89, 111, 129, 144, 198};
        int n1 = 89;
        System.out.println(findPos(arr1, n1));  // 6

        int[] arr2 = {2, 4, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26};
        int n2 = 20;
        System.out.println(findPos(arr2, n2));  // 8

        //my example for desc ordered array (it is strange that all sources in the internet guess that the array is ASC ordered
        //reversed arr2
        int[] arr3 = {26, 24, 22, 20, 18, 16, 14, 12, 10, 8, 6, 4, 2};
        int n3 = 20;
        System.out.println(findPos(arr3, n3));  // 3
    }

    /**
     * KREVSKY SOLUTION:
     * idea: start from the range: startInx = 0, endInx = 1. Extend the range to the right if current range does not contain 'key' value
     * time to solve ~ 14 mins
     * time to debug ~ 5 mins
     *
     * time ~ O(logN)
     * space ~ O(1)
     * 1 attempt
     */
    public static int findPos(int[] arr, int key) {
        int left = 0;
        int right = 1;

        boolean asc = arr[left] < arr[right];

        if (asc) {
            if (key < arr[0]) {
                return -1;
            }

            while (key > arr[right]) {
                left = right;	//small optimization
                right *= 2;		//move right bound until the 'key' element is bounded by range (left, right)
            }
        } else {
            //desc order
            if (key > arr[0]) {
                return -1;
            }

            while (key < arr[right]) {
                left = right;	//small optimization
                right *= 2;		//move right bound until the 'key' element is bounded by range (left, right)
            }
        }

        return agnosticBinarySearch(arr, key, left, right, asc);
    }

    /**
     * the same as src/solving_techniques/p11_ModifiedBinarySearch/OrderAgnosticBinarySearch.java
     */
    private static int agnosticBinarySearch(int[] arr, int key, int left, int right, boolean acs) {
        while (left <= right) {
            int mid = (left + right)/2;
            if (arr[mid] == key) {
                return mid;
            } else if (acs && arr[mid] < key) {
                left = mid + 1;
            } else if (acs && arr[mid] > key) {
                right = mid - 1;
            } else if (!acs && arr[mid] < key) {
                right = mid - 1;
            } else if (!acs && arr[mid] > key) {
                left = mid + 1;
            }
        }

        return -1;	//not found
    }
}