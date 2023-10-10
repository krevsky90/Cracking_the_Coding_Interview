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
        int[] arr = new int[]{-2,-1,-3};
        int resultN3 = getMaxContiguousSubarraySumN3(arr);
        int resultN2 = getMaxContiguousSubarraySumN2(arr);
        int resultOnSpace = getMaxContiguousSubarraySumOnSpace(arr);
        int resultO1Space = getMaxContiguousSubarraySumO1Space(arr);
        System.out.println(resultN3);
        System.out.println(resultN2);
        System.out.println(resultOnSpace);
        System.out.println(resultO1Space);
    }

    /**
     * brute force O(n^3)
     * time to solve ~ 30 mins
     * 2 attempts
     *
     * DEBUG:
     * 					-2	1	-3	4	-1	2	1	-5	4
     * i1					i1
     * i2									i2
     * i3									i3
     * tempResult  						0
     * tempTempResult						0
     * sum									-1
     */
    public static int getMaxContiguousSubarraySumN3(int[] arr) {
        int tempTempResult = Integer.MIN_VALUE;
        for (int i1 = 0; i1 < arr.length; i1++) {
            for (int i2 = i1 + 1; i2 < arr.length; i2++) {
                int sum = 0;
                for (int i3 = i1; i3 <= i2; i3++) {
                    sum += arr[i3];
                    tempTempResult = Math.max(tempTempResult, sum);
                }
            }
        }

        return tempTempResult;
    }

    /**
     * brute force O(n^2)
     * time to solve ~ 6 mins
     * 1 attempt
     */
    public static int getMaxContiguousSubarraySumN2(int[] arr) {
        int tempResult = Integer.MIN_VALUE;
        for (int i1 = 0; i1 < arr.length; i1++) {
//            int tempResult = arr[i1];
            int sum = 0;
            for (int i2 = i1; i2 < arr.length; i2++) {
                sum += arr[i2];
                tempResult = Math.max(tempResult, sum);
            }
//            result = Math.max(result, tempResult);
        }

        return tempResult;
    }

    /**
     * info: https://www.youtube.com/watch?v=2MmGzdiKR9Y
     * Kadana's algorithm (dynamic programming)
     * time complexity ~ O(n)
     * space complexity ~ O(n)
     *
     */
    public static int getMaxContiguousSubarraySumOnSpace(int[] arr) {
        if (arr == null || arr.length == 0) throw new IllegalArgumentException();

        int[] memo = new int[arr.length];       //this is the idea!
        memo[0] = arr[0];
        int result = Integer.MIN_VALUE;

        for (int i = 1; i < arr.length; i++) {
            memo[i] = Math.max(arr[i], memo[i-1] + arr[i]); //this is the idea!
            result = Math.max(memo[i], result);     //to avoid double traversing throught 'sums' array
        }

        return result;
    }

    /**
     * info: https://www.youtube.com/watch?v=2MmGzdiKR9Y
     * Kadana's algorithm (dynamic programming)
     * time complexity ~ O(n)
     * space complexity ~ O(1)
     *
     */
    public static int getMaxContiguousSubarraySumO1Space(int[] arr) {
        if (arr == null || arr.length == 0) throw new IllegalArgumentException();

        int prevSum = arr[0];
        int sum = arr[0];
        int result = Integer.MIN_VALUE;

        for (int i = 1; i < arr.length; i++) {
            sum = Math.max(arr[i], prevSum + arr[i]); //this is the idea!
            prevSum = sum;
            result = Math.max(sum, result); //to avoid double traversing throught 'sums' array
        }

        return result;
    }
}
