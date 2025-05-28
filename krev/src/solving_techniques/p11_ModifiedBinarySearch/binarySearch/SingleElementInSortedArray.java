package solving_techniques.p11_ModifiedBinarySearch.binarySearch;


/**
 * 540. Single Element in a Sorted Array (medium)
 * https://leetcode.com/problems/single-element-in-a-sorted-array
 *
 * #Company (28.05.2025): 0 - 3 months Microsoft 13 Google 10 Amazon 6 Meta 4 0 - 6 months Bloomberg 6
 *
 * You are given a sorted array consisting of only integers where every element appears exactly twice, except for one element which appears exactly once.
 *
 * Return the single element that appears only once.
 *
 * Your solution must run in O(log n) time and O(1) space.
 *
 * Example 1:
 * Input: nums = [1,1,2,3,3,4,4,8,8]
 * Output: 2
 *
 * Example 2:
 * Input: nums = [3,3,7,7,10,11,11]
 * Output: 10
 *
 * Constraints:
 * 1 <= nums.length <= 10^5
 * 0 <= nums[i] <= 10^5
 */
public class SingleElementInSortedArray {
    public static void main(String[] args) {
        SingleElementInSortedArray obj = new SingleElementInSortedArray();

        int[] nums = {1, 2};
        obj.singleNonDuplicate(nums);
    }

    /**
     * NOT SOLVED correctly, but go the idea
     *
     * time to solve ~ 60 mins
     *
     * BEATS ~ 100%
     */
    public int singleNonDuplicate(int[] nums) {
        int lo = 0;
        int hi = nums.length - 1;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            int leftAmount = mid;
            int rightAmount = nums.length - 1 - mid;
            if (nums[mid - 1] == nums[mid]) {
                if (leftAmount % 2 == 0) {
                    //left part is not ok
                    hi = mid - 2;
                } else {
                    //left part is ok
                    lo = mid + 1;
                }
            } else if (nums[mid] == nums[mid + 1]) {
                if (rightAmount % 2 == 0) {
                    //right part is not ok
                    lo = mid + 2;
                } else {
                    //right part is ok
                    hi = mid - 1;
                }
            } else {
                return nums[mid];
            }
        }
        return nums[lo];
    }

    /**
     * Official SOLUTION #3:
     * idea:
     * The single element is at the first even index not followed by its pair.
     * After the single element, the pattern changes to being odd indexes followed by their pair. This means that the single element (an even index) and all elements after it are even indexes not followed by their pair. Therefore, given any even index in the array, we can easily determine whether the single element is to the left or to the right.
     *
     */
    public int singleNonDuplicate3(int[] nums) {
        int lo = 0;
        int hi = nums.length - 1;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (mid % 2 == 1) mid--;
            if (nums[mid] == nums[mid + 1]) {
                lo = mid + 2;
            } else {
                hi = mid;
            }
        }
        return nums[lo];
    }
}
