package solving_techniques.p17_FibonacciNumbers;

import java.util.Arrays;

/**
 * 198. House Robber
 * https://leetcode.com/problems/house-robber
 * <p>
 * You are a professional robber planning to rob houses along a street.
 * Each house has a certain amount of money stashed,
 * the only constraint stopping you from robbing each of them is that adjacent houses have security systems
 * connected and it will automatically contact the police if two adjacent houses were broken into on the same night.
 * <p>
 * Given an integer array nums representing the amount of money of each house,
 * return the maximum amount of money you can rob tonight without alerting the police.
 * <p>
 * Example 1:
 * Input: nums = [1,2,3,1]
 * Output: 4
 * Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
 * Total amount you can rob = 1 + 3 = 4.
 * <p>
 * Example 2:
 * Input: nums = [2,7,9,3,1]
 * Output: 12
 * Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
 * Total amount you can rob = 2 + 9 + 1 = 12.
 * <p>
 * Constraints:
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 400
 */
public class HouseRobber {
    /**
     * KREVSKY SOLUTION:
     * idea: top-down + memoization
     * <p>
     * time to solve ~ 11 mins
     * <p>
     * time ~ O(n)
     * space ~ O(n)
     * <p>
     * 2 attempts:
     * - compilation error: forgot to add 'dp' to rob(.. ) method where it is called
     * <p>
     * BEATS = 100%
     */
    public int rob(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, -1);
        return rob(nums, nums.length - 1, dp);
    }

    private int rob(int[] nums, int i, int[] dp) {
        if (i < 0) return 0;

        if (dp[i] != -1) return dp[i];

        int result = 0;
        //if take i-th element
        int take = nums[i] + rob(nums, i - 2, dp);
        int notTake = rob(nums, i - 1, dp);
        return dp[i] = Math.max(take, notTake);
    }
}
