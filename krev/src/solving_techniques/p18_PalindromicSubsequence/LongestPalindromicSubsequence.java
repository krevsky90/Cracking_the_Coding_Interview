package solving_techniques.p18_PalindromicSubsequence;

/**
 * https://www.designgurus.io/course-play/grokking-dynamic-programming/doc/637f52996dd42a3426a476b7
 * OR (locked)
 * https://leetcode.com/problems/longest-palindromic-subsequence
 *
 * Given a sequence, find the length of its Longest Palindromic Subsequence (LPS).
 * In a palindromic subsequence, elements read the same backward and forward.
 * A subsequence is a sequence that can be derived from another sequence by deleting some or no elements
 * without changing the order of the remaining elements.
 *
 * Example 1:
 * Input: "abdbca"
 * Output: 5
 * Explanation: LPS is "abdba".
 *
 * Example 2:
 * Input: = "cddpd"
 * Output: 3
 * Explanation: LPS is "ddd".
 *
 * Example 3:
 * Input: = "pqr"
 * Output: 1
 *
 */
public class LongestPalindromicSubsequence {
    public static void main(String[] args) {
        String s1 = "abdbca";
        System.out.println(longestPalindromicSubsequence1(s1)); //expected 5
        System.out.println(longestPalindromicSubsequence2(s1)); //expected 5
        System.out.println(longestPalindromicSubsequence3(s1)); //expected 5
        String s2 = "cddpd";
        System.out.println(longestPalindromicSubsequence1(s2)); //expected 3
        System.out.println(longestPalindromicSubsequence2(s2)); //expected 3
        System.out.println(longestPalindromicSubsequence3(s2)); //expected 3
        String s3 = "pqr";
        System.out.println(longestPalindromicSubsequence1(s3)); //expected 1
        System.out.println(longestPalindromicSubsequence2(s3)); //expected 1
        System.out.println(longestPalindromicSubsequence3(s3)); //expected 1

    }
    /**
     * SOLUTION #1:
     * https://www.geeksforgeeks.org/longest-palindromic-subsequence-dp-12/
     * idea:
     * calculate LPS(i,j), where i and j - start and end indexes of the intial string
     * cases:
     * 1) IF i == j THEN L(i,j) = 1
     * 2) IF i+1 == j AND arr[i] == arr[j] THEN L(i,j) = 2
     * 3) IF arr[i] == arr[j] THEN L(i,j) = 2 + L(i+1, j-1)
     * 4) ELSE, i.e. arr[i] != arr[j], THEN L(i,j) = max(L(i+1,j), L(i, j-1))
     *
     * time ~ O(2^n)
     * space ~ O(n^2), where n - s.length()
     *
     * time to implement ~ 6 mins
     * 1 attempt
     */
    public static int longestPalindromicSubsequence1(String s) {
        if (s == null || s.isEmpty()) return 0;
        char[] arr = s.toCharArray();
        return lpsHelper1(arr, 0, arr.length - 1);
    }

    private static int lpsHelper1(char[] arr, int i, int j) {
        if (i == j) {
            return 1;
        } else if (i + 1 == j && arr[i] == arr[j]) {
            return 2;
        } else if (arr[i] == arr[j]) {
            return 2 + lpsHelper1(arr, i+1, j-1);
        } else {
            return Math.max(lpsHelper1(arr, i+1, j), lpsHelper1(arr, i, j-1));
        }
    }

    /**
     * SOLUTION #2:
     * idea: use DP and memoization. top-down + recursive approaches
     * time ~ O(n^2)
     * space ~ O(n^2), where n - s.length()
     *
     * time to solve ~ 13 mins
     * 2 attempts:
     * - forgot the condition "if (dp[i][j] > 0)"
     */
    public static int longestPalindromicSubsequence2(String s) {
        if (s == null || s.isEmpty()) return 0;
        char[] arr = s.toCharArray();
        int[][] dp = new int[s.length()][s.length()];
        return lpsHelper2(arr, 0, arr.length - 1, dp);
    }

    // use top-down + recursive approaches. Since there is not necessity to fill the whole dp table
    private static int lpsHelper2(char[] arr, int i, int j, int[][] dp) {
        if (dp[i][j] > 0) {
            return dp[i][j];
        }

        if (i == j) {
            return 1;   //don't see the reason to store it to dp[i][i]
        } else if (i + 1 == j && arr[i] == arr[j]) {
            dp[i][j] = 2;   //not sure that saving to dp with improve performance, but ok
        } else if (arr[i] == arr[j]) {
            dp[i][j] = 2 + lpsHelper2(arr, i+1, j-1, dp);
        } else {
            dp[i][j] = Math.max(lpsHelper2(arr, i+1, j, dp), lpsHelper2(arr, i, j-1, dp));
        }
        return dp[i][j];
    }

    /**
     * SOLUTION #3:
     * idea: use DP and memoization: bottom-up and iterative approaches
     * copied from https://www.geeksforgeeks.org/longest-palindromic-subsequence-dp-12/,
     * see "Using the Tabulation technique of Dynamic programming to find LPS"
     * time ~ O(n^2)
     * space ~ O(n^2), where n - s.length()
     */
    public static int longestPalindromicSubsequence3(String s) {
        if (s == null || s.isEmpty()) return 0;
        String r = new StringBuilder(s).reverse().toString();
        //will store the length of the longest palindromic subsequence
        // for the substring starting at index i and ending at index j
        int[][] dp = new int[s.length() + 1][s.length() + 1];

        return longestCommonSubsequence(s, r);
    }

    //copy as is from LongestCommonSubsequence
    public static int longestCommonSubsequence(String text1, String text2) {
        int[][] dp = new int[text1.length() + 1][text2.length() + 1];

        for (int i = 1; i < text1.length() + 1; i++) {
            for (int j = 1; j < text2.length() + 1; j++) {
                if (text1.charAt(i-1) == text2.charAt(j-1)) {
                    dp[i][j] = 1 + dp[i-1][j-1];
                } else {
                    dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);
                }
            }
        }

        return dp[text1.length()][text2.length()];
    }

}
