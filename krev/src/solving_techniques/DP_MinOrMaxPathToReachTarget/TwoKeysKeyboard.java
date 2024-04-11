package solving_techniques.DP_MinOrMaxPathToReachTarget;

/**
 * 650. 2 Keys Keyboard
 * https://leetcode.com/problems/2-keys-keyboard
 * <p>
 * There is only one character 'A' on the screen of a notepad. You can perform one of two operations on this notepad for each step:
 * <p>
 * Copy All: You can copy all the characters present on the screen (a partial copy is not allowed).
 * Paste: You can paste the characters which are copied last time.
 * Given an integer n, return the minimum number of operations to get the character 'A' exactly n times on the screen.
 * <p>
 * Example 1:
 * Input: n = 3
 * Output: 3
 * Explanation: Initially, we have one character 'A'.
 * In step 1, we use Copy All operation.
 * In step 2, we use Paste operation to get 'AA'.
 * In step 3, we use Paste operation to get 'AAA'.
 * <p>
 * Example 2:
 * Input: n = 1
 * Output: 0
 * <p>
 * Constraints:
 * 1 <= n <= 1000
 */
public class TwoKeysKeyboard {
    /**
     * NOT SOLVED by myself
     * idea: https://www.youtube.com/watch?v=CIDdvpX66IY
     * time to implement ~ 6 mins
     * <p>
     * 3 attempts:
     * - incorrect formula (I used my own formula)
     * - incorrect size of array
     */
    //idea: https://www.youtube.com/watch?v=CIDdvpX66IY
    //but I found my own formula
    public int minSteps(int n) {
        int[] dp = new int[Math.max(4, n + 1)];
        dp[0] = 0;
        dp[1] = 0;
        dp[2] = 2;
        dp[3] = 3;

        for (int i = 4; i <= n; i++) {
            dp[i] = i;  //anyway we can reach i by copying 'A' n-1 times
            for (int j = 2; j < i; j++) {
                if (i % j == 0) {
                    //+1 - since we perform Copy operation
                    //i/j - 1 - since we already have 1 instance of 'AA..A' with length j
                    dp[i] = Math.min(dp[i], dp[j] + 1 + (i / j - 1));
                }
            }
        }

        return dp[n];
    }
}
