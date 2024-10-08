package solving_techniques.p11_ModifiedBinarySearch;

/**
 * 153. Find Minimum in Rotated Sorted Array (medium)
 * https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/
 *
 * #Company: Amazon Apple Bloomberg Facebook Goldman Sachs Google LinkedIn Microsoft Salesforce Uber VMware Walmart Labs Yandex
 *
 * Suppose an array of length n sorted in ascending order is rotated between 1 and n times.
 * For example, the array nums = [0,1,2,4,5,6,7] might become:
 *
 * [4,5,6,7,0,1,2] if it was rotated 4 times.
 * [0,1,2,4,5,6,7] if it was rotated 7 times.
 * Notice that rotating an array [a[0], a[1], a[2], ..., a[n-1]] 1 time results in the array [a[n-1], a[0], a[1], a[2], ..., a[n-2]].
 * Given the sorted rotated array nums of unique elements, return the minimum element of this array.
 * You must write an algorithm that runs in O(log n) time.
 *
 * Example 1:
 * Input: nums = [3,4,5,1,2]
 * Output: 1
 * Explanation: The original array was [1,2,3,4,5] rotated 3 times.
 *
 * Example 2:
 * Input: nums = [4,5,6,7,0,1,2]
 * Output: 0
 * Explanation: The original array was [0,1,2,4,5,6,7] and it was rotated 4 times.
 *
 * Example 3:
 * Input: nums = [11,13,15,17]
 * Output: 11
 * Explanation: The original array was [11,13,15,17] and it was rotated 4 times.
 *
 * Constraints:
 * n == nums.length
 * 1 <= n <= 5000
 * -5000 <= nums[i] <= 5000
 * All the integers of nums are unique.
 * nums is sorted and rotated between 1 and n times.
 */
public class FindMinimumInRotatedSortedArray {
    /**
     * KREVSKY SOLUTION
     * time to solve ~ 35 mins
     * time ~ O(logN)
     * space ~ O(1)
     * 2 attempts
     */

    // 7,8,1,2,3,4,5,6
    // 2,1
    // low = 1
    // high = 1
    // mid = 0 => num = 2
    public int findMinKREV(int[] nums) {
        int low = 0;
        int high = nums.length - 1;

        while (low <= high) {
            if (nums[low] <= nums[high]) {
                //sorted (low, high) array => return nums[low]
                return nums[low];
            }

            int mid = (low + high)/2;
            if (nums[mid] >= nums[low]) {
                //It means that left part is sorted.
                //And since array is rotated => nums[low] > nums[high] => nums[low] cannot be minimum
                // => minimum belongs to right (unsorted) part
                low = mid + 1;
            } else {//}if (nums[mid] < nums[high]) {
                //NOTE: mid cannot be equals to high => we do not write such condition
                high = mid;
            }
        }

        return nums[low];
    }

    /**
     * https://www.youtube.com/watch?v=nIVW4P8b1VA
     */
    public int findMinNeetCode(int[] nums) {
        int low = 0;
        int high = nums.length - 1;
        int res = nums[0];

        while (low <= high) {
            if (nums[low] < nums[high]) {
                //sorted (low, high) array
                res = Math.min(res, nums[low]);
                break;
            }

            int mid = (low + high)/2;
            res = Math.min(res, nums[mid]);

            if (nums[mid] >= nums[low]) {
                //It means that left part is sorted.
                //Let's search in the right (unsorted) part
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return res;
    }
}
