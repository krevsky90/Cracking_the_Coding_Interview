package solving_techniques.sliding_window;

/**
 * https://youtu.be/jM2dhDPYMQM?t=1086
 *
 * Given an array of 0's and 1's, find the maximum sequence of continuous 1's that can be formed by flipping at-most k 0's to 1's
 *
 * Example: [0, 1, 0, 1, 0, 0, 1, 1]
 * max flips: 2
 * Output: 5 (since [1, 0, 0, 1, 1])
 *
 * Time ~ O(n)
 * Space ~ O(1)
 */
public class SlidingWindowWithFlippingOrSwapping {
    public static void main(String[] args) {
        int[] arr = new int[]{0, 1, 0, 1, 0, 0, 1, 1};
        int maxFlips = 2;
        int[] result = getMaxSequenceWithFlipping(arr, maxFlips);
        System.out.println(result.length);
    }

    public static int[] getMaxSequenceWithFlipping(int[] arr, int maxFlips) {
        int start = 0;
        int maxLen = Integer.MIN_VALUE;
        int zeroCounter = 0;

        for (int end = 0; end < arr.length; end++) {
            if (arr[end] == 0) zeroCounter++;

            if (zeroCounter > maxFlips) {
                if (arr[start] == 0) zeroCounter--;
                start++;
            }

            maxLen = Math.max(maxLen, end - start + 1);
        }

        int[] result = new int[maxLen];
        System.arraycopy(arr, start, result, 0, maxLen);    // in case if we want to return sequence (i.e. subarray) rather thab its length
        return result;
    }
}
