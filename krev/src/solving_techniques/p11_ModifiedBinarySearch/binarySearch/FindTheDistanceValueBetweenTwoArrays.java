package solving_techniques.p11_ModifiedBinarySearch.binarySearch;

import java.util.Arrays;

/**
 * 1385. Find the Distance Value Between Two Arrays (easy)
 * https://leetcode.com/problems/find-the-distance-value-between-two-arrays
 * <p>
 * Companies (8.06.2025): 6 months ago Uber 3 Google 2 Amazon 2 Zepto 2
 *
 * Given two integer arrays arr1 and arr2, and the integer d, return the distance value between the two arrays.
 *
 * The distance value is defined as the number of elements arr1[i] such that there is not any element arr2[j] where |arr1[i]-arr2[j]| <= d.
 *
 * Example 1:
 * Input: arr1 = [4,5,8], arr2 = [10,9,1,8], d = 2
 * Output: 2
 * Explanation:
 * For arr1[0]=4 we have:
 * |4-10|=6 > d=2
 * |4-9|=5 > d=2
 * |4-1|=3 > d=2
 * |4-8|=4 > d=2
 * For arr1[1]=5 we have:
 * |5-10|=5 > d=2
 * |5-9|=4 > d=2
 * |5-1|=4 > d=2
 * |5-8|=3 > d=2
 * For arr1[2]=8 we have:
 * |8-10|=2 <= d=2
 * |8-9|=1 <= d=2
 * |8-1|=7 > d=2
 * |8-8|=0 <= d=2
 *
 * Example 2:
 * Input: arr1 = [1,4,2,3], arr2 = [-4,-3,6,10,20,30], d = 3
 * Output: 2
 *
 * Example 3:
 * Input: arr1 = [2,1,100,3], arr2 = [-5,-2,10,-3,7], d = 6
 * Output: 1
 *
 * Constraints:
 * 1 <= arr1.length, arr2.length <= 500
 * -1000 <= arr1[i], arr2[j] <= 1000
 * 0 <= d <= 100
 */
public class FindTheDistanceValueBetweenTwoArrays {
    /**
     * NOT SOLVED by myself
     * idea: binary search
     */
    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        Arrays.sort(arr2);

        int result = 0;
        for (int a1 : arr1) {
            if (!inRange(arr2, a1, d)) result++;
        }

        return result;
    }

    private boolean inRange(int[] arr, int a, int d) {
        //check if elements of arr meets condition: a - d <= arr[i] <= a + d
        //but let's do this not in linear time, but using binary search
        int low = 0;
        int hi = arr.length - 1;
        while (low <= hi) {
            int mid = low + (hi - low) / 2;
            if (a - d <= arr[mid] && arr[mid] <= a + d) {
                return true;
            } else if (arr[mid] < a - d) {
                low = mid + 1;
            } else {
                hi = mid - 1;
            }
        }

        return false;
    }
}
