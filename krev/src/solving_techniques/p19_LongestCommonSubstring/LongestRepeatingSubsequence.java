package solving_techniques.p19_LongestCommonSubstring;

/**
 * https://www.designgurus.io/course-play/grokking-dynamic-programming/doc/637f67d2a356150aa354efa1
 * OR
 * https://www.geeksforgeeks.org/longest-repeating-subsequence/
 * and the same https://www.geeksforgeeks.org/longest-repeated-subsequence/
 * <p>
 * Given a sequence, find the length of its longest repeating subsequence (LRS).
 * A repeating subsequence will be the one that appears at least twice in the original sequence and is not overlapping
 * (i.e. none of the corresponding characters in the repeating subsequences have the same index).
 * <p>
 * Example 1:
 * Input: str = "abc"
 * Output: 0
 * There is no repeating subsequence
 * <p>
 * Example 2:
 * Input: str = "aab"
 * Output: 1
 * The two subsequence are 'a'(first) and 'a'(second).
 * Note that 'b' cannot be considered as part of subsequence
 * as it would be at same index in both.
 * <p>
 * Example 3:
 * Input: str = "aabb"
 * Output: 2
 * <p>
 * Example 4:
 * Input: str = "axxxy"
 * Output: 2
 */
public class LongestRepeatingSubsequence {
    public static void main(String[] args) {
        String s1 = "abc";
        String s2 = "aab";
        String s3 = "aabb";
        String s4 = "axxxy";

        System.out.println(longestRepeatingSubsequence(s1));    //expected 0
        System.out.println(longestRepeatingSubsequence(s2));    //expected 1
        System.out.println(longestRepeatingSubsequence(s3));    //expected 2
        System.out.println(longestRepeatingSubsequence(s4));    //expected 2
    }
    /**
     * NOT SOLVED by myself
     * idea: build dp table as if we are finding longestCommonSubsequence of s1 = s and s2 = s,
     * BUT we need to add extra condition: i != j
     *
     * time to implement ~ 15 mins
     * time ~ O(n^2), where n = s.length()
     * space ~ O(n^2), where n = s.length()
     *
     * 1 attempt
     */
    public static int longestRepeatingSubsequence(String s) {
        return longestRepeatingSubsequence(s, s);
    }

    private static int longestRepeatingSubsequence(String s1, String s2) {
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];

        for (int i = 1; i < s1.length() + 1; i++) {
            for (int j = 1; j < s2.length() + 1; j++) {
                //NOTE: the main idea is additional check "i != j"!
                if (s1.charAt(i - 1) == s2.charAt(j - 1) && i != j) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[s1.length()][s2.length()];
    }
}