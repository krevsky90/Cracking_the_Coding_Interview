package solving_techniques.p15_0or1_Knapsack;

/**
 * https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/63a47de13f028e60d7da5a88
 * OR
 * 416. Partition Equal Subset Sum
 * https://leetcode.com/problems/partition-equal-subset-sum/description/
 *
 * Given an integer array nums, return true if you can partition the array into two subsets
 * such that the sum of the elements in both subsets is equal or false otherwise.
 *
 * Example 1:
 * Input: nums = [1,5,11,5]
 * Output: true
 * Explanation: The array can be partitioned as [1, 5, 5] and [11].
 *
 * Example 2:
 * Input: nums = [1,2,3,5]
 * Output: false
 * Explanation: The array cannot be partitioned into equal sum subsets.
 *
 * Constraints:
 * 1 <= nums.length <= 200
 * 1 <= nums[i] <= 100
 */
public class EqualSubsetSumPartition {
    /**
     * KREVSKY SOLUTION:
     * time to solve ~ 15 mins
     * time ~ O(nums.length*sum(nums)/2)
     * space ~ O(nums.length*sum(nums)/2)
     * 3 attempts (forget to return false is capacity is odd)
     */
    public boolean canPartition(int[] nums) {
        int capacity = 0;
        for (int n : nums) {
            capacity += n;
        }

        if (capacity % 2 != 0) return false;

        int targetC = capacity/2;

        int[][] dp = new int[nums.length + 1][targetC + 1];
        for (int i = 0; i < nums.length; i++) {
            for (int w = 0; w <= targetC; w++) {
                if (nums[i] > w) {
                    dp[i + 1][w] = dp[i][w];
                } else {
                    dp[i + 1][w] = Math.max(dp[i][w], dp[i][w - nums[i]] + nums[i]);
                }

                if (dp[i + 1][w] == targetC) {
                    return true;
                }
            }
        }

        return false;
    }
}
