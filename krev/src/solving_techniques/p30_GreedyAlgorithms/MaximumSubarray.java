package solving_techniques.p30_GreedyAlgorithms;

/**
 * 53. Maximum Subarray (medium)
 * https://leetcode.com/problems/maximum-subarray
 *
 * #Company: Adobe Alibaba Amazon Apple Asana Atlassian Bloomberg ByteDance Capital One Cisco Citadel eBay Evernote Expedia Facebook Goldman Sachs Google Intel caMorgan LinkedIn Microsoft Morgan Stanley Nvidia Oracle Palantir Technologies Paypal Salesforce SAP Two Sigma Uber Walmart Labs Wayfair Yahoo Zillow
 * <p>
 * Given an integer array nums, find the subarray with the largest sum, and return its sum.
 * <p>
 * Example 1:
 * Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
 * Output: 6
 * Explanation: The subarray [4,-1,2,1] has the largest sum 6.
 * <p>
 * Example 2:
 * Input: nums = [1]
 * Output: 1
 * Explanation: The subarray [1] has the largest sum 1.
 * <p>
 * Example 3:
 * Input: nums = [5,4,-1,7,8]
 * Output: 23
 * Explanation: The subarray [5,4,-1,7,8] has the largest sum 23.
 * <p>
 * Constraints:
 * 1 <= nums.length <= 10^5
 * -10^4 <= nums[i] <= 10^4
 * <p>
 * <p>
 * Follow up: If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.
 */
public class MaximumSubarray {
    /**
     * KREVSKY SOLUTION #1:
     * idea: we skip part of subarray if its sum < 0
     * time to solve ~ 10-15 mins
     * <p>
     * time ~ O(n)
     * space ~ O(1)
     * 1 attempt
     * <p>
     * BEATS = 99%
     */
    public int maxSubArray(int[] nums) {
        int maxSum = Integer.MIN_VALUE;
        int tempSum = 0;

        for (int i = 0; i < nums.length; i++) {
            tempSum += nums[i];
            if (maxSum < tempSum) maxSum = tempSum;
            if (tempSum < 0) tempSum = 0;   //idea!
        }

        return maxSum;
    }

    /**
     * KREVSKY SOLUTION #2:
     * idea: use DP:
     * see  https://leetcode.com/problems/maximum-subarray/solutions/1595186/java-kadane-divide-and-conquer-dp/
     *
     * dp[i] = Math.max(dp[i-1] + nums[i], nums[i]);
     * time ~ O(n)
     * space ~ O(n) - can be optimized to O(1) since we can store only previous state, not all array
     */
    public int maxSubArrayDP(int[] nums) {
        int[] dp = new int[nums.length];

        int maxSum = nums[0];
        dp[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i-1] + nums[i], nums[i]);
            maxSum = Math.max(maxSum, dp[i]);
        }
        return maxSum;
    }
}

// nums = [-2,1,-3,4,-1,2,1,-5,4]
// dp = [-2 1 -2 4 3 5 6 1 5]
//  1 -3 || -3
//  -2 + 4 || 4
//  4 -1 ||-1
//  3 + 2 || 2
//  5 + 1 || 1
//  6 - 5 || -5
//  1 + 4 || 4
