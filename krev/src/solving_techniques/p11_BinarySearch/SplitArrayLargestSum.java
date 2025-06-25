package solving_techniques.p11_BinarySearch;

/**
 * 410. Split Array Largest Sum (hard)
 * https://leetcode.com/problems/split-array-largest-sum
 * <p>
 * #Company (30.01.2025): 0 - 3 months Google 8 Amazon 3 0 - 6 months Meta 4 Microsoft 4 Bloomberg 2 Uber 2 6 months ago Salesforce 12 PhonePe 7 Adobe 6 Apple 6 TikTok 3 DE Shaw 2 Atlassian 2 MathWorks 2 Baidu 2
 * <p>
 * Given an integer array nums and an integer k, split nums into k non-empty subarrays such that the largest sum of any subarray is minimized.
 * <p>
 * Return the minimized largest sum of the split.
 * <p>
 * A subarray is a contiguous part of the array.
 * <p>
 * Example 1:
 * Input: nums = [7,2,5,10,8], k = 2
 * Output: 18
 * Explanation: There are four ways to split nums into two subarrays.
 * The best way is to split it into [7,2,5] and [10,8], where the largest sum among the two subarrays is only 18.\
 * <p>
 * Example 2:
 * Input: nums = [1,2,3,4,5], k = 2
 * Output: 9
 * Explanation: There are four ways to split nums into two subarrays.
 * The best way is to split it into [1,2,3] and [4,5], where the largest sum among the two subarrays is only 9.
 * <p>
 * Constraints:
 * 1 <= nums.length <= 1000
 * 0 <= nums[i] <= 10^6
 * 1 <= k <= min(50, nums.length)
 */
public class SplitArrayLargestSum {
    /**
     * NOT SOLVED by myself
     * idea: binary search  -see approach #2 in Editorial on leetcode
     * suppose X is the number, which >= sum of all subarrays
     * if we can split nums into <= k subarray in such way that sum of each subarray will be <= X,
     * then X might be the answer.
     * using binary search xmin = max element of nums array, xmax = total sum of all elements of nums array
     * <p>
     * time ~ O(N*logS), where N - nums.length, S - sum of all nums' elements
     * <p>
     * time to implement ~ 10-15 mins
     * <p>
     * 1 attempt
     * <p>
     * BEATS ~ 99%
     */
    public int splitArray(int[] nums, int k) {
        long total = 0;
        long maxEl = 0;
        for (int i = 0; i < nums.length; i++) {
            total += nums[i];
            if (maxEl < nums[i]) maxEl = nums[i];
        }

        long low = maxEl;
        long high = total;
        long minSum = Long.MAX_VALUE;
        while (low <= high) {
            long mid = low + (high - low) / 2;
            if (canSplit(nums, k, mid)) {
                minSum = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return (int) minSum;
    }

    //check if we can split nums into not more than K subarrays where each sum of numbers of subarray <= bound
    private boolean canSplit(int[] nums, int k, long bound) {
        int count = 0;
        int tempSum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (tempSum + nums[i] > bound) {
                count++;
                tempSum = nums[i];
            } else {
                tempSum += nums[i];
            }
        }

        return count + 1 <= k;
    }
}
