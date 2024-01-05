package solving_techniques.p15_0or1_Knapsack;

/**
 * https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/63a4810a4265331fa1ecf4aa
 * OR
 * https://www.javatpoint.com/subset-sum-problems
 *
 * Given a set of positive numbers, determine if a subset exists whose sum is equal to a given number ?S?.
 *
 * Example 1:
 * Input: {1, 2, 3, 7}, S=6
 * Output: True
 * The given set has a subset whose sum is '6': {1, 2, 3}
 *
 * Example 2:
 * Input: {1, 2, 7, 1, 5}, S=10
 * Output: True
 * The given set has a subset whose sum is '10': {1, 2, 7}
 *
 * Example 3:
 * Input: {1, 3, 4, 8}, S=6
 * Output: False
 * The given set does not have any subset whose sum is equal to '6'.
 */
public class SubsetSum {
    public static void main(String[] args) {
        int[] arr1 = {1, 2, 3, 7};
        int s1 = 6;
        System.out.println(isSubsetSumKrevTopDown(arr1, s1));    //expected = true
        System.out.println(isSubsetSumKrevBottomUp(arr1, s1));    //expected = true


        int[] arr2 = {2, 3, 5, 7, 10};
        int s2 = 14;
        System.out.println(isSubsetSumKrevTopDown(arr2, s2));    //expected = true
        System.out.println(isSubsetSumKrevBottomUp(arr2, s2));    //expected = true

        int[] arr3 = {1, 3, 4, 8};
        int s3 = 6;
        System.out.println(isSubsetSumKrevTopDown(arr3, s3));    //expected = false
        System.out.println(isSubsetSumKrevBottomUp(arr3, s3));    //expected = false
    }

    /**
     * KREVSKY SOLUTION:
     * idea: top-down DP (since we DO NOT need to solve ALL sub-problems and fill all cells of dp matrix! see https://youtu.be/Q2vDTam9qMQ?t=470) + recursive approach + memoization
     * time to solve ~ 20 mins
     * 2 attempts:
     * - forgot base case #2: "if (sum < 0)"
     *
     * time ~ O(arr.length*sum)
     * space ~ O(arr.length*sum)
     */
    public static boolean isSubsetSumKrevTopDown(int arr[], int sum) {
        //do not use boolean, since we need 3 possible values: initial, true, false
        // => for example:
        // initial = 0
        // negative = -1
        // positive = 1
        int [][] dp = new int[arr.length][sum + 1];
        return isSubsetSumKrevTopDown(arr, sum, 0, dp) == 1 ? true : false;
    }

    /**
     * top-down + memoization
     */
    private static int isSubsetSumKrevTopDown(int arr[], int sum, int currentItem, int[][] dp) {
        //base case 1:
        if (sum == 0) {
            return 1;
        }

        //base case 2:
        if (sum < 0) {
            return -1;
        }

        //base case 3: all elements are used, but sum is still > 0 => no solution
        if (currentItem == arr.length) {
            return -1;
        }

        if (dp[currentItem][sum] != 0) {
            return dp[currentItem][sum];
        } else {
            int include = isSubsetSumKrevTopDown(arr, sum - arr[currentItem], currentItem + 1, dp);
            int notInclude = isSubsetSumKrevTopDown(arr, sum, currentItem + 1, dp);
            dp[currentItem][sum] = (include == 1 || notInclude == 1) ? 1 : -1;
            return dp[currentItem][sum];
        }
    }

    /**
     * KREVSKY:
     * NOTE: bad and dirty solution!!=
     * idea ~ bottom-up DP + iterative approach + memoization
     * time to solve ~ 40-50 mins
     * > 5 attempts
     * time ~ O(N*sum)
     * space ~ O(N*sum)
     */

    //        0   1   2   3   4   5   6   7   8   9   10   11   12   13   14
    //    2   1   0   1   0   ..
    //    3   1   0   1   1   0   1
    //    5
    //    7
    //    10
    public static boolean isSubsetSumKrevBottomUp(int arr[], int sum) {
        int rows = arr.length;
        int[][] dp = new int[rows][sum + 1];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j <= sum; j++) {
                if (j == 0 || j == arr[i]) {
                    dp[i][j] = 1;
                } else if (i > 0 && j < arr[i]) {
                    dp[i][j] = dp[i - 1][j];
                } else if (i > 0) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - arr[i]]);
                }

                if (j == sum && dp[i][j] == 1) {
                    return true;
                }
            }
        }

        return false;
    }


}