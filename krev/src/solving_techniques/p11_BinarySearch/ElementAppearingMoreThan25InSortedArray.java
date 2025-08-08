package solving_techniques.p11_BinarySearch;

/**
 * 1287. Element Appearing More Than 25% In Sorted Array (easy)
 * https://leetcode.com/problems/element-appearing-more-than-25-in-sorted-array/
 * <p>
 * #Company (8.08.2025): 0 - 3 months Meta 3 6 months ago Amazon 10 Google 5 Adobe 3 Apple 3 Bloomberg 2 Oracle 2
 * <p>
 * Given an integer array sorted in non-decreasing order, there is exactly one integer in the array
 * that occurs more than 25% of the time, return that integer.
 * <p>
 * Example 1:
 * Input: arr = [1,2,2,6,6,6,6,7,10]
 * Output: 6
 * <p>
 * Example 2:
 * Input: arr = [1,1]
 * Output: 1
 * <p>
 * Constraints:
 * 1 <= arr.length <= 10^4
 * 0 <= arr[i] <= 10^5
 */
public class ElementAppearingMoreThan25InSortedArray {
    /**
     * KREVSKY SOLUTION #1:
     * time ~ O(n)
     * space ~ O(1)
     *
     * time to solve ~ 10 mins
     *
     * 2 attempts:
     * set 'if (cnt*1.0/size > 0.25) return a;' in if, but not in the end of for-loop
     *
     * BEATS ~ 100%
     */
    public int findSpecialIntegerLinear(int[] arr) {
        int size = arr.length;
        int cnt = 0;
        int prev = -1;
        for (int a : arr) {
            if (a == prev) {
                cnt++;
            } else {
                prev = a;
                cnt = 1;
            }

            if (cnt*1.0/size > 0.25) return a;
        }

        return -1;  //should not happen
    }

    /**
     * Binary search solution
     * idea:
     * 1) special integer can be only at the end of n/4-th intervals
     * 2) for each candidate find leftmost and rightmost positions using BS
     *
     * time ~ O(logN)
     * space ~ O(1)
     *
     * many attempts
     * BEATS ~ 100%
     */
    public int findSpecialInteger(int[] arr) {
        int n = arr.length;
        //idea: special integer can be only at the end of n/4-th intervals
        int[] candidates = new int[]{arr[n / 4], arr[n / 2], arr[3 * n / 4]};

        //for each candidate find its leftmost and rightmost positions to calculate amount of occurrences
        for (int c : candidates) {
            int leftPos = findLeft(arr, c);
            int rightPos = findRight(arr, c);
            if (rightPos - leftPos + 1 > n / 4) return c;
        }

        return -1;  //should not happen
    }

    private int findRight(int[] arr, int c) {
        int low = 0;
        int high = arr.length - 1;
        int res = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] < c) {
                low = mid + 1;
            } else if (arr[mid] > c) {
                high = mid - 1;
            } else {
                res = mid;
                low = mid + 1;
            }
        }

        return res;
    }

    private int findLeft(int[] arr, int c) {
        int low = 0;
        int high = arr.length - 1;
        int res = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] < c) {
                low = mid + 1;
            } else if (arr[mid] > c) {
                high = mid - 1;
            } else {
                res = mid;
                high = mid - 1;
            }
        }

        return res;
    }
}
