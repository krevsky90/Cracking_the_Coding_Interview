package solving_techniques.p16_UnboundedKnapsack;

/**
 * https://www.designgurus.io/course-play/grokking-dynamic-programming/doc/637f4536e57c531cec398fbd
 * <p>
 * We are given a ribbon of length ?n? and a set of possible ribbon lengths.
 * We need to cut the ribbon into the maximum number of pieces that comply with the above-mentioned possible lengths.
 * Write a method that will return the count of pieces.
 * <p>
 * Example 1:
 * n: 5
 * Ribbon Lengths: {2,3,5}
 * Output: 2
 * Explanation: Ribbon pieces will be {2,3}.
 * <p>
 * Example 2:
 * n: 7
 * Ribbon Lengths: {2,3}
 * Output: 3
 * Explanation: Ribbon pieces will be {2,2,3}.
 * <p>
 * Example 3:
 * n: 13
 * Ribbon Lengths: {3,5,7}
 * Output: 3
 * Explanation: Ribbon pieces will be {3,3,7}
 */
public class MaximumRibbonCut {
    public static void main(String[] args) {
        int[] ribbons1 = {2, 3, 5};
        int totalLength1 = 5;
        System.out.println(maximumRibbonCut(ribbons1, totalLength1));   //2, since 2+3 (but not 5)
        System.out.println(maximumRibbonCutSpaceOptimized(ribbons1, totalLength1));   //2, since 2+3 (but not 5)

        int[] ribbons2 = {3, 5, 7};
        int totalLength2 = 14;
        System.out.println(maximumRibbonCut(ribbons2, totalLength2));   //4, since 3+3+3+5 (but not 7+7)
        System.out.println(maximumRibbonCutSpaceOptimized(ribbons2, totalLength2));   //4, since 3+3+3+5 (but not 7+7)
    }

    /**
     * KREVSKY SOLUTION:
     * idea - similar to MinimumCoinChange problem. But now we search max, but not min.
     * <p>
     * dp = [arr.length + 1][capacity + 1]
     * dp[i][j] - stores maximum elements from arr (from 0 to i) whose sum = j
     * <p>
     * exclude: dp[i-1][j]
     * include: 1 + dp[i][j - arr[i-1]], where we write "+1" because we include i-1-th element
     * <p>
     * IF arr[i-1] > j) THEN dp[i][j] = exclude = dp[i-1][j]
     * ELSE dp[i][j] = max(exclude, include)
     * <p>
     * base case 1:
     * dp[0][j] = -Inf
     * base case 2:
     * dp[i][0] = 0
     * ---------------
     * <p>
     * time to solve ~ 15 mins
     * time ~ O(arr.length * capacity)
     * space ~ O(arr.length * capacity)
     * 2 attempts:
     * - wrote "else if (arr[i-1] > capacity)" instead of "else if (arr[i-1] > j)"
     */
    public static int maximumRibbonCut(int[] arr, int capacity) {
        int[][] dp = new int[arr.length + 1][capacity + 1];

        for (int i = 0; i < arr.length + 1; i++) {
            for (int j = 0; j < capacity + 1; j++) {
                if (i == 0) {
                    //base case 1:
                    dp[i][j] = Integer.MIN_VALUE;
                } else if (j == 0) {
                    //base case 2:
                    dp[i][j] = 0;
                } else if (arr[i - 1] > j) {
                    //use only exclude option
                    dp[i][j] = dp[i - 1][j];
                } else {
                    int exclude = dp[i - 1][j];
                    //NOTE: IF dp[i][j - arr[i - 1]] = MIN_VALUE THEN include = MIN_VALUE+1, => it is better to check if value = MIN_VALUE and set it itself
                    int include = dp[i][j - arr[i - 1]] == Integer.MIN_VALUE ?
                            Integer.MIN_VALUE :
                            (1 + dp[i][j - arr[i - 1]]);
                    dp[i][j] = Math.max(include, exclude);
                }
            }
        }

        return dp[arr.length][capacity];
    }

    /**
     * KREVSKY SOLUTION:
     * Optimize space since i-th row is based on i-1-th row
     * time to solve ~ 20 mins!
     * 1 attemps
     */
    public static int maximumRibbonCutSpaceOptimized(int[] arr, int capacity) {
        int[] dp = new int[capacity + 1];

        for (int i = 0; i < arr.length + 1; i++) {
            for (int j = 0; j < capacity + 1; j++) {
                if (i == 0) {
                    //base case 1:
                    dp[j] = Integer.MIN_VALUE;
                } else if (j == 0) {
                    //base case 2:
                    dp[j] = 0;
                } else if (arr[i - 1] > j) {
                    //use only exclude option => leave dp[j] as is!
                } else {
                    int exclude = dp[j];
                    int include = 1 + dp[j - arr[i - 1]];
                    dp[j] = Math.max(include, exclude);
                }
            }
        }

        return dp[capacity];
    }
}
