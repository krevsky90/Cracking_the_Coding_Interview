package solving_techniques.p19_LongestCommonSubstring;

/**
 * https://www.designgurus.io/course-play/grokking-dynamic-programming/doc/637f6054046a2a95a84d00bc
 *
 * Given strings s1 and s2, we need to transform s1 into s2 by deleting and inserting characters.
 * Write a function to calculate the count of the minimum number of deletion and insertion operations.
 *
 * Example 1:
 * Input: s1 = "abc"
 *        s2 = "fbc"
 * Output: 1 deletion and 1 insertion.
 * Explanation: We need to delete {'a'} and insert {'f'} to s1 to transform it into s2.
 *
 * Example 2:
 * Input: s1 = "abdca"
 *        s2 = "cbda"
 * Output: 2 deletions and 1 insertion.
 * Explanation: We need to delete {'a', 'c'} and insert {'c'} to s1 to transform it into s2.
 */
public class MinimumDeletionsAndInsertionsToTransformStringIntoAnother {
    public static void main(String[] args) {
        String s11 = "abc";
        String s12 = "fbc";
        int[] result1 = getMinDeletionsAndInsertions(s11, s12);
        System.out.println("deletions = " + result1[0] + ", additions = " + result1[1]);

        String s21 = "abdca";
        String s22 = "cbda";
        int[] result2 = getMinDeletionsAndInsertions(s21, s22);
        System.out.println("deletions = " + result2[0] + ", additions = " + result2[1]);
    }

    /**
     * KREVSKY SOLUTION:
     * idea: count the length of the longest common subsequence and then find delta s1 - lcs and s2 - lcs
     * the same idea as here https://leetcode.com/discuss/general-discussion/1274591/minimum-no-of-deletions-insertions-to-transform-1-string-into-another
     * time to solve ~ 12 mins
     * time ~ O(text1.length * text2.length)
     * space ~ O(text1.length * text2.length)
     * 2 attempts:
     * - forgot dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
     */
    public static int[] getMinDeletionsAndInsertions(String s1, String s2) {
        int lcs = longestCommonSubsequence(s1, s2);
        int deletions = s1.length() - lcs;
        int additions = s2.length() - lcs;
        return new int[]{deletions, additions};
    }


    //the same method as in src/solving_techniques/p19_LongestCommonSubstring/LongestCommonSubsequence # longestCommonSubsequence
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
