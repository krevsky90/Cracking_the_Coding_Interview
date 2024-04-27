package solving_techniques.p15_0or1_Knapsack;

import java.util.Arrays;

/**
 * https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/63a48ec9a6f530f607324a50
 * OR
 * 494. Target Sum (medium)
 * https://leetcode.com/problems/target-sum
 * <p>
 * You are given an integer array nums and an integer target.
 * <p>
 * You want to build an expression out of nums by adding one of the symbols '+' and '-' before each integer in nums
 * and then concatenate all the integers.
 * <p>
 * For example, if nums = [2, 1], you can add a '+' before 2 and a '-' before 1
 * and concatenate them to build the expression "+2-1".
 * <p>
 * Return the number of different expressions that you can build, which evaluates to target.
 * <p>
 * Example 1:
 * Input: nums = [1,1,1,1,1], target = 3
 * Output: 5
 * Explanation: There are 5 ways to assign symbols to make the sum of nums be target 3.
 * -1 + 1 + 1 + 1 + 1 = 3
 * +1 - 1 + 1 + 1 + 1 = 3
 * +1 + 1 - 1 + 1 + 1 = 3
 * +1 + 1 + 1 - 1 + 1 = 3
 * +1 + 1 + 1 + 1 - 1 = 3
 * <p>
 * Example 2:
 * Input: nums = [1], target = 1
 * Output: 1
 * <p>
 * Constraints:
 * 1 <= nums.length <= 20
 * 0 <= nums[i] <= 1000
 * 0 <= sum(nums[i]) <= 1000
 * -1000 <= target <= 1000
 */
public class ProblemChallenge2_TargetSum {
    public static void main(String[] args) {
        int[] arr1 = {1, 1, 2, 3};
        int target1 = 1;
        int[] arr2 = {0, 0, 1};
        int target2 = 1;
        ProblemChallenge2_TargetSum obj = new ProblemChallenge2_TargetSum();

//        System.out.println(obj.countWays(arr1, target1));
//        System.out.println(obj.countWaysOptimized(arr2, target2));
//        System.out.println(obj.findTargetSumWays(arr2, target2));

        System.out.println(obj.countWaysOptimized2(arr1, target1));
    }

    /**
     * KREVSKY SOLUTION:
     * idea: each time we have 2 cases: to add '+' to '-' to arr[idx]
     * base (end) case: we handle all numbers => need to check if out sum = target
     * <p>
     * time to solve ~ 10 mins
     * <p>
     * time ~ O(2^n), where n = arr.length
     * <p>
     * 1 attempt
     * <p>
     * BEATS = 22%
     */
    public int countWays(int[] arr, int target) {
        return countWays(arr, 0, 0, target);
    }

    private int countWays(int[] arr, int sum, int idx, int target) {
        if (idx == arr.length) {
            if (sum == target) return 1;
            else return 0;
        }

        int positiveCase = countWays(arr, sum + arr[idx], idx + 1, target);
        int negativeCase = countWays(arr, sum - arr[idx], idx + 1, target);
        return positiveCase + negativeCase;
    }

    /**
     * SOLUTION #2: DOES NOT WORK 100% PROPERLY! search "INCORRECT LOGIC' words
     * memoization
     * https://leetcode.com/problems/target-sum/solutions/4933869/subset-extension-dp-java-5ms-intuitive/
     * idea:
     * let's assume: S1 - S2 = target, where
     * S1 = sum of numbers of arr that we take with "+"
     * S2 = sum of numbers of arr that we take with "-"
     * S1 + S2 = totalSum of all numbers of arr
     * then (S1 - S2) + (S1 + S2) = target + totalSum
     * i.e. 2*S1 = target + totalSum
     * <p>
     * we can paraphrase the initial problem as:
     * count all ways to get the numbers with sum = (target + totalSum)/2
     * <p>
     * it is 2D-memo approach: chosen elements + target
     *
     */
    public int countWaysOptimized(int[] arr, int target) {
        int totalSum = 0;
        for (int n : arr) {
            totalSum += n;
        }

        //validation:
        if ((totalSum + target) % 2 != 0) return 0;
        if (totalSum < Math.abs(target)) return 0;

        int s1 = (totalSum + target) / 2;
        int[][] memo = new int[arr.length + 1][s1 + 1];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }

        //!!! INCORRECT LOGIC IF elements of array are 0! then we will get 0 options with the same effect: include 0 or exclude => 0, 0, 1 and target = 1 will give us 4 ways, for example!
        for (int i = 0; i < arr.length + 1; i++) {
            memo[i][0] = 1; //since there is only 1 combination to get target = 0 (i.e. 0*a1 + 0*a2 + ...)
        }

        for (int j = 1; j < s1 + 1; j++) {
            memo[0][j] = 0;
        }

        return countWaysOptimized(arr, arr.length, s1, memo);
    }

    private int countWaysOptimized(int[] arr, int i, int target, int[][] memo) {
        if (memo[i][target] != -1) {
            return memo[i][target];
        }

        if (target - arr[i - 1] >= 0) {
            int include = countWaysOptimized(arr, i - 1, target - arr[i - 1], memo);
            int exclude = countWaysOptimized(arr, i - 1, target, memo);
            return memo[i][target] = include + exclude;
        } else {
            return memo[i][target] = countWaysOptimized(arr, i - 1, target, memo);
        }
    }

    //or we can delete prefilling memo by 0 and 1 and add base conditions to count method
    //BUT in this case we need to check out of bound before we check memo[][] != -1
    //DOES NOT WORK 100% PROPERLY! search "INCORRECT LOGIC' words
    public int countWaysOptimized2(int[] arr, int target) {
        int totalSum = 0;
        for (int n : arr) {
            totalSum += n;
        }

        //validation:
        if ((totalSum + target) % 2 != 0) return 0;
        if (totalSum < Math.abs(target)) return 0;

        int s1 = (totalSum + target) / 2;
        int[][] memo = new int[arr.length + 1][s1 + 1];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }

        return countWaysOptimized2(arr, arr.length, s1, memo);
    }

    private int countWaysOptimized2(int[] arr, int i, int target, int[][] memo) {
        if (target < 0 || i < 0) return 0;

        //!!! INCORRECT LOGIC IF elements of array are 0! then we will get 0 options with the same effect: include 0 or exclude => 0, 0, 1 and target = 1 will give us 4 ways, for example!
        if (target == 0) return 1;

        //i.e. target > 0
        if (i == 0) return 0;

        if (memo[i][target] != -1) {
            return memo[i][target];
        }

        if (target - arr[i - 1] >= 0) {
            int include = countWaysOptimized2(arr, i - 1, target - arr[i - 1], memo);
            int exclude = countWaysOptimized2(arr, i - 1, target, memo);
            return memo[i][target] = include + exclude;
        } else {
            return memo[i][target] = countWaysOptimized2(arr, i - 1, target, memo);
        }
    }

    /**
     * SOLUTION #3: bottom-up
     * https://leetcode.com/problems/target-sum/solutions/4933869/subset-extension-dp-java-5ms-intuitive/
     *
     * idea:
     * As you have two subsets, denote the one with positive impact as S1 and negative impact as S2.
     * S1-S2 = target ---------------(1)
     * S1+S2 = totalSum(arr) ------(2)
     *
     * Now you just have to find the subset whose sum is equal to (totalSum+target)/2 by adding eq 1&2.
     */
    public int findTargetSumWays(int[] nums, int target) {
        int sum = findSum(nums);
        int tsum = (sum + target) / 2;
        if ((sum + target) % 2 != 0 || sum < Math.abs(target))
            return 0;
        return dpPartition(nums, nums.length, tsum);

    }

    int findSum(int[] nums) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        return sum;
    }

    int dpPartition(int[] nums, int N, int sum) {
        int[][] dp = new int[N + 1][sum + 1];

        for (int i = 0; i <= N; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i <= sum; i++) {
            dp[0][i] = 0;
        }

        for (int i = 1; i <= N; i++) {
            //CORRECT LOGIC: since we start with j = 0, we cover case when arr[i] = 0 and recalculate value that is in dp[i][0]
            for (int j = 0; j <= sum; j++) {
                if (nums[i - 1] <= j) {
                    dp[i][j] = dp[i - 1][j - nums[i - 1]] + dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[N][sum];

    }
}

