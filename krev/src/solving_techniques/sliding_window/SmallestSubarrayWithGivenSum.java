package solving_techniques.sliding_window;

/**
 * https://youtu.be/MK-NZ4hN7rs?t=1493
 *
 * Dynamic window:
 * 1) increase window (move end pointer) until sum < targetSum.
 * 2) Inside p.1's iteration: move start point until sum >= targetSum
 */
public class SmallestSubarrayWithGivenSum {
    public static void main(String[] args) {
        int[] arr = new int[]{4, 2, 1, 7, 8, 1, 2, 8, 1, 0};
        int targetSum = 8;
        int result = findSmallestSubarraySizeWithGivenSum(arr, targetSum);
        System.out.println(result);
    }

    public static int findSmallestSubarraySizeWithGivenSum(int[] arr, int targetSum) {
        int minWindowSize = Integer.MAX_VALUE;
        int start = 0;
        int tempSum = 0;

        for (int end = 0; end < arr.length; end++) {
            tempSum += arr[end];

            while (tempSum >= targetSum && minWindowSize > 0) {
                minWindowSize = Math.min(minWindowSize, end - start + 1);
                //cut the most left element
                tempSum -= arr[start];
                start++;
            }
        }

        return minWindowSize;
    }
}
