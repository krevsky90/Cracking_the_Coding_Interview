package solving_techniques.p18_PalindromicSubsequence;

/**
 * https://www.designgurus.io/course-play/grokking-dynamic-programming/doc/637f57620fbfd1e5b122406f
 *
 * Given a string, find the minimum number of characters that we can remove to make it a palindrome.
 * Example 1:
 * Input: "abdbca"
 * Output: 1
 * Explanation: By removing "c", we get a palindrome "abdba".
 *
 * Example 2:
 * Input: = "cddpd"
 * Output: 2
 * Explanation: Deleting "cp", we get a palindrome "ddd".
 *
 * Example 3:
 * Input: = "pqr"
 * Output: 2
 * Explanation: We have to remove any two characters to get a palindrome, e.g. if we
 * remove "pq", we get palindrome "r"
 *
 * NOTE: this is THE SAME AS Leetcode
 * 1312. Minimum Insertion Steps to Make a String Palindrome (hard)
 * https://leetcode.com/problems/minimum-insertion-steps-to-make-a-string-palindrome/description/
 *
 */
public class MinimumDeletionsInStringToMakeItPalindrome {
    public static void main(String[] args) {
        String s = "leetcode";

        System.out.println(minDeletions(s));   //expected 5, explanation: deleting 5 characters the string becomes "eee".
        System.out.println(minInsertions(s));   //expected 5, explanation: Inserting 5 characters the string becomes "leetcodocteel".
    }

    /**
     * SOLVE, BUT after hints from leetcode
     * idea: min number of elements that should be inserted OR removed = s.length() - longestPalindromicSubsequence.length()
     *
     * time to implement ~ 16 mins
     * time ~ O(s.length()*s.length())
     * space ~ O(s.length()*s.length())
     *
     * 2 attempts:
     * - typo: "i == j + 1" instead of "i + 1 == j"
     */
    public static int minDeletions(String s) {
        int len = s.length();
        int[][] dp = new int[len][len];
        int lps = longestPalindromicSubsequence(s, 0, len - 1, dp);
        return len - lps;
    }

    //for leetcode
    //Example 3:
    //Input: s = "leetcode"
    //Output: 5
    //Explanation: Inserting 5 characters the string becomes "leetcodocteel".
    public static int minInsertions(String s) {
        int len = s.length();
        int[][] dp = new int[len][len];
        int lps = longestPalindromicSubsequence(s, 0, len - 1, dp);
        return len - lps;
    }


    private static int longestPalindromicSubsequence(String s, int i, int j, int[][] dp) {
        if (dp[i][j] > 0) {
            return dp[i][j];
        }

        if (i == j) {
            return 1;
        } else if (i + 1 == j && s.charAt(i) == s.charAt(j)) {
            dp[i][j] = 2;
        } else if (s.charAt(i) == s.charAt(j)) {
            dp[i][j] = 2 + longestPalindromicSubsequence(s, i + 1, j - 1, dp);
        } else {
            dp[i][j] = Math.max(longestPalindromicSubsequence(s, i + 1, j, dp), longestPalindromicSubsequence(s, i, j - 1, dp));
        }
        return dp[i][j];
    }
}
