package solving_techniques.p19_LongestCommonSubstring;

import java.util.Arrays;

/**
 * https://www.designgurus.io/course-play/grokking-dynamic-programming/doc/637f6a06046a2a95a84d276e
 * OR
 * https://www.geeksforgeeks.org/longest-bitonic-subsequence-dp-15/
 *
 * Given a number sequence, find the length of its Longest Bitonic Subsequence (LBS).
 * A subsequence is considered bitonic if it is monotonically increasing and then monotonically decreasing.
 *
 * Example 1:
 * Input: {4,2,3,6,10,1,12}
 * Output: 5
 * Explanation: The LBS is {2,3,6,10,1}.
 *
 * Example 2:
 * Input: {4,2,5,9,7,6,10,3,1}
 * Output: 7
 * Explanation: The LBS is {4,5,9,7,6,3,1}.
 */
public class LongestBitonicSubsequence {
    public static void main(String[] args) {
        int[] arr1 = {4,2,3,6,10,1,12};
        System.out.println(longestBitonicSubsequence(arr1));    //expected 5

        int[] arr2 = {4,2,5,9,7,6,10,3,1};
        System.out.println(longestBitonicSubsequence(arr2));    //expected 7

        int[] arr3 = {1,2,3,4,5};
        System.out.println(longestBitonicSubsequence(arr3));    //expected 0
    }

    /**
     * KREVSKY SOLUTION:
     * idea:
     * 1) fill dp for increasing subsequences: dp[i] = the longest increasing subsequence (IS) in range [0..i]
     * 2) fill dp for decreasing subsequences: dp[i] = the longest decreasing subsequence (DS) in range [i..n]
     * After that for each i-th element of arr we have the length of IS/DS that ends/start with this i-th element.
     * The common length of bitonic subsequence that has i-th element as pivot (or how can it be called??) = IS + DS - 1,
     * "-1" since i-th element is included into IS and DS also
     *      so total[i] = IS[i] + DS[i] - 1;
     * BUT: if IS (and/or DS) has dp[i] = 1, it means there is no IS (or DS) for i-th element
     *      => BS with pivot = i-th element does not exist => set total[i] = 0 in this case
     *
     * time to think + solve ~ 20 + 10 = 30 mins
     *
     * time ~ O(arr.ength^2)
     * space ~ O(arr.length)
     *
     * 1 attempt
     *
     * NOTE: the same solution is here https://www.geeksforgeeks.org/longest-bitonic-subsequence-dp-15/
     * BUT they do not consider case "if IS (and/or DS) has dp[i] = 1" and say that
     *      A sequence, sorted in increasing order is considered Bitonic with the decreasing part as empty.
     *      Similarly, decreasing order sequence is considered Bitonic with the increasing part as empty.
     */

    // initial data:
    // 4,2,5,9,7,6,10,3,1
    //
    // increasing dp:
    // 1 1 2 3 3 3 4 2 1
    //
    // decreasing dp (from i-th to the end)
    // 3 2 3 5 4 3 3 2 1
    //
    // result (if both elements are not 1 => sum - 1, else = 0)
    // 0 0 4 7 6 5 6 3 0
    // result = 7
    public static int longestBitonicSubsequence(int[] arr) {
        int[] increase = fillIncreaseArr(arr);
        int[] decrease = fillDecreaseArr(arr);

        int[] total = new int[arr.length];	//or use one existing arrs
        int result = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if (increase[i] == 1 || decrease[i] == 1) {
                total[i] = 0;
            } else {
                total[i] = increase[i] + decrease[i] - 1;
            }

            result = Math.max(result, total[i]);
        }

        return result;
    }

    private static int[] fillIncreaseArr(int[] arr) {
        int[] dp = new int[arr.length];
        Arrays.fill(dp, 1);

        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        return dp;
    }

    private static int[] fillDecreaseArr(int[] arr) {
        int[] dp = new int[arr.length];
        Arrays.fill(dp, 1);

        //fill from the right to the left
        for (int i = arr.length - 2; i >= 0; i--) {
            for (int j = arr.length - 1; j > i; j--) {
                if (arr[j] < arr[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        return dp;
    }
}