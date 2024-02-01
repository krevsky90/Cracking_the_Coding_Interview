package solving_techniques.p19_LongestCommonSubstring;

/**
 * https://www.designgurus.io/course-play/grokking-dynamic-programming/doc/637f6cbb046a2a95a84d385a
 * OR
 * 72. Edit Distance
 * https://leetcode.com/problems/edit-distance/description/
 *
 * Given two strings word1 and word2, return the minimum number of operations required to convert word1 to word2.
 * You have the following three operations permitted on a word:
 *
 * Insert a character
 * Delete a character
 * Replace a character
 *
 * Example 1:
 * Input: word1 = "horse", word2 = "ros"
 * Output: 3
 * Explanation:
 * horse -> rorse (replace 'h' with 'r')
 * rorse -> rose (remove 'r')
 * rose -> ros (remove 'e')
 *
 * Example 2:
 * Input: word1 = "intention", word2 = "execution"
 * Output: 5
 * Explanation:
 * intention -> inention (remove 't')
 * inention -> enention (replace 'i' with 'e')
 * enention -> exention (replace 'n' with 'x')
 * exention -> exection (replace 'n' with 'c')
 * exection -> execution (insert 'u')
 *
 * Constraints:
 * 0 <= word1.length, word2.length <= 500
 * word1 and word2 consist of lowercase English letters.
 */
public class EditDistance {
    /**
     * NOT SOLVED by myself (did not found the right idea)
     * idea: https://www.youtube.com/watch?v=MiqoA-yF-0M
     *      dp[i][j] is the distance between word1.substring(0,i + 1) and  word2.subtring(0, j + 1)
     *      deleteDist = dp[i][j-1];
     *      insertDist = dp[i-1][j];
     *      replaceDist = dp[i-1][j-1];
     *
     * time to implement ~ 6 mins
     * time ~ O(word1.length() * word2.length())
     * space ~ O(word1.length() * word2.length())
     *
     * 1 attempt
     */
    public int minDistance(String word1, String word2) {
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];

        //count the distance for transformation word1.substring(0,i + 1) to "" (i.e. empty string)
        //i.e. distance for delete operation
        for (int i = 0; i < word1.length() + 1; i++) {
            dp[i][0] = i;
        }

        //count the distance for transformation "" (i.e. empty string) to word2.substring(0,j + 1)
        //i.e. distance for insert operation
        for (int j = 1; j < word2.length() + 1; j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i < word1.length() + 1; i++) {
            for (int j = 1; j < word2.length() + 1; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    //the idea!!!
                    int deleteDist = dp[i][j-1];
                    int insertDist = dp[i-1][j];
                    int replaceDist = dp[i-1][j-1];

                    // +1 since we do one of these actions
                    dp[i][j] = Math.min(deleteDist, Math.min(insertDist, replaceDist)) + 1;
                }
            }
        }

        return dp[word1.length()][word2.length()];
    }
}