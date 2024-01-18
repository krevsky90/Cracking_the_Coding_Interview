package solving_techniques.p19_LongestCommonSubstring;

import java.util.Arrays;

/**
 * https://www.designgurus.io/course-play/grokking-dynamic-programming/doc/637f6325b26d9934cb6aab9f
 *
 * Given a number sequence, find the increasing subsequence with the highest sum.
 * Write a method that returns the highest sum.
 *
 * Example 1:
 * Input: {4,1,2,6,10,1,12}
 * Output: 32
 * Explanation: The increasing sequence is {4,6,10,12}.
 * Please note the difference, as the LIS is {1,2,6,10,12} which has a sum of '31'.
 *
 * Example 2:
 * Input: {-4,10,3,7,15}
 * Output: 25
 * Explanation: The increasing sequences are {10, 15} and {3,7,15}
 */
public class MaximumSumIncreasingSubsequence {
    public static void main(String[] args) {
        int[] arr1 = {4,1,2,6,10,1,12};
        System.out.println(getMaximumSumOfIncreasingSubsequence(arr1)); // expected 32

        int[] arr2 = {-4,10,3,7,15};
        System.out.println(getMaximumSumOfIncreasingSubsequence(arr2)); // expected 25
    }

    /**
     * KREVSKY SOLUTION:
     * idea: is the same as for src/solving_techniques/p19_LongestCommonSubstring/LongestIncreasingSubsequence.java#lengthOfLI_2(..)
     * BUT
     * 1) initially dp[i] = arr[i], rather than 1
     * 2) IF (arr[j] < arr[i]) THEN dp[i] = max(dp[i], dp[j] + arr[i]), rather than  max(dp[i], dp[j] + 1)
     *
     * time to solve + debug ~ 10 + 8 ~ 18 mins
     * time ~ O(arr.length^2)
     * space ~ O(arr.length)
     * 1 attempt
     */

    //                        i
    //    arr = 4,1,2,6, 10,1,12
    //                      j
    //    dp =  4,1,3,10,20,1,32
    public static int getMaximumSumOfIncreasingSubsequence(int[] arr) {
        int[] dp = Arrays.copyOf(arr, arr.length);

        for (int i = 1; i < arr.length; i++) {
            //for each i we need to calculate max sum of increasing subsequence of subarray from 0 to i-th index
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + arr[i]);    //the main formula!
                }
            }
        }

        //find max sum of dp[]
        int result = 0;
        for (int s : dp) {
            result = Math.max(result, s);
        }

        return result;
    }
}