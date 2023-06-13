package data_structures.chapter1_arrays_n_strings.extra;

/**
 * https://www.youtube.com/watch?v=2MmGzdiKR9Y
 *
 * Question: Given an integer array, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.
 *
 * dynamic programming - Kadana's algorithm
 */
public class MaxContiguousSubarraySum_DynP {
    public static void main(String[] args) {
//        int[] arr = new int[]{-2,1,-3,4,-1,2,1,-5,4};
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
//            int tempResult = arr[i1];
            for (int i2 = i1 + 1; i2 < arr.length; i2++) {
//                int tempTempResult = arr[i1];
                int sum = 0;
                for (int i3 = i1; i3 <= i2; i3++) {
                    sum += arr[i3];
                    tempTempResult = Math.max(tempTempResult, sum);
                }
//                tempResult = Math.max(tempResult, tempTempResult);
            }
//            result = Math.max(result, tempResult);
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
            result = Math.max(memo[i], result);
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
            result = Math.max(sum, result);
        }

        return result;
    }
}
