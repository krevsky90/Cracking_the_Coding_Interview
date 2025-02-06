package solving_techniques.p1_SlidingWindow;

/**
 * https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/636b1d083b22faa3e89b2478
 * OR
 * 209. Minimum Size Subarray Sum (medium)
 * https://leetcode.com/problems/minimum-size-subarray-sum
 *
 * #Company (6.02.2025): 0 - 3 months BCG 8 Google 6 Amazon 4 Meta 2 0 - 6 months Microsoft 4 Bloomberg 3 TikTok 3 Goldman Sachs 2 6 months ago Adobe 5 Uber 5 Oracle 4 Yandex 3 Yahoo 3 Darwinbox 2 Expedia 2
 *
 * Given an array of positive integers and a number ?S,? find the length of the smallest contiguous subarray whose sum is greater than or equal to 'S'. Return 0 if no such subarray exists.
 *
 * Example 1:
 * Input: [2, 1, 5, 2, 3, 2], S=7
 * Output: 2
 * Explanation: The smallest subarray with a sum greater than or equal to '7' is [5, 2].
 */
public class SmallestSubarrayWithGreaterSum {
    public static void main(String[] args) {
        int[] arr = new int[]{3, 4, 1, 1, 6};
        int targetSum = 118;
        int result = findSmallestSubarrayWithGreaterOrEqualsSum(arr, targetSum);
        System.out.println(result);
    }

    /**
     * time to solve ~ 5 mins
     * time to test ~ 4 mins
     * 1 attempt
     *
     * BEATS ~ 99%
     *
     *
     * 1 5 3 8 2
     * 10
     * sum = 10
     * end = 4
     * start = 3
     * length = 2
     */
    public static int findSmallestSubarrayWithGreaterOrEqualsSum(int[] arr, int target) {
        if (arr == null || arr.length == 0) return -1;

        int length = Integer.MAX_VALUE;
        int sum = 0;
        int start = 0;
        for (int end = 0; end < arr.length; end++) {
            sum += arr[end];

            while (sum >= target) {
                length = Math.min(length, end - start + 1);
                sum -= arr[start];
                start++;
            }
        }

        return length == Integer.MAX_VALUE ? 0 : length;
    }
}
