package solving_techniques.sliding_window.fixedWindowLength;

/**
 * https://youtu.be/MK-NZ4hN7rs?t=1138
 * Find the max sum subarray of a fixed size K
 *
 * Fixed window
 */
public class MaxSumSubarray {
    public static void main(String[] args) {
        int[] arr = new int[]{4,2,1,7,8,1,2,8,1,0};
        int size = 3;
        int result = findMaxSumSubarray(arr, size);
        System.out.println(result);
        int resultKREV = findMaxSumSubarrayKREV(arr, size);
        System.out.println(resultKREV);
    }

    //4,2,1,7,8,1,2,8,1,0
    //size = 0
    //i = 3
    //curSum = 10
    //maxSum = 10
    public static int findMaxSumSubarrayKREV(int[] arr, int size) {
        int curSum = 0;
        int maxSum = Integer.MIN_VALUE;

        for (int i = 0; i < arr.length; i++) {
            curSum += arr[i];
            if (i >= size) {
                curSum -= arr[i - size];
            }

            maxSum = Math.max(maxSum, curSum);
        }

        return maxSum;
    }

    //4,2,1,7,8,1,2,8,1,0
    //size = 3
    //i = 3
    //curSum = 8
    //maxSum = 10

    public static int findMaxSumSubarray(int[] arr, int size) {
        int curSum = 0;
        int maxSum = Integer.MIN_VALUE;

        for (int i = 0; i < arr.length; i++) {
            curSum += arr[i];
            if (i >= size - 1) {
                maxSum = Math.max(maxSum, curSum);
                curSum -= arr[i - (size - 1)];
            }
        }

        return maxSum;
    }
}
