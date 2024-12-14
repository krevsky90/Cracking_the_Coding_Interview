package solving_techniques.p13_TopKElements;

import java.util.PriorityQueue;

/**
 * 1438. Longest Continuous Subarray With Absolute Diff Less Than or Equal to Limit (medium)
 * https://leetcode.com/problems/longest-continuous-subarray-with-absolute-diff-less-than-or-equal-to-limit/
 * <p>
 * #Company: 0 - 3 months Uber 15 Amazon 5 Google 4 Capital One 3 Meta 2 6 months ago Microsoft 2 Bloomberg 2 Databricks 2 eBay 2 PhonePe 2 Nutanix 2
 * <p>
 * Given an array of integers nums and an integer limit,
 * return the size of the longest non-empty subarray such that the absolute difference between any two elements of this subarray
 * is less than or equal to limit.
 * <p>
 * Example 1:
 * Input: nums = [8,2,4,7], limit = 4
 * Output: 2
 * Explanation: All subarrays are:
 * [8] with maximum absolute diff |8-8| = 0 <= 4.
 * [8,2] with maximum absolute diff |8-2| = 6 > 4.
 * [8,2,4] with maximum absolute diff |8-2| = 6 > 4.
 * [8,2,4,7] with maximum absolute diff |8-2| = 6 > 4.
 * [2] with maximum absolute diff |2-2| = 0 <= 4.
 * [2,4] with maximum absolute diff |2-4| = 2 <= 4.
 * [2,4,7] with maximum absolute diff |2-7| = 5 > 4.
 * [4] with maximum absolute diff |4-4| = 0 <= 4.
 * [4,7] with maximum absolute diff |4-7| = 3 <= 4.
 * [7] with maximum absolute diff |7-7| = 0 <= 4.
 * Therefore, the size of the longest subarray is 2.
 * <p>
 * Example 2:
 * Input: nums = [10,1,2,4,7,2], limit = 5
 * Output: 4
 * Explanation: The subarray [2,4,7,2] is the longest since the maximum absolute diff is |2-7| = 5 <= 5.
 * <p>
 * Example 3:
 * Input: nums = [4,2,2,2,4,4,2,2], limit = 0
 * Output: 3
 * <p>
 * Constraints:
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^9
 * 0 <= limit <= 10^9
 */
public class LongestContinuousSubarrayWithAbsoluteDiffLessThanOrEqualToLimit {
    /**
     * KREVSKY SOLUTION: gives TLE!
     * idea:
     * 1) use sliding window
     * 2) use min heap and max heap to track current maximum and minimum
     * <p>
     * time to solve ~ 30 mins
     * time ~ O(n*logn)
     * space ~ O(n)
     * <p>
     * 1 attempt
     * <p>
     * gives TLE
     */
    public int longestSubarray(int[] nums, int limit) {
        int start = 0;
        int result = 1;
        PriorityQueue<Integer> maxQ = new PriorityQueue<>((a, b) -> b - a);
        PriorityQueue<Integer> minQ = new PriorityQueue<>();

        for (int i = 0; i < nums.length; i++) {
            maxQ.add(nums[i]);
            minQ.add(nums[i]);

            while (maxQ.peek() - minQ.peek() > limit) {
                maxQ.remove(nums[start]);
                minQ.remove(nums[start]);
                start++;
            }

            result = Math.max(result, i - start + 1);
        }

        return result;
    }

    /**
     * Official solution
     * = KREVSKY SOLUTION + idea #1 (see below in the comments)
     *
     * BEATS ~ 30%
     */
    public int longestSubarray2(int[] nums, int limit) {
        int start = 0;
        int result = 1;
        //store pairs(value, index in the nums array)
        PriorityQueue<int[]> maxQ = new PriorityQueue<>((a,b) -> b[0] - a[0]);
        PriorityQueue<int[]> minQ = new PriorityQueue<>((a,b) -> a[0] - b[0]);

        for (int i = 0; i < nums.length; i++) {
            maxQ.add(new int[]{nums[i], i});
            minQ.add(new int[]{nums[i], i});

            while (maxQ.peek()[0] - minQ.peek()[0] > limit) {
                //optimization: if subsequence is [startElement ... 100 elements... min .. 2 elements .. max]
                // then we can move start directly to min(index(max), index(min)) + 1
                int newStartPos = Math.min(maxQ.peek()[1], minQ.peek()[1]) + 1;
                start = newStartPos;
                // idea #1
                // and remove elements from max heap that might affect our further calculations:
                // i.e. it can be max element that is top of max heap, BUT out of window => we need to remove it!
                // if (after this removal) new top element is also outside of the window, we also remove it)
                // but if new top element is inside the window => we will not remove it
                // so.. we remove only elements that can affect us, but should not, because they outside of the window
                // => less removals, then we would do traversing from old start position to new start position and removing elements from both queues
                while (maxQ.peek()[1] < start) {
                    maxQ.poll();
                }

                while (minQ.peek()[1] < start) {
                    minQ.poll();
                }
            }

            result = Math.max(result, i - start + 1);
        }

        return result;
    }
}
