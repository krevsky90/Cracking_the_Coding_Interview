package solving_techniques.p1_SlidingWindow;

/**
 * 713. Subarray Product Less Than K
 * https://leetcode.com/problems/subarray-product-less-than-k/
 * <p>
 * Given an array of integers nums and an integer k, return the number of contiguous subarrays where the product of all the elements in the subarray is strictly less than k.
 * Example 1:
 * Input: nums = [10,5,2,6], k = 100
 * Output: 8
 * Explanation: The 8 subarrays that have product less than 100 are:
 * [10], [5], [2], [6], [10, 5], [5, 2], [2, 6], [5, 2, 6]
 * <p>
 * Note that [10, 5, 2] is not included as the product of 100 is not strictly less than k.
 */
public class SubarrayProductLessThanK {
    /**
     * KREVSKY SOLUTION #2 (06.02.2025):
     * time to solve ~ 30 mins
     * <p>
     * 2 attempts:
     * - did not handle case nums[i] < k properly
     * <p>
     * BEATS ~ 99%
     */
    public int numSubarrayProductLessThanK2(int[] nums, int k) {
        int result = 0;
        int start = 0;
        int tempProduct = 1;
        for (int i = 0; i < nums.length; i++) {
            //edge case:
            //OR we can remove this if and handle edge cases where k is 0 or 1 (no subarrays possible) BEFORE while-loop
            // like this:
            // if (k <= 1) return 0;
            if (nums[i] >= k) {
                start = i + 1;
                tempProduct = 1;
                continue;
            }

            tempProduct *= nums[i];

            while (tempProduct >= k) {
                tempProduct /= nums[start];
                start++;
            }

            result += (i - start + 1);
        }

        return result;
    }



    /**
     * https://www.geeksforgeeks.org/number-subarrays-product-less-k/
     * <p>
     * Idea:
     * Case 1. p*x < k
     * This means we can move the window?s right bound one step further. How many contiguous arrays does this step produce? It is: 1 + (end-start).
     * Indeed, the element itself comprises an array, and also we can add x to all contiguous arrays which have right border at end.
     * There are as many such arrays as the length of the window.
     */
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int p = 1;
        int start = 0;
        int result = 0;

        for (int end = 0; end < nums.length; end++) {
            p *= nums[end];

            // Move left bound so guarantee that
            // p is again less than k.

            //start < end condition is required for case when k < min element of arr
            while (start < end && p >= k) {
                p /= nums[start];
                start++;
            }

            //we can move the window?s right bound one step further.
            //How many contiguous arrays does this step produce?
            //It is: 1 + (end-start).
            //Indeed, the element itself comprises an array,
            //and also we can add x to all contiguous arrays which have right border at end.

            //p < k condition is necessary for case when while was interrupted due to start = end
            if (p < k) {
                result += (end - start) + 1;
            }
        }

        return result;
    }
}
