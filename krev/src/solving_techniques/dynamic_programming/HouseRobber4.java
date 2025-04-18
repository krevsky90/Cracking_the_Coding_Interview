package solving_techniques.dynamic_programming;

import java.util.Arrays;

/**
 * 2560. House Robber IV (medium)
 * https://leetcode.com/problems/house-robber-iv/
 * <p>
 * #Company (26.03.2025): 0 - 3 months Google 8 Amazon 5 Meta 3 Microsoft 3 Bloomberg 3 6 months ago LinkedIn 3 Adobe 2 Cashfree 2
 * <p>
 * There are several consecutive houses along a street, each of which has some money inside.
 * There is also a robber, who wants to steal money from the homes, but he refuses to steal from adjacent homes.
 * <p>
 * The capability of the robber is the maximum amount of money he steals from one house of all the houses he robbed.
 * <p>
 * You are given an integer array nums representing how much money is stashed in each house.
 * More formally, the ith house from the left has nums[i] dollars.
 * <p>
 * You are also given an integer k, representing the minimum number of houses the robber will steal from. It is always possible to steal at least k houses.
 * <p>
 * Return the minimum capability of the robber out of all the possible ways to steal at least k houses.
 * <p>
 * Example 1:
 * Input: nums = [2,3,5,9], k = 2
 * Output: 5
 * Explanation:
 * There are three ways to rob at least 2 houses:
 * - Rob the houses at indices 0 and 2. Capability is max(nums[0], nums[2]) = 5.
 * - Rob the houses at indices 0 and 3. Capability is max(nums[0], nums[3]) = 9.
 * - Rob the houses at indices 1 and 3. Capability is max(nums[1], nums[3]) = 9.
 * Therefore, we return min(5, 9, 9) = 5.
 * <p>
 * Example 2:
 * Input: nums = [2,7,9,3,1], k = 2
 * Output: 2
 * Explanation: There are 7 ways to rob the houses. The way which leads to minimum capability is to rob the house at index 0 and 4. Return max(nums[0], nums[4]) = 2.
 * <p>
 * Constraints:
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^9
 * 1 <= k <= (nums.length + 1)/2
 */
public class HouseRobber4 {
    /**
     * NOT SOLVED by myself (tied in DP top-down conditions
     * <p>
     * AI solution is below
     *  BUT it gives TLE!!! => see optimized solution by Binary Search here:
     *  \krev\src\solving_techniques\p11_ModifiedBinarySearch\binarySearch\HouseRobber4.java
     *
     * time ~ O(n*k)
     * space ~ O(n*k)
     */
    private int[][] memo;

    public int minCapability(int[] nums, int k) {
        int n = nums.length;
        memo = new int[n][k + 1];

        // Initialize memoization table with -1 to indicate uncomputed states
        for (int i = 0; i < n; i++) {
            Arrays.fill(memo[i], -1);
        }

        // Start the recursion from the first house and k houses to steal
        return dfs(nums, 0, k);
    }

    private int dfs(int[] nums, int i, int k) {
        // Base cases
        if (k == 0) return 0; // No more houses to steal from
        if (i >= nums.length) return Integer.MAX_VALUE; // Not enough houses left

        // Check memoization table
        if (memo[i][k] != -1) return memo[i][k];

        // Option 1: Skip the current house
        int skip = dfs(nums, i + 1, k);

        // Option 2: Steal from the current house
        int steal = Math.max(nums[i], dfs(nums, i + 2, k - 1));

        // Take the minimum capability between skipping and stealing
        memo[i][k] = Math.min(skip, steal);
        return memo[i][k];
    }
}
