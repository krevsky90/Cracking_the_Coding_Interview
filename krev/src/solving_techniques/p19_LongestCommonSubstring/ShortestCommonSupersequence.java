package solving_techniques.p19_LongestCommonSubstring;

/**
 * https://www.designgurus.io/course-play/grokking-dynamic-programming/doc/637f64d0046a2a95a84d1556
 * OR similar, but HARDER
 * 1092. Shortest Common Supersequence
 * https://leetcode.com/problems/shortest-common-supersequence/
 *
 * NOTE: Let's solve Leetcode's problem (since designgurus's problem is simpler: count lcs, and return text1.len + text.len - lcs)
 *
 * Given two strings str1 and str2, return the shortest string that has both str1 and str2 as subsequences.
 * If there are multiple valid strings, return any of them.
 * A string s is a subsequence of string t if deleting some number of characters from t (possibly 0) results in the string s.
 *
 * Example 1:
 * Input: str1 = "abac", str2 = "cab"
 * Output: "cabac"
 * Explanation:
 * str1 = "abac" is a subsequence of "cabac" because we can delete the first "c".
 * str2 = "cab" is a subsequence of "cabac" because we can delete the last "ac".
 * The answer provided is the shortest such string that satisfies these properties.
 *
 * Example 2:
 * Input: str1 = "aaaaaaaa", str2 = "aaaaaaaa"
 * Output: "aaaaaaaa"
 *
 * Constraints:
 * 1 <= str1.length, str2.length <= 1000
 * str1 and str2 consist of lowercase English letters.
 *
 */
public class ShortestCommonSupersequence {
    public static void main(String[] args) {
        String str1 = "abac";
        String str2 = "cab";
        System.out.println(shortestCommonSupersequence(str1, str2));    //expected cabac

        String str3 = "abcf";
        String str4 = "bdcf";
        System.out.println(shortestCommonSupersequence(str3, str4));    //expected abdcf
    }

    /**
     * NOT completely solved by myself, but was too close
     * idea:
     * https://leetcode.com/problems/shortest-common-supersequence/solutions/1274116/printing-scs-shortest-common-supersequence/
     *
     * 1) build dp[][] table for LongestCommonSubsequence as usual!
     * 2) go through dp[][] table as if we want to print it - see src/solving_techniques/p19_LongestCommonSubstring/LongestCommonSubsequence # longestCommonSubsequencePrint
     * BUT with small additions (from https://leetcode.com/problems/shortest-common-supersequence/solutions/1274116/printing-scs-shortest-common-supersequence):
     * - IF we go up, i.e. decrease i iterator (of str1), THEN we print (in the end of our future result) i-1-th character of str1
     * - IF we go the the left, i.e. decrease j iterator (of str2), THEN we print (in the end of our future result) j-1-th character of str2
     * - IF we go diagonally, THEN we print (in the end of our future result) i-1-th character of str1 OR j-1-th character of str2 (no matter since they are equals)
     *
     * time to solve 90 mins
     * time ~ O(text1.length * text2.length) + O(text1.length + text2.length) = O(text1.length * text2.length)
     * space ~ O(text1.length * text2.length)
     *
     * 3 attempts:
     * - set incorrect base condition: (startI or startJ == 0) instead of 2 separate conditions
     * - incorrect sequence of IF-condition for going through dp table. Had to move "dp[startI][startJ] == dp[startI - 1][startJ - 1] + 1" condition to the bottom
     */
    public static String shortestCommonSupersequence(String text1, String text2) {
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

        //find Supersequence (string) basing on dp matrix and text1 and text2
        String res = shortestCommonSupersequencePrint(text1, text2, dp, text1.length(), text2.length());

        return res;
    }

    /**
     * time ~ O(text1.length + text2.length)
     */
    public static String shortestCommonSupersequencePrint(String text1, String text2, int[][] dp, int startI, int startJ) {
        if (startI == 0) {
            //append (in the beginning of th final result) part of text2 that is not considered, as is
            return text2.substring(0, startJ);
        }

        if (startJ == 0) {
            //append (in the beginning of th final result) part of text1 that is not considered, as is
            return text1.substring(0, startI);
        }

        if (dp[startI][startJ] == dp[startI - 1][startJ]) {
            return shortestCommonSupersequencePrint(text1, text2, dp, startI - 1, startJ) + text1.charAt(startI - 1);
        } else if (dp[startI][startJ] == dp[startI][startJ - 1]) {
            return shortestCommonSupersequencePrint(text1, text2, dp, startI, startJ - 1) + text2.charAt(startJ - 1);
        } else {
            //i.e. dp[startI][startJ] == dp[startI - 1][startJ - 1] + 1
            //AND both previous cases are skipped => it means that dp[startI][startJ] might be built ONLY on the diagonal cell [startI - 1][startJ - 1]
            //so print, for example, char from text1
            return shortestCommonSupersequencePrint(text1, text2, dp, startI - 1, startJ - 1) + text1.charAt(startI - 1);
        }
    }
}