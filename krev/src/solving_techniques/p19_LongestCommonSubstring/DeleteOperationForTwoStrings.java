package solving_techniques.p19_LongestCommonSubstring;

/**
 * 583. Delete Operation for Two Strings
 * https://leetcode.com/problems/delete-operation-for-two-strings
 * <p>
 * Given two strings word1 and word2, return the minimum number of steps required to make word1 and word2 the same.
 * In one step, you can delete exactly one character in either string.
 * <p>
 * Example 1:
 * Input: word1 = "sea", word2 = "eat"
 * Output: 2
 * Explanation: You need one step to make "sea" to "ea" and another step to make "eat" to "ea".
 * <p>
 * Example 2:
 * Input: word1 = "leetcode", word2 = "etco"
 * Output: 4
 * <p>
 * Constraints:
 * 1 <= word1.length, word2.length <= 500
 * word1 and word2 consist of only lowercase English letters.
 */
public class DeleteOperationForTwoStrings {
    /**
     * KREVSKY SOLUTION:
     * idea: it the same as src/solving_techniques/p19_LongestCommonSubstring/MinimumDeletionsAndInsertionsToTransformStringIntoAnother.java
     * <p>
     * time to solve 9 mins
     * <p>
     * time ~ O(len1*len2)
     * space ~ O(len1*len2)
     * <p>
     * 1 attempt
     * <p>
     * BEATS = 83%
     */
    public int minDistance(String word1, String word2) {
        int lcs = longestCommonSubsequence(word1, word2);
        return word1.length() + word2.length() - 2 * lcs;
    }

    private int longestCommonSubsequence(String word1, String word2) {
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];
        for (int i = 1; i <= word1.length(); i++) {
            for (int j = 1; j <= word2.length(); j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[word1.length()][word2.length()];
    }
}
