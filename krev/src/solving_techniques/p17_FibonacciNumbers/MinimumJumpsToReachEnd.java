package solving_techniques.p17_FibonacciNumbers;

import java.util.Arrays;

/**
 * https://www.designgurus.io/course-play/grokking-dynamic-programming/doc/637f4ca6e57c531cec39ac76
 * OR
 * 45. Jump Game II
 * https://leetcode.com/problems/jump-game-ii
 * <p>
 * You are given a 0-indexed array of integers nums of length n. You are initially positioned at nums[0].
 * Each element nums[i] represents the maximum length of a forward jump from index i.
 * In other words, if you are at nums[i], you can jump to any nums[i + j] where:
 * <p>
 * 0 <= j <= nums[i] and
 * i + j < n
 * Return the minimum number of jumps to reach nums[n - 1].
 * The test cases are generated such that you can reach nums[n - 1].
 * <p>
 * Example 1:
 * Input: nums = [2,3,1,1,4]
 * Output: 2
 * Explanation: The minimum number of jumps to reach the last index is 2. Jump 1 step from index 0 to 1, then 3 steps to the last index.
 * <p>
 * Example 2:
 * Input: nums = [2,3,0,1,4]
 * Output: 2
 * <p>
 * Constraints:
 * 1 <= nums.length <= 10^4
 * 0 <= nums[i] <= 1000
 * It's guaranteed that you can reach nums[n - 1].
 */
public class MinimumJumpsToReachEnd {
    public static void main(String[] args) {
        int[] arr1 = {2,3,1,1,4};
        System.out.println(new MinimumJumpsToReachEnd().jump2(arr1));
    }
    /**
     * NOT SOLVED OPTIMALLY (used DP + recursion, but couldn't add memoization)
     * time to spend ~ 45 mins
     * <p>
     * time ~ O(n)
     */
    public int jump(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j <= i + nums[i] && j < n; j++) {
                dp[j] = Math.min(dp[j], dp[i] + 1);
            }
        }

        return dp[n - 1];
    }

    /**
     * SOLUTION #2
     */
    public int jump2(int[] nums) {
        if (nums == null || nums.length <= 1) return 0;

        int numStep = 1;
        int i = 0;
        int newI = 0;
        int maxJump = i + nums[i];
        // 2 3 1 1 4
        // i = 0
        // maxJump = 0 + 2 = 2
        // j = 1
        // j + nums[j] = 1 + 3 = 4 >= 2
        // maxJump = 4
        // newI = 1
        // j + nums[j] = 2 + 1 = 3 < maxJump
        // i = 1
        // numStep = 2
        //while (1 + nums[1] = 1 + 3 = 4 < 4)
        //return 2
        while (i + nums[i] < nums.length - 1) {
            for (int j = i + 1; j <= i + nums[i]; j++) {
                if (j + nums[j] >= maxJump) {
                    maxJump = j + nums[j];
                    newI = j;
                }
            }
            i = newI;
            numStep++;
        }

        return numStep;
    }
}
