package solving_techniques;

/**
 * Given an integer array nums, find the subarray with the largest sum, and return its sum.
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
 *
 * info 1: https://leetcode.com/problems/maximum-subarray/solutions/1327840/easy-explanation-for-kadanes-algorithm-not-so-scary/
 * info 2 (the same): https://www.youtube.com/watch?v=2MmGzdiKR9Y
 */
public class KadaneAlgorithm {
    /**
     * Dynamic Programming:
     * store max sum of sub-array [0, i] for each i-th cell
     * the idea of Kadane's algorithm is to choose maximum between i-th element and max subarray [0, i-1] + i-th element
     * in other words we can cut out subarray [0, i-1] if its sum < 0
     */
    public static void main(String[] args) {
        int[] arr1 = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int[] arr2 = new int[]{1};
        int[] arr3 = new int[]{5, 4, -1, 7, 8};

        int res1 = findMaxSumOfSubarray(arr1);
        System.out.println(res1);
        int res2 = findMaxSumOfSubarray(arr2);
        System.out.println(res2);
        int res3 = findMaxSumOfSubarray(arr3);
        System.out.println(res3);
    }

    public static int findMaxSumOfSubarray(int[] arr) {
        if (arr == null || arr.length == 0) return 0;

        int[] sums = new int[arr.length];
        sums[0] = arr[0];
        int maxResult = sums[0];

        for (int i = 1; i < arr.length; i++) {
            sums[i] = Math.max(sums[i-1] + arr[i], arr[i]); //dynamic programming
            maxResult = Math.max(sums[i], maxResult);   //to avoid double traversing throught 'sums' array
        }

        return maxResult;
    }
}
