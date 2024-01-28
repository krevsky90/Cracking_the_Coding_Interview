package solving_techniques.p19_LongestCommonSubstring;

/**
 * https://www.designgurus.io/course-play/grokking-dynamic-programming/doc/637f68eaa356150aa354fe62
 *
 * Given a string and a pattern,
 * write a method to count the number of times the pattern appears in the string as a subsequence.
 *
 * Example 1: Input: string: "baxmx", pattern: "ax"
 * Output: 2
 * Explanation: {baxmx, baxmx}.
 *
 */
public class SubsequencePatternMatching {
    public static void main(String[] args) {
        String s1 = "baxmx";
        String pattern1 = "ax";
        System.out.println(countSubsequencePatternMatching(s1, pattern1));  //expected 2

        String s2 = "xaaxxa";
        String pattern2 = "ax";
        System.out.println(countSubsequencePatternMatching(s2, pattern2));  //expected 4

        String s3 = "xacxca";
        String pattern3 = "axc";
        System.out.println(countSubsequencePatternMatching(s3, pattern3));  //expected 1

        String s4 = "bbagbag";
        String pattern4 = "bag";
        System.out.println(countSubsequencePatternMatching(s4, pattern4));  //expected 7 (see https://www.educative.io/courses/grokking-dynamic-programming-a-deep-dive-using-java/distinct-subsequence-pattern-matching)
    }
    /**
     * KREVSKY SOLUTION:
     * idea:
     * 1) fill 0-th row separately
     * 2) starting from 1st row:
     *      IF dp[i-1][j] == 0 THEN dp[i][j] = 0
     *      IF pattern[i] != s[j] THEN dp[i][j] = dp[i-1][j]
     *      IF pattern[i] == s[j] THEN dp[i][j] = dp[i-1][j] + dp[i][j-1]
     *  time to solve ~ 30-40 mins
     *  time ~ O(s.length() * pattern.length())
     *  space ~ O(s.length() * pattern.length())
     *
     *  1 attempt
     */


    //   xacxcax
    //a 00111122
    //x 00001113
    //c 00000111


    public static int countSubsequencePatternMatching(String s, String sub) {
        int[][] dp = new int[sub.length()][s.length() + 1];

        for (int j = 1; j < s.length() + 1; j++) {
            if (sub.charAt(0) == s.charAt(j-1)) {
                dp[0][j] = dp[0][j-1] + 1;
            } else {
                dp[0][j] = dp[0][j-1];
            }
        }

        for (int i = 1; i < sub.length(); i++) {
            for (int j = 1; j < s.length() + 1; j++) {
                if (dp[i-1][j] == 0) {
                    dp[i][j] = 0;
                } else if (sub.charAt(i) != s.charAt(j-1)) {
                    dp[i][j] = dp[i][j-1];
                } else if (sub.charAt(i) == s.charAt(j-1)) {
                    dp[i][j] = dp[i][j-1] + dp[i-1][j];
                }
            }
        }

        return dp[sub.length() - 1][s.length()];
    }
}
