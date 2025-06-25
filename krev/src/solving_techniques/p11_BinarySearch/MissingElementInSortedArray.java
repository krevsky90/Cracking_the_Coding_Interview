package solving_techniques.p11_BinarySearch;

/**
 * 1060. Missing Element in Sorted Array (medium) (locked)
 * https://leetcode.com/problems/missing-element-in-sorted-array
 * <p>
 * #Company (16.05.2025): 0 - 3 months Meta 7 0 - 6 months Google 2 6 months ago Amazon 4 Arista Networks 3
 *
 * Given an integer array nums which is sorted in ascending order and all of its elements are unique and given also an integer k,
 *      return the kth missing number starting from the leftmost number of the array.
 *
 * Example 1:
 * Input: nums = [4,7,9,10], k = 1
 * Output: 5
 * Explanation: The first missing number is 5.
 *
 * Example 2:
 * Input: nums = [4,7,9,10], k = 3
 * Output: 8
 * Explanation: The missing numbers are [5,6,8,...], hence the third missing number is 8.
 *
 * Example 3:
 * Input: nums = [1,2,4], k = 3
 * Output: 6
 * Explanation: The missing numbers are [3,5,6,7,...], hence the third missing number is 6.
 *
 * Constraints:
 * 1 <= nums.length <= 5 * 10^4
 * 1 <= nums[i] <= 10^7
 * nums is sorted in ascending order, and all the elements are unique.
 * 1 <= k <= 10^8
 *
 *
 * Follow up: Can you find a logarithmic time complexity (i.e., O(log(n))) solution?
 */
public class MissingElementInSortedArray {
    /**
     * KREVSKY SOLUTION #1: naive solution
     * time to solve ~ 11 mins
     * <p>
     * time ~ O(n)
     * space ~ O(1)
     * <p>
     * 1 attempt
     * <p>
     * BEATS ~ 25%
     */
    public int missingElement(int[] nums, int k) {
        int n = nums.length;
        for (int i = 0; i < n - 1; i++) {
            int delta = nums[i + 1] - nums[i];
            if (delta != 1) {
                if (delta > k) {
                    return nums[i] + k;
                } else {
                    k -= (delta - 1);
                }
            }
        }

        return nums[n - 1] + k;
    }

    /**
     * SOLUTION #2:
     * time to solve ~ 40+ mins
     *
     * idea: use binary search,
     * set function f(i) that shows how many numbers are missed in the range [0, i]
     * if f(i) < k => left = mid + 1
     * otherwise: right = mid - 1
     *
     * I identified f(i) incorrectly, but was close to it
     * +spent a lot of time elaborating which while-condition should I use and how to move left and right
     *
     * time ~ O(logN)
     * space ~ O(1)
     *
     * BEATS ~ 100%
     */
    public int missingElementOptimized(int[] nums, int k) {
        //time ~ O(log n)
        int n = nums.length;
        int left = 0;
        int right = n - 1;
        while (left <= right) {
            int mid = left + (right - left)/2;
            int amountOfMissedNumbers = nums[mid] - nums[0] - mid;  //f(i), where i = mid
            if (amountOfMissedNumbers < k) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        //finally right < left

        return nums[right] - (nums[right] - nums[0] - right) + k;   //or  = nums[0] + right + k
    }
}

