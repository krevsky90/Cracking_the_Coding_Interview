package solving_techniques.p2_TwoPointers;

import java.util.ArrayList;
import java.util.List;

/**
 * https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/638f976d20f87893374e4e6b
 * <p>
 * Given an array with positive numbers and a positive target number, find all of its contiguous subarrays whose product is less than the target number.
 * <p>
 * Example 1:
 * Input: [2, 5, 3, 10], target=30
 * Output: [2], [5], [2, 5], [3], [5, 3], [10]
 * Explanation: There are six contiguous subarrays whose product is less than the target.
 * <p>
 * Example 2:
 * Input: [8, 2, 6, 5], target=50
 * Output: [8], [2], [8, 2], [6], [2, 6], [5], [6, 5]
 * Explanation: There are seven contiguous subarrays whose product is less than the target.
 * <p>
 * Similar problem is
 * 713. Subarray Product Less Than K
 * https://leetcode.com/problems/subarray-product-less-than-k/
 * - but it is a little bit simpler and = https://www.geeksforgeeks.org/number-subarrays-product-less-k/
 */
public class SubarraysWithProductLessThanTarget {

    /**
     * https://www.geeksforgeeks.org/find-all-possible-subarrays-having-product-less-than-or-equal-to-k/
     * closer to 'Sliding window' technique
     * <p>
     * Idea: If the product of all the elements of a subarray is less than or equal to K,
     * then all the subarrays possible from this subarray also has product less than or equal to K.
     * Therefore, these subarrays need to be included in the answer as well.
     * <p>
     * + good explanation #2 from https://www.geeksforgeeks.org/number-subarrays-product-less-k/
     * Case 1. p*x < k
     * This means we can move the window?s right bound one step further. How many contiguous arrays does this step produce? It is: 1 + (end-start).
     * Indeed, the element itself comprises an array, and also we can add x to all contiguous arrays which have right border at end.
     * There are as many such arrays as the length of the window.
     * <p>
     * Time Complexity: O(N^2)
     * Auxiliary Space: O(1) ???? I think it is O(N) due to tempList
     */
    public static List<List<Integer>> findSubarrays(int[] arr, int target) {
        List<List<Integer>> result = new ArrayList<>();

        int p = 1;
        int start = 0;

        // Check for empty array
        if (arr.length <= 1 || target < 0) {
            return new ArrayList<>();
        }

        for (int end = 0; end < arr.length; end++) {
            p *= arr[end];

            while (p >= target) {
                p /= arr[start];
                start++;
            }

            //Stores the subarray elements
            List<Integer> tempList = new ArrayList<>();
            //see 'explanation #2'
            //NOTE: I think, this for can be considered as 2nd pointer that moves back => this problem was classified as 'two pointers' problem
            for (int i = end; i >= start; i--) {
                tempList.add(0, arr[i]);
                result.add(new ArrayList<>(tempList));
            }
        }

        return result;
    }

}
