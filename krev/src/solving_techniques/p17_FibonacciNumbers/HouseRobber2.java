package solving_techniques.p17_FibonacciNumbers;

import java.util.Arrays;

/**
 * 213. House Robber II (medium)
 * https://leetcode.com/problems/house-robber-ii/
 * <p>
 * #Company: 0 - 3 months Databricks 8 Microsoft 5  Amazon 3  Google 2 0 - 6 months Apple 3 Bloomberg 2 TikTok 2 Uber 2 6 months ago PhonePe 4 Adobe 3 ByteDance 3 Nordstrom 3 Meta 2 LinkedIn 2 Darwinbox 2 Sprinklr  2 Yaoo 2 oyo 2
 * <p>
 * You are a professional robber planning to rob houses along a street.
 * Each house has a certain amount of money stashed.
 * All houses at this place are arranged in a circle.
 * That means the first house is the neighbor of the last one.
 * Meanwhile, adjacent houses have a security system connected,
 * and it will automatically contact the police if two adjacent houses were broken into on the same night.
 * <p>
 * Given an integer array nums representing the amount of money of each house,
 * return the maximum amount of money you can rob tonight without alerting the police.
 * <p>
 * Example 1:
 * Input: nums = [2,3,2]
 * Output: 3
 * Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2), because they are adjacent houses.
 * <p>
 * Example 2:
 * Input: nums = [1,2,3,1]
 * Output: 4
 * Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
 * Total amount you can rob = 1 + 3 = 4.
 * <p>
 * Example 3:
 * Input: nums = [1,2,3]
 * Output: 3
 * <p>
 * Constraints:
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 1000
 */
public class HouseRobber2 {
    /**
     * KREVSKY SOLUTION:
     * time to solve ~ 40 mins
     * idea: DP:
     * 1) take or not to take
     * 2) consider 2 subarrays: [0, n-2] and [1, n-1]
     * <p>
     * time ~ O(n)
     * space ~ O(2*n) ~ O(n)
     * <p>
     * 3 attempts:
     * - forgot if nums.length == 1
     * - missed idea #2
     * <p>
     * BEATS ~ 100%
     */
    public int rob(int[] nums) {
        if (nums.length == 1) return nums[0];

        int[][] memo = new int[nums.length][2];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }

        int res1 = helper(0, nums.length - 1, nums, memo);
        int res2 = helper(1, nums.length, nums, memo);
        return Math.max(res1, res2);
    }

    private int helper(int start, int end, int[] nums, int[][] memo) {
        if (start >= end) return 0;

        if (memo[start][end - (nums.length - 1)] != -1) return memo[start][end - (nums.length - 1)];

        int toTake = nums[start] + helper(start + 2, end, nums, memo);
        int notTake = helper(start + 1, end, nums, memo);

        return memo[start][end - (nums.length - 1)] = Math.max(toTake, notTake);
    }

    /**
     * SOLUTION #2:
     * info: https://www.youtube.com/watch?v=hRz7MUwNUSc&list=PLUPSMCjQ-7od5IVz8ug6D-apxFLkDTsoy&index=140
     * time ~ O(n)
     * space ~ O(1)
     *
     * BEATS ~ 100%
     */
    public int rob2(int[] nums) {
        if (nums.length == 1) return nums[0];

        int res1 = helper2(0, nums.length - 1, nums);
        int res2 = helper2(1, nums.length, nums);
        return Math.max(res1, res2);
    }

    private int helper2(int start, int end, int[] nums) {
        int prevHouse = 0;
        int twoHousesAgo = 0;
        for (int i = start; i < end; i++) {
            int best = Math.max(prevHouse, nums[i] + twoHousesAgo);
            twoHousesAgo = prevHouse;
            prevHouse = best;
        }
        return Math.max(prevHouse, twoHousesAgo);
    }
}

// 1,2,3,1
// h(0) = max(1 + h(2), h(1)) = max(1 + 3, 3) = 4
//     h(2) = max(3 + h(4), h(3)) = max(3+0, 1) = 3
//     h(1) = max(2 + h(3), h(2)) = max(2 + 1, 3) = 3
