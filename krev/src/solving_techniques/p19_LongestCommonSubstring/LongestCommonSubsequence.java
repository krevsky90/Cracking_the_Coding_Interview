package solving_techniques.p19_LongestCommonSubstring;

/**
 * https://www.designgurus.io/course-play/grokking-dynamic-programming/doc/637f5c8ba842de869cd6272b
 * OR
 * 1143. Longest Common Subsequence
 * https://leetcode.com/problems/longest-common-subsequence/
 *
 * Given two strings text1 and text2, return the length of their longest common subsequence.
 * If there is no common subsequence, return 0.
 * A subsequence of a string is a new string generated from the original string
 * with some characters (can be none) deleted without changing the relative order of the remaining characters.
 * For example, "ace" is a subsequence of "abcde".
 * A common subsequence of two strings is a subsequence that is common to both strings.
 *
 * Example 1:
 * Input: text1 = "abcde", text2 = "ace"
 * Output: 3
 * Explanation: The longest common subsequence is "ace" and its length is 3.
 *
 * Example 2:
 * Input: text1 = "abc", text2 = "abc"
 * Output: 3
 * Explanation: The longest common subsequence is "abc" and its length is 3.
 *
 * Example 3:
 * Input: text1 = "abc", text2 = "def"
 * Output: 0
 * Explanation: There is no such common subsequence, so the result is 0.
 *
 * Constraints:
 *
 * 1 <= text1.length, text2.length <= 1000
 * text1 and text2 consist of only lowercase English characters
 */
public class LongestCommonSubsequence {
    public static void main(String[] args) {
        String text11 = "abcde";
        String text12 = "ace";
        System.out.println(longestCommonSubsequence(text11, text12));
        System.out.println(longestCommonSubsequenceStr(text11, text12));
        System.out.println("---------");
        String text21 = "abc";
        String text22 = "acb";
        System.out.println(longestCommonSubsequence(text21, text22));
        System.out.println(longestCommonSubsequenceStr(text21, text22));
        System.out.println("---------");
        String text31 = "abc";
        String text32 = "def";
        System.out.println(longestCommonSubsequence(text31, text32));
        System.out.println(longestCommonSubsequenceStr(text31, text32));
        System.out.println("---------");
    }
    /**
     * KREVSKY SOLUTION:
     * time to solve + debug ~ 16 + 7 mins = 23 mins
     * 3 attempts:
     * - incorrect condition: "i < text1.length()" instead of "i < text1.length() + 1"
     * - incorrect index: "text1.charAt(i)" instead of "text1.charAt(i-1)"
     *
     * time ~ O(text1.length()*text2.length())
     * space ~ O(text1.length()*text2.length()) - can be optimized to O(text2.length()) - see LongestCommonSubstring
     */

    //just to see the difference
    //to find largest common subsequence
    //  *       a c b d a (j)
    //  *   | 0 0 0 0 0 0
    //  * a | 0 1 1 1 1 1
    //  * d | 0 1 1 1 2 2
    //  * b | 0 1 1 2 2 2
    //  * c | 0 1 2 2 2 2
    //  * a | 0 1 2 2 2 3
    //  *(i)

    ////to find largest common substring
    //  *       a c b d a (j)
    //  *   | 0 0 0 0 0 0
    //  * a | 0 1 0 0 0 1
    //  * d | 0 0 0 0 1 0
    //  * b | 0 0 0 1 0 0
    //  * c | 0 0 1 0 0 0
    //  * a | 0 1 0 0 0 1
    //  *(i)
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

    //additional solution to get the string itself
    public static String longestCommonSubsequenceStr(String text1, String text2) {
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

        //find the string basing on dp matrix and text1 and text2
        String res = longestCommonSubsequencePrint(text1, text2, dp, text1.length(), text2.length());

        return res;
    }

    public static String longestCommonSubsequencePrint(String text1, String text2, int[][] dp, int startI, int startJ) {
        if (startI == 0 || startJ == 0) {
            return "";
        }

        if (dp[startI][startJ] == dp[startI - 1][startJ]) {
            return longestCommonSubsequencePrint(text1, text2, dp, startI - 1, startJ);
        } else if (dp[startI][startJ] == dp[startI][startJ - 1]) {
            return longestCommonSubsequencePrint(text1, text2, dp, startI, startJ - 1);
        } else {
            //i.e. dp[startI][startJ] == dp[startI - 1][startJ - 1] + 1
            //AND both previous cases are skipped => it means that dp[startI][startJ] might be built ONLY on the diagonal cell [startI - 1][startJ - 1]
            return longestCommonSubsequencePrint(text1, text2, dp, startI - 1, startJ - 1) + text1.charAt(startI - 1);
        }
    }
}
