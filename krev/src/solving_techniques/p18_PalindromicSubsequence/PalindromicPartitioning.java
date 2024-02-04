package solving_techniques.p18_PalindromicSubsequence;

/**
 * https://www.designgurus.io/course-play/grokking-dynamic-programming/doc/637f57c20fbfd1e5b1224be2
 * <p>
 * Given a string, we want to cut it into pieces such that each piece is a palindrome. Write a function to return the minimum number of cuts needed.
 * <p>
 * Example 1:
 * Input: "abdbca"
 * Output: 3
 * Explanation: Palindrome pieces are "a", "bdb", "c", "a".
 * <p>
 * Example 2:
 * Input: = "cddpd"
 * Output: 2
 * Explanation: Palindrome pieces are "c", "d", "dpd".
 * <p>
 * Example 3:
 * Input: = "pqr"
 * Output: 2
 * Explanation: Palindrome pieces are "p", "q", "r".
 * <p>
 * Example 4:
 * Input: = "pp"
 * Output: 0
 * Explanation: We do not need to cut, as "pp" is a palindrome
 */
public class PalindromicPartitioning {
    public static void main(String[] args) {
        String s1 = "abdbca";
        System.out.println(palindromicPartitioning(s1));    //expected 3
        String s2 = "cddpd";
        System.out.println(palindromicPartitioning(s2));    //expected 2
        String s3 = "pqr";
        System.out.println(palindromicPartitioning(s3));    //expected 2
        String s4 = "pp";
        System.out.println(palindromicPartitioning(s4));    //expected 0
    }

    /**
     * KREVSKY SOLUTION: (not tested automatically!)
     * idea: recursively do the following:
     * 1) find the longest palindromic substring, save its start and end indexes
     * if start > 0 => we cut
     * if end < s.length() - 1 => we cut
     * 2) apply p.1 for [0,start) and [end,ss.length()) substrings (ss) and return sub-results, where ss - CURRENT substring (initially it equals to given s)
     *
     * NOTE: it would be better to work with char[] arr = s.toCharArray(), but... leave it as is
     *
     * time to solve ~ 30 mins
     *
     * time ~ O(s.length() * s.length())
     * space ~ O(s.length() * s.length())
     * 1 attempt
     */
    public static int palindromicPartitioning(String s) {
        int result = 0;
        int[] minmax = new int[2];
        longestPalindromeSubstring(s, minmax);
        if (minmax[0] > 0) {
            result++;
            result += palindromicPartitioning(s.substring(0, minmax[0]));
        }

        if (minmax[1] < s.length() - 1) {
            result++;
            result += palindromicPartitioning(s.substring(minmax[1] + 1));
        }

        return result;
    }

    // see LongestPalindromicSubstring
    private static void longestPalindromeSubstring(String s, int[] minmax) {
        int len = s.length();
        boolean[][] dp = new boolean[len][len];

        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }

        for (int i = 0; i < len - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                dp[i][i + 1] = true;
                if (minmax[1] - minmax[0] < 1) {
                    minmax[0] = i;
                    minmax[1] = i + 1;
                }
            }
        }

        for (int i = 0; i < len - 2; i++) {
            for (int j = i + 2; j < len; j++) {
                if (dp[i + 1][j - 1] == true && s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = true;
                    if (minmax[1] - minmax[0] < j - i) {
                        minmax[0] = i;
                        minmax[1] = j;
                    }
                }
            }
        }
    }
}
