package solving_techniques.different;

import java.util.Arrays;

/**
 * 670. Maximum Swap (medium)
 * https://leetcode.com/problems/maximum-swap/
 * <p>
 * #Company (17.02.2025): 0 - 3 months Meta 21 Amazon 3 0 - 6 months Google 10 Microsoft 4 Bloomberg 3 TikTok 2 6 months ago Apple 2
 * <p>
 * You are given an integer num. You can swap two digits at most once to get the maximum valued number.
 * <p>
 * Return the maximum valued number you can get.
 * <p>
 * Example 1:
 * Input: num = 2736
 * Output: 7236
 * Explanation: Swap the number 2 and the number 7.
 * <p>
 * Example 2:
 * Input: num = 9973
 * Output: 9973
 * Explanation: No swap.
 * <p>
 * Constraints:
 * 0 <= num <= 10^8
 */
public class MaximumSwap {
    /**
     * NOT fully SOLVED by myself:
     * idea:
     * 1) keep 'rightMostIdx' array such as:
     * for each i we keep the right most index of the element which is max element of subarray [i, arr.length - 1]
     * 2) find the left most element which arr[i] < arr[rightMostIdx[i]]
     * 3) swap i and rightMostIdx[i] elements
     * <p>
     * time to spend ~ 50 mins
     * <p>
     * time ~ O(n)
     * space ~ O(n), where n = log10 num
     * <p>
     * BEATS ~ 30%
     */
    public int maximumSwap(int num) {
        if (num <= 9) return num;

        String s = "" + num;
        char[] arr = s.toCharArray();

        //time ~ O(log10 num)
        //space ~ O(log10 num)

        int[] rightMostIdx = new int[arr.length];   //for each i we keep index of max element of subarray [i, arr.length - 1]
        rightMostIdx[arr.length - 1] = arr.length - 1;

        for (int i = arr.length - 2; i >= 0; i--) {
            if (arr[i] > arr[rightMostIdx[i + 1]]) {
                rightMostIdx[i] = i;
            } else {
                rightMostIdx[i] = rightMostIdx[i + 1];
            }
        }

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < arr[rightMostIdx[i]]) {
                //swap
                char temp = arr[i];
                arr[i] = arr[rightMostIdx[i]];
                arr[rightMostIdx[i]] = temp;
                return Integer.valueOf(new String(arr));
            }
        }

        return num;
    }

    // 1 9 9 4
    // 2 2 2 3 => will swap arr[0] and arr[2]

    // 9 8 3 6 8
    // 0 4 4 4 4 => will swap arr[2] and arr[4]
}
