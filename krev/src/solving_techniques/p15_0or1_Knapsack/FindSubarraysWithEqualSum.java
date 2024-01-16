package solving_techniques.p15_0or1_Knapsack;

import java.util.HashSet;
import java.util.Set;

/**
 * 2395. Find Subarrays With Equal Sum (easy)
 * https://leetcode.com/problems/find-subarrays-with-equal-sum
 * <p>
 * Given a 0-indexed integer array nums, determine whether there exist two subarrays of length 2 with equal sum.
 * Note that the two subarrays must begin at different indices.
 * Return true if these subarrays exist, and false otherwise.
 * A subarray is a contiguous non-empty sequence of elements within an array.
 * <p>
 * Example 1:
 * Input: nums = [4,2,4]
 * Output: true
 * Explanation: The subarrays with elements [4,2] and [2,4] have the same sum of 6.
 * <p>
 * Example 2:
 * Input: nums = [1,2,3,4,5]
 * Output: false
 * Explanation: No two subarrays of size 2 have the same sum.
 * <p>
 * Example 3:
 * Input: nums = [0,0,0]
 * Output: true
 * Explanation: The subarrays [nums[0],nums[1]] and [nums[1],nums[2]] have the same sum of 0.
 * Note that even though the subarrays have the same content,
 * the two subarrays are considered different because they are in different positions in the original array.
 * <p>
 * Constraints:
 * 2 <= nums.length <= 1000
 * -10^9 <= nums[i] <= 10^9
 */
public class FindSubarraysWithEqualSum {
    /**
     * KREVSKY SOLUTION:
     * honestly don't understand why does this problem belong to Knapsack topic...
     * time to solve ~ 5 mins
     * time ~ O(n)
     * space ~ O(n)
     * 1 attempt
     */
    public boolean findSubarrays(int[] nums) {
        Set<Integer> sums = new HashSet<>();

        for (int i = 0; i < nums.length - 1; i++) {
            int tempSum = nums[i] + nums[i + 1];
            if (sums.contains(tempSum)) {
                return true;
            } else {
                sums.add(tempSum);
            }
        }

        return false;
    }
}
