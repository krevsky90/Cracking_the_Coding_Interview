package solving_techniques.p15_0or1_Knapsack;

/**
 * https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/63a4860bf76b432f5b473dea
 * similar to
 * 39. Combination Sum
 * https://leetcode.com/problems/combination-sum, that is implemented here: solving_techniques/p26_backtracking/CombinationSum.java
 * BUT '39. Combination Sum' is Unbounded Knapsack!
 *
 * So.. her we solve the problem for Knapsack 0/1
 *
 * Given a set of positive numbers, find the total number of subsets whose sum is equal to a given number ?S?.
 * Example 1:
 * Input: {1, 1, 2, 3}, S=4
 * Output: 3
 * The given set has '3' subsets whose sum is '4': {1, 1, 2}, {1, 3}, {1, 3}
 * Note that we have two similar sets {1, 3}, because we have two '1' in our input.
 *
 * Example 2:
 * Input: {1, 2, 7, 1, 5}, S=9
 * Output: 3
 * The given set has '3' subsets whose sum is '9': {2, 7}, {1, 7, 1}, {1, 2, 1, 5}
 *
 */
public class ProblemChallenge1_CountOfSubsetSum {
    public static void main(String[] args) {
        int[] arr1 = {1, 1, 2, 3};
        int target1 = 4;
        int[] arr2 = {1, 2, 7, 1, 5};
        int target2 = 9;

        System.out.println(combinationSum(arr1, target1));  //expected 3
        System.out.println(combinationSumTopDown(arr1, target1));  //expected 3
        System.out.println(combinationSum(arr2, target2));  //expected 3
        System.out.println(combinationSumTopDown(arr2, target2));  //expected 3
    }

    /**
     * KREVSKY SOLUTION:
     * time to solve ~ 15 mins
     *
     * time ~ O(2^n) - without memoization
     * space ~ O(1) - without memoization
     * time ~ O(n*target) - with memoization
     * space ~ O(n*target) - with memoization
     *      where n = candidates.length
     *
     * The idea is similar to Top-Down, but with inverted indexing
     *
     * 1 attempt
     */
    public static int combinationSum(int[] candidates, int target) {
        int[][] dp = new int[candidates.length][target];
        int result = combinationSumMemo(candidates, target, 0, 0, dp);
        return result;
    }

    public static int combinationSumMemo(int[] candidates, int target, int idx, int sum, int[][] dp) {
        if (sum > target) {
            return 0;
        }

        if (sum == target) {
            return 1;
        }

        if (idx == candidates.length) {
            //it means that sum != target, but we considered all candidates => return 0
            return 0;
        }

        if (dp[idx][sum] == 0) {
            int includeResult = combinationSumMemo(candidates, target, idx + 1, sum + candidates[idx], dp);
            int excludeResult = combinationSumMemo(candidates, target, idx + 1, sum, dp);
            dp[idx][sum] = includeResult + excludeResult;
        }
        return dp[idx][sum];
    }

    /**
     * Top-Down with usual indexing is:
     */
    public static int combinationSumTopDown(int[] candidates, int target) {
        int[][] dp = new int[candidates.length][target + 1];
        int result = combinationSumTopDown(candidates, target, 0, dp);
        return result;
    }

    public static int combinationSumTopDown(int[] candidates, int sum, int idx, int[][] dp) {
        if (sum < 0) {
            return 0;
        }

        if (sum == 0) {
            return 1;
        }

        if (idx == candidates.length) {
            //it means that sum != target, but we considered all candidates => return 0
            return 0;
        }

        if (dp[idx][sum] == 0) {
            int includeResult = combinationSumTopDown(candidates, sum - candidates[idx], idx + 1, dp);
            int excludeResult = combinationSumTopDown(candidates, sum, idx + 1, dp);
            dp[idx][sum] = includeResult + excludeResult;
        }
        return dp[idx][sum];
    }

}
