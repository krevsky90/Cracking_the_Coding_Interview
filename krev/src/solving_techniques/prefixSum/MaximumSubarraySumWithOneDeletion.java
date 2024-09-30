package solving_techniques.prefixSum;

/**
 * 1186. Maximum Subarray Sum with One Deletion (medium)
 * https://leetcode.com/problems/maximum-subarray-sum-with-one-deletion
 *
 * #Company: Two Sigma Meta
 *
 * Given an array of integers, return the maximum sum for a non-empty subarray (contiguous elements) with at most one element deletion.
 * In other words, you want to choose a subarray and optionally delete one element from it so that there is still at least one element left and the sum of the remaining elements is maximum possible.
 *
 * Note that the subarray needs to be non-empty after deleting one element.
 *
 * Example 1:
 * Input: arr = [1,-2,0,3]
 * Output: 4
 * Explanation: Because we can choose [1, -2, 0, 3] and drop -2, thus the subarray [1, 0, 3] becomes the maximum value.
 *
 * Example 2:
 * Input: arr = [1,-2,-2,3]
 * Output: 3
 * Explanation: We just choose [3] and it's the maximum sum.
 *
 * Example 3:
 * Input: arr = [-1,-1,-1,-1]
 * Output: -1
 * Explanation: The final subarray needs to be non-empty. You can't choose [-1] and delete -1 from it, then get an empty subarray to make the sum equals to 0.
 *
 * Constraints:
 * 1 <= arr.length <= 10^5
 * -10^4 <= arr[i] <= 10^4
 */
public class MaximumSubarraySumWithOneDeletion {
    public static void main(String[] args) {
        int[] arr1 = {-200, 50, -100, 200, -150, -150, -40, 500, 100, -10, 50};

        MaximumSubarraySumWithOneDeletion obj = new MaximumSubarraySumWithOneDeletion();
        obj.maximumSum(arr1);

    }

    /**
     * NOT SOLVED by myself
     * info: https://leetcode.com/problems/maximum-subarray-sum-with-one-deletion/solutions/2137263/very-clean-and-easy-to-understand-dp-solution/
     * idea:
     * Kadana algorithm or prefix Sums
     * OR
     * https://www.youtube.com/watch?v=fTkkeDvqeIo&list=PLUPSMCjQ-7od5IVz8ug6D-apxFLkDTsoy&index=97
     *
     * I did not get anyone of these solution!!!
     */
    public int maximumSum(int[] arr) {
        int n = arr.length;

        if (n == 1) return arr[0];

        int possibleLargest = -1;

        int[] prefixSum = new int[n];
        int[] suffixSum = new int[n];

        prefixSum[0] = arr[0];
        suffixSum[n - 1] = arr[n - 1];

        for (int i = 1; i < n; i++) {
            prefixSum[i] = Math.max(prefixSum[i - 1] + arr[i], arr[i]);
            possibleLargest = Math.max(possibleLargest, prefixSum[i]);
        }

        for (int i = n - 2; i >= 0; i--) {
            suffixSum[i] = Math.max(suffixSum[i + 1] + arr[i], arr[i]);
            possibleLargest = Math.max(possibleLargest, suffixSum[i]);
        }

        for (int i = 1; i <= n - 2; i++) {
            possibleLargest = Math.max(prefixSum[i - 1] + suffixSum[i + 1], possibleLargest);
        }

        return possibleLargest;
    }
}
