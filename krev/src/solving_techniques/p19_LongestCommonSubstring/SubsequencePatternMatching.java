package solving_techniques.p19_LongestCommonSubstring;

/**
 * https://www.designgurus.io/course-play/grokking-dynamic-programming/doc/637f68eaa356150aa354fe62
 * OR
 * 115. Distinct Subsequences (hard)
 * https://leetcode.com/problems/distinct-subsequences/
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
        System.out.println(countSubsequencePattern2(s1, pattern1));  //expected 2

        String s2 = "xaaxxa";
        String pattern2 = "ax";
        System.out.println(countSubsequencePattern2(s2, pattern2));  //expected 4

        String s3 = "xacxca";
        String pattern3 = "axc";
        System.out.println(countSubsequencePattern2(s3, pattern3));  //expected 1

        String s4 = "bbagbag";
        String pattern4 = "bag";
        System.out.println(countSubsequencePattern2(s4, pattern4));  //expected 7 (see https://www.educative.io/courses/grokking-dynamic-programming-a-deep-dive-using-java/distinct-subsequence-pattern-matching)

        String s5 = "rabbbit";
        String pattern5 = "rabbit";
        System.out.println(countSubsequencePattern2(s5, pattern5));  //expected 3
    }

    /**
     * NOT SOLVED (correctly) by myself:
     * info:
     * https://leetcode.com/problems/distinct-subsequences/solutions/4895290/java-clean-solution-beginner-friendly-interview-question/
     *
     * SOLUTION #1: backtracking + recursion
     * idea: generate ALL subsequences of s (using the logic of Knapsack 0/1) and save to SB
     * and count the subseq that = t
     *
     * time ~ O(2^s_len * t_len)
     * space ~ O(s_len) - stringBuilder's space
     *
     * NOT time-optimal and there are no overlaps, since all combinations can be unique
     *
     */
    public static int countSubsequencePattern1(String s, String t) {
        return countSubsequencePattern1(s, t, new StringBuilder(), 0);
    }

    private static int countSubsequencePattern1(String s, String t, StringBuilder sb, int idx) {
        //base case:
        if (idx >= s.length()) {
            if (sb.toString().equals(t)) {
                return 1;
            } else {
                return 0;
            }
        }

        //take idx-th element:
        sb.append(s.charAt(idx));
        int take = countSubsequencePattern1(s, t, sb, idx + 1);
        sb.deleteCharAt(sb.length() - 1);   //backtracking
        //not take
        int notTake = countSubsequencePattern1(s, t, sb, idx + 1);

        return take + notTake;
    }

    /**
     * SOLUTION #2: top-down + memo
     * info:
     * https://leetcode.com/problems/distinct-subsequences/solutions/4895290/java-clean-solution-beginner-friendly-interview-question/
     *
     * idea:
     * 1) track i, j to countSubseqs in substrings of s and t
     * 2) if s[i] == t[j], then we consider 2 options: include s[i] to subseq that potentially = t OR not!
     * 2.2) if s[i] != t[j], then we skip s[i] and find further
     *
     * time ~ O(s_len*t_len)
     * space ~ O(s_len*t_len)
     *
     * BEATS = 13%
     *
     */
    public static int countSubsequencePattern2(String s, String t) {
        Integer[][] dp = new Integer[s.length() + 1][t.length() + 1];
        return countSubsequencePattern2(s, t, 0, 0, dp);
    }

    public static int countSubsequencePattern2(String s, String t, int i, int j, Integer[][] dp) {
        //base cases:
        if (j >= t.length()) return 1;
        if (i >= s.length()) return 0;

        if (dp[i][j] != null) return dp[i][j];

        if (s.charAt(i) == t.charAt(j)) {
            //idea! consider take and not take cases!
            int take = countSubsequencePattern2(s, t, i + 1, j + 1, dp);
            int notTake = countSubsequencePattern2(s, t, i + 1, j, dp);
            dp[i][j] = take + notTake;
        } else {
            //just go further
            dp[i][j] = countSubsequencePattern2(s, t, i + 1, j, dp);
        }
        return dp[i][j];
    }

    /**
     * NOTE: it is INCORRECT! example:
     * "rabbbit" and "rabbit": it returns 6, but correct answer = 3
     *
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